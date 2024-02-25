package parishregister;

import java.sql.Connection;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class GenericMethods 
{
    public static void errorBox(String infoMessage, String headerText, String title)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    public static void dbDisconnect()
    {
        Connection conn = DBConnection.getConnection();
        try
        {
            if((conn != null) &&(!conn.isClosed()))
            {
                conn.close();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
//    public static void validateInt(TextField txtfld)
//    {
//        txtfld.textProperty().addListener(new ChangeListener<String>()
//        {
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
//            {
//                if(!newValue.matches("\\d*"))
//                {
//                    txtfld.setText(newValue.replaceAll("[^\\d]", ""));
//                }
//            }
//        });
//    }
    
    public static void warningBox(String infoMessage, String headerText, String title)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    public static void infoBox(String infoMessage, String headerText, String title)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    public static void confirmBox(String infoMessage, String headerText, String title)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
