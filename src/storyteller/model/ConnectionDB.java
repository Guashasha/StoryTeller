package storyteller.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import storyteller.utils.Utils;

public class ConnectionDB {
    
    public static final String URI_CONEXION = "jdbc:mysql://"+
            "localhost" + ":" + "3306" + "/" +
            "storyteller" + "?allowPublicKeyRetrieval=true&useSSL=false";
    
    public static Connection obtainConnection(){
        Connection ConexionBD = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //Define user and password as String
            ConexionBD = DriverManager.getConnection(URI_CONEXION, "storyteller_admin", "ST_24_aps");
        } catch (ClassNotFoundException ex1){
            Utils.showSimpleAlert("Error Inesperado",
                    "Un error inesperado a ocurrido, por favor repórtalo "
                            + "de inmediato.\n"+ex1.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (SQLException ex2){
            Utils.showSimpleAlert("Error Inesperado",
                    "Un error inesperado a ocurrido, por favor repórtalo "
                            + "de inmediato.\n"+ex2.getMessage(),
                    Alert.AlertType.ERROR);
        }
        
        return ConexionBD;
    }
}
