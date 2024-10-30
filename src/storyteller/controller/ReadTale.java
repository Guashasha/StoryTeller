package storyteller.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import storyteller.model.ConnectionDB;
import storyteller.model.pojo.Tale;

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
    @FXML
    private Button goBackBtn;

    public void initialize(Tale tale) {
        this.stage = stage;
        this.titleTxt.setText(tale.getTitle());
        this.contentTxt.setText(tale.getSpanishText());
        this.questionTxt.setText(tale.getQuestion().getSpanishQuestion());
        String randomSpanishWord = obtainWord(tale.getSpanishText());
        loadEnglishWords(randomSpanishWord, translateWord(randomSpanishWord), tale.getSpanishText());
    }
    
    private void loadEnglishWords(String esWord, String enWord, String tale){
        tale.replace(esWord, enWord);
        contentTxt.setText(tale);
        //TO DO set stage so question uses this word and its correct translation
    }
    
    private String obtainWord(String tale){
        String[] words = tale.split("\\s+");
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        String esWord = words[randomIndex];
        return esWord;
    }
    
    private String translateWord(String esWord){
        //TO DO Translation Function Implementation
        String enWord = esWord;
        return enWord;
    }

    private void goBack() {
        System.out.println("hola mundo");
    }

    @FXML
    private void btnClickGoBack(ActionEvent event) {
    }

    @FXML
    private void btnClickAccept(ActionEvent event) {
    }

    @FXML
    private void btnClickSpanish(ActionEvent event) {
    }

    @FXML
    private void btnClickEnglish(ActionEvent event) {
    }
    
}
