package storyteller.controller;

import java.io.IOException;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import storyteller.model.ConnectionDB;
import storyteller.model.pojo.Tale;
import storyteller.model.pojo.Word;
import storyteller.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedHashMap;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ReadTale {
    @FXML
    private Button spanishBtn;
    @FXML
    private Button englishBtn;
    @FXML
    private Button acceptBtn;
    @FXML
    private TextField answerTf;
    @FXML
    private Text titleTxt;
    @FXML
    private Text contentTxt;
    @FXML
    private Text questionTxt;

    private Stage stage;
    private Tale tale;

    public void initialize(Tale tale) {
        this.tale = tale;
        this.titleTxt.setText(tale.getTitle());
        this.contentTxt.setText(tale.getSpanishText());
        this.questionTxt.setText(tale.getQuestion().getSpanishQuestion());
        this.contentTxt.setText(tale.getSpanishText());
    }

    // translate the story content to the selected language
    // 0 for spanish
    // 1 for english
    @FXML
    private void translateTale(int language){
        Word answers = this.tale.getQuestion().getQuestionAnswer();
        String englishWord = answers.getEnglishWord();
        String spanishWord = answers.getSpanishWord();
        String translatedTale;

        switch (language) {
            case 1:
                translatedTale = tale.getSpanishText().replace(spanishWord, englishWord);
                questionTxt.setText(tale.getQuestion().getEnglishQuestion());
                break;

            case 2:
                translatedTale = tale.getSpanishText();
                questionTxt.setText(tale.getQuestion().getSpanishQuestion());
                break;

            default:
                return;
        }

        contentTxt.setText(translatedTale);
    }

    @FXML
    private void answerQuestion() {
        String correctAnswer = tale.getQuestion().getQuestionAnswer().getSpanishWord();
        String userAnswer = answerTf.getText().trim();

        if (correctAnswer.equals(userAnswer)) {
            Utils.showSimpleAlert("Felicidades", "Respuesta correcta, has completado el cuento :D", Alert.AlertType.CONFIRMATION);
            setTaleCompleted(tale.getId());
            setQuestionAnswered(tale.getId());
            goBack();
        }
        else {
            Utils.showSimpleAlert("incorrecto", "La repuesta no es correcta, intenta nuevamente.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void goBack() {
        Stage stage = (Stage) titleTxt.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader guiLoader = new FXMLLoader(getClass().getResource("/storyteller/view/Start.fxml"));

            Parent root = guiLoader.load();
            StartController controller = guiLoader.getController();
            controller.setStage(stage);
            Scene scene = new Scene(root);
            stage.setTitle("Storyteller");
            stage.setScene(scene);
            stage.show();
        } catch(IOException ioException){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Hubo un problema");
            alert.setContentText("Se presentó un problema inesperado, vuelve a intentarlo");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnClickSpanish(ActionEvent event) {
        translateTale(2);
    }

    @FXML
    private void btnClickEnglish(ActionEvent event) {
        translateTale(1);
    }
    
    public void setTaleCompleted(int idTale){
        Connection conexionBD = ConnectionDB.obtainConnection();
        if(conexionBD != null){
            try {
                String query = "UPDATE cuento SET completado = 1 WHERE id = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
                preparedStatement.setInt(1, idTale);
                int rowsAffected = preparedStatement.executeUpdate();
                
                if(rowsAffected > 0){
                    System.out.println("Cuento marcado como completado");
                }

                conexionBD.close();
            } catch (SQLException sqlex) {
                System.err.println("Error: " + sqlex.getMessage());
            }
        }
    }

    private void setQuestionAnswered(int idTale) {
                Connection conexionBD = ConnectionDB.obtainConnection();
        if(conexionBD != null){
            try {
                String query = "UPDATE pregunta SET contestada = 1 WHERE id_cuento = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
                preparedStatement.setInt(1, idTale);
                int rowsAffected = preparedStatement.executeUpdate();
                
                if(rowsAffected > 0){
                    System.out.println("Pregunta marcada como completada");
                }

                conexionBD.close();
            } catch (SQLException sqlex) {
                System.err.println("Error: " + sqlex.getMessage());
            }
        }
    }
}