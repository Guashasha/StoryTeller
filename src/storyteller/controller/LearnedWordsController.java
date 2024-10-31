package storyteller.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import storyteller.model.ConnectionDB;
import storyteller.model.pojo.Question;
import storyteller.utils.Utils;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public class LearnedWordsController implements Initializable {

    @FXML
    private Button goBackBtn;
    @FXML
    private Text titleTxt;
    @FXML
    private TableView<Question> tvWordsLearned;
    @FXML
    private TableColumn<Question, String> colSpanish;
    @FXML
    private TableColumn<Question, String> colEnglish;

    private ObservableList<Question> questionsList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colSpanish.setCellValueFactory(new PropertyValueFactory<>("spanishQuestion"));
        colEnglish.setCellValueFactory(new PropertyValueFactory<>("englishQuestion"));

        questionsList = FXCollections.observableArrayList();

        loadAnsweredQuestions();
        tvWordsLearned.setItems(questionsList);
    }

    private void loadAnsweredQuestions() {
        Connection conexionBD = ConnectionDB.obtainConnection();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT pregunta_espanol, pregunta_ingles FROM pregunta WHERE contestada = 1";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet result = prepararSentencia.executeQuery();

                ArrayList<Question> questions = new ArrayList<>();

                while (result.next()) {
                    Question question = new Question();
                    question.setSpanishQuestion(result.getString("pregunta_espanol"));
                    question.setEnglishQuestion(result.getString("pregunta_ingles"));
                    questions.add(question);
                }

                questionsList.addAll(questions);
                conexionBD.close();

            } catch (SQLException sqlex) {
                Utils.showSimpleAlert("Error al Cargar Preguntas",
                        "Ocurrió un error al cargar las preguntas contestadas:\n" + sqlex.getMessage(),
                        Alert.AlertType.ERROR);
            }
        } else {
            Utils.showSimpleAlert("Error de Conexión",
                    "No se pudo conectar con la base de datos. Por favor, intenta de nuevo más tarde.",
                    Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void btnClickGoBack(ActionEvent event) {
        Stage stage = (Stage) titleTxt.getScene().getWindow();
        stage.close();
    }
}
