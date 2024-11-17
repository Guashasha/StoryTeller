import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import storyteller.controller.StartController;

public class Storyteller extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("storyteller/view/Start.fxml"));
            Parent root = loader.load();

            StartController controller = loader.getController();
            controller.setStage(stage);

            Scene scene = new Scene(root);
            stage.setTitle("Storyteller");
            stage.setScene(scene);
            stage.show();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
