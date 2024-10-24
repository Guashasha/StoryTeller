package storyteller.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import storyteller.model.Tale;

import java.util.ArrayList;

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
        tales = (ArrayList<Tale>)TalesDAO.getTales().get("tales");
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
        // TODO
    }
}
