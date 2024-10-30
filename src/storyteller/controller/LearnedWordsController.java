/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package storyteller.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class LearnedWordsController implements Initializable {

    @FXML
    private Button goBackBtn;
    @FXML
    private Text titleTxt;
    @FXML
    private TableView<?> tvWordsLearned;
    @FXML
    private TableColumn<?, ?> colSpanish;
    @FXML
    private TableColumn<?, ?> colEnglish;
    @FXML
    private Text titleTxt1;
    @FXML
    private Text titleTxt11;
    @FXML
    private Text titleTxt12;
    @FXML
    private Label lbWordsLearned;
    @FXML
    private Label lbStoriesRead;
    @FXML
    private Label lbTimeReading;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnClickGoBack(ActionEvent event) {
    }
    
}
