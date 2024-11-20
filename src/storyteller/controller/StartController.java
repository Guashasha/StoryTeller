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
        getTales();
        configureVBox();
        generateTaleButtons();
        logoImageView.setImage(new Image("/resources/logo.png"));
    }

    private void configureVBox() {
        talesVBox.setSpacing(15);
    }

    private void generateTaleButtons() {
        for (Tale tale : tales) {
            Button button = new Button(tale.getTitle());
            button.setOnAction(e -> {
                try {
                    FXMLLoader guiLoader = new FXMLLoader(getClass().getResource("storyteller/view/ReadTale.fxml"));
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

    public void getTales() {
        Connection conexionBD = ConnectionDB.obtainConnection();

        if (conexionBD != null) {
            try {
                String query = "SELECT cuento.id, titulo, texto_espanol, completado, contestada, pregunta español, pregunta ingles, palabra_espanol, palabra_ingles FROM cuento, pregunta, palabra where cuento.id=pregunta.id_cuento and cuento.id=palabra.id_cuento";
                PreparedStatement taleStatement = conexionBD.prepareStatement(query);
                ResultSet taleResult = taleStatement.executeQuery();

                while (taleResult.next()) {
                    Tale tale = new Tale();
                    tale.setId(taleResult.getInt("id"));
                    tale.setTitle(taleResult.getString("titulo"));
                    tale.setSpanishText(taleResult.getString("texto_espanol"));
                    tale.setState(taleResult.getInt("completado") == 0 ? State.PENDING : State.COMPLETED);

                    Question question = new Question();
                    question.setSpanishQuestion(taleResult.getString("pregunta_espanol"));
                    question.setEnglishQuestion(taleResult.getString("pregunta_ingles"));

                    Word answer = new Word();
                    answer.setSpanishWord(taleResult.getString("palabra_espanol"));
                    answer.setEnglishWord(taleResult.getString("palabra_espanol"));
                    question.SetQuestionAnswer(answer);

                    question.setIsAnswered(taleResult.getInt("contestada"));

                    tale.setQuestion(question);
                    tales.add(tale);
                }

                conexionBD.close();
            } catch (SQLException sqlex) {
                System.err.println("Ocurrió un error al leer los cuentos del servidor");
            }
        }
        else {
            System.err.println("No se pudo conectar con los servicios de storyteller");
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
