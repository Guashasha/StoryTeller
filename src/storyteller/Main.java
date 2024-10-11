package storyteller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader guiLoader = new FXMLLoader(getClass().getResource("storyteller/view/Start.fxml"));

        try {
            Parent root = guiLoader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Storyteller");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }
}
