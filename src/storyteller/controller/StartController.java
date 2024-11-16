package storyteller.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import storyteller.model.pojo.Tale;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import storyteller.model.ConnectionDB;
import storyteller.model.pojo.Question;
import storyteller.model.pojo.State;
import storyteller.model.pojo.Word;

public class StartController {
    @FXML
    private Button btnLearnedWords;
    @FXML
    private VBox talesVBox;
    @FXML
    private ImageView logoImageView;

    private ArrayList<Tale> tales;
    private Stage stage;

    public void initialize() {
        obtainTales();
        configureVBox();
        generateTaleButtons();
        logoImageView.setImage(new Image("/resources/logo.png"));
    }

    private void obtainTales() {
        tales = (ArrayList<Tale>) getTales().get("tales");
    }

    private void configureVBox() {
        talesVBox.setSpacing(15);
    }

    private void generateTaleButtons() {
        for (Tale tale : tales) {
            Button button = new Button(tale.getTitle());
            button.setOnAction(e -> {
                try {
                    FXMLLoader guiLoader = new FXMLLoader(getClass().getResource("/storyteller/view/ReadTale.fxml"));
                    Parent root = guiLoader.load();

                    ReadTale readTaleController = guiLoader.getController();
                    readTaleController.initialize(tale);

                    Scene scene = new Scene(root);
                    stage.setTitle(tale.getTitle());
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception error) {
                    error.printStackTrace();
                }
            });

            button.setStyle(
                "-fx-background-color: #FFB98D; " +
                "-fx-border-radius: 8; " +
                "-fx-background-radius: 9; " +
                "-fx-border-color: #000000; " +
                "-fx-border-width: 1; " +
                "-fx-pref-width: 720px; " +
                "-fx-pref-height: 60px; " +
                "-fx-font-size: 18px;"
            );
            talesVBox.getChildren().add(button);
        }
    }


    public void goToLearnedWords() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/storyteller/view/LearnedWords.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) btnLearnedWords.getScene().getWindow();
            currentStage.close();
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("Learned Words");
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, Object> getTales() {
        HashMap<String, Object> response = new LinkedHashMap<>();
        response.put("error", true);
        Connection conexionBD = ConnectionDB.obtainConnection();
        ArrayList<Tale> tales = new ArrayList<>();

        if (conexionBD != null) {
            try {
                String taleQuery = "SELECT id, titulo, texto_espanol, completado FROM cuento";
                PreparedStatement taleStatement = conexionBD.prepareStatement(taleQuery);
                ResultSet taleResult = taleStatement.executeQuery();

                while (taleResult.next()) {
                    Tale tale = new Tale();
                    tale.setId(taleResult.getInt("id"));
                    tale.setTitle(taleResult.getString("titulo"));
                    tale.setSpanishText(taleResult.getString("texto_espanol"));
                    tale.setState(taleResult.getInt("completado") == 0 ? State.PENDING : State.COMPLETED);

                    String questionQuery = "SELECT pregunta_espanol, pregunta_ingles, respuesta, lenguaje_respuesta, contestada FROM pregunta WHERE id_cuento = ?";
                    PreparedStatement questionStatement = conexionBD.prepareStatement(questionQuery);
                    questionStatement.setInt(1, tale.getId());

                    ResultSet questionResult = questionStatement.executeQuery();

                    if (questionResult.next()) {
                        Question question = new Question();
                        question.setSpanishQuestion(questionResult.getString("pregunta_espanol"));
                        question.setEnglishQuestion(questionResult.getString("pregunta_ingles"));

                        Word answer = new Word();
                        if ("espanol".equals(questionResult.getString("lenguaje_respuesta"))) {
                            answer.setSpanishWord(questionResult.getString("respuesta"));
                        } else {
                            answer.setEnglishWord(questionResult.getString("respuesta"));
                        }
                        question.SetQuestionAnswer(answer);

                        question.setIsAnswered(questionResult.getInt("contestada"));

                        tale.setQuestion(question);
                    }
                    tales.add(tale);
                    questionStatement.close();
                }

                response.put("error", false);
                response.put("tales", tales);
                conexionBD.close();
            } catch (SQLException sqlex) {
                response.put("message", "Error: " + sqlex.getMessage());
            }
        } else {
            response.put("message", "Por el momento este cuento no se encuentra disponible, inténtalo de nuevo más tarde. ¡Puedes leer otro cuento mientras esperas!");
        }
        return response;
    }



    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
