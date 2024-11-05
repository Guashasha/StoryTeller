package storyteller.controller;

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
        this.stage = stage;
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
                break;

            case 2:
                translatedTale = tale.getSpanishText();
                break;

            default:
                return;
        }

        contentTxt.setText(translatedTale);
    }

    @FXML
    private void answerQuestion() {
        String correctAnswer = tale.getQuestion().getQuestionAnswer().getEnglishWord();
        String userAnswer = answerTf.getText().trim();

        if (correctAnswer.equals(userAnswer)) {
            Utils.showSimpleAlert("Felicidades", "Respuesta correcta, has completado el cuento :D", Alert.AlertType.CONFIRMATION);
            setTaleCompleted(tale.getId());
            goBack();
        }
        else {
            Utils.showSimpleAlert("incorrecto", "La repuesta no es correcta, intenta nuevamente.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void goBack() {
        System.out.println("hola mundo");
    }

    @FXML
    private void btnClickSpanish(ActionEvent event) {
        translateTale(2);
    }

    @FXML
    private void btnClickEnglish(ActionEvent event) {
        translateTale(1);
    }
    
    public static HashMap<String, Object> setTaleCompleted(int idTale){
        HashMap<String, Object> response = new LinkedHashMap<>();
        response.put("error", true);
        Connection conexionBD = ConnectionDB.obtainConnection();
        if(conexionBD != null){
            try {
                String query = "UPDATE cuento SET completado = 1 WHERE id = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
                preparedStatement.setInt(1, idTale);
                int rowsAffected = preparedStatement.executeUpdate();
                
                if(rowsAffected > 0){
                    response.put("error", false);
                    response.put("message", "Tale completed");
                }else{
                    response.put("message", "Something happened while trying to mark the tale as completed.");
                }
                conexionBD.close();
            } catch (SQLException sqlex) {
                response.put("message", "Error: " + sqlex.getMessage());
            }
        }else{
            response.put("message", "Por el momento este cuento no se encuentra disponible, inténtalo de nuevo más tarde. ¡Puedes leer otro cuento mientras esperas!");
        }
        return response;
    }
}