import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader guiLoader = new FXMLLoader(getClass().getResource("storyteller/view/Start.fxml"));

            Parent root = guiLoader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Storyteller");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }
}