
package storyteller.utils;

import javafx.scene.control.Alert;

public class Utils {
    public static void showSimpleAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert (type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
