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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import storyteller.model.pojo.Tale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import storyteller.model.ConnectionDB;
import storyteller.model.pojo.State;

public class StartController {
    @FXML
    private Button btnLearnedWords;
    @FXML
    private VBox talesVBox;

    private ArrayList<Tale> tales;
    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
        obtainTales();
        generateTaleButtons();
    }
    
    private void obtainTales(){
        tales = (ArrayList<Tale>)getTales().get("tales");
    }

    private void generateTaleButtons() {
        for (Tale tale : tales) {
            Button button = new Button(tale.getTitle());
            button.setOnAction(e -> {
                try {
                    FXMLLoader guiLoader = new FXMLLoader(getClass().getResource("ReadTale.fxml"));

                    Parent root = guiLoader.load();
                    Scene scene = new Scene(root);
                    stage.setTitle(tale.getTitle());
                    stage.setScene(scene);
                    stage.show();
                }
                catch (Exception error) {
                    error.printStackTrace();
                }
            });

            button.setStyle("");
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

    
    public static HashMap<String, Object> getTales(){
        HashMap<String, Object> response = new LinkedHashMap<>();
        response.put("error", true);
        Connection conexionBD = ConnectionDB.obtainConnection();
        ArrayList<Tale> tales = new ArrayList<>();
        if(conexionBD != null){
            try {
                //TODO sentencia sql de consulta
                String consulta = "SELECT id, titulo, texto_espanol, completado FROM cuento";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                
                ResultSet result = prepararSentencia.executeQuery();
                
                while(result.next()){
                    Tale tale = new Tale();
                    tale.setId(result.getInt("id"));
                    tale.setTitle(result.getString("titulo"));
                    tale.setSpanishText(result.getString("texto_espanol"));
                    if(result.getInt("completado") == 0){
                        tale.setState(State.PENDING);
                    }else{
                        tale.setState(State.COMPLETED);
                    }
                    
                    tales.add(tale);
                }
                response.put("error", false);
                response.put("tales", tales);
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
