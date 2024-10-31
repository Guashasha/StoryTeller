package storyteller.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
                translatedTale = tale.getSpanishText().replace(englishWord, spanishWord);
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
}