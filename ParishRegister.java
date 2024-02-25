package parishregister;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ParishRegister extends Application 
{
    @Override
    public void start(Stage loginWindow) throws Exception 
    {
//        checkConnection();
        Parent root = FXMLLoader.load(getClass().getResource("design/ParishRegisterLogin.fxml"));
        
        Scene scene = new Scene(root);
        
        loginWindow.setScene(scene);
        loginWindow.setTitle("Parish Register | Login");
        loginWindow.setResizable(false);
//        loginWindow.getIcons().add(new Image("bible.png"));
        loginWindow.show();
        loginWindow.centerOnScreen();
    }
    
//    private void checkConnection()
//    {    
//        try
//        {
//            Connection conn = DBConnection.getConnection();
//            infoBox("Connection to database has been established", "SUCCESS", "Database Connection");
//            conn.close();
//        }
//        catch (SQLException ex) 
//        {
//            String errMsg = ex.toString();
//            errorBox(errMsg, "ERROR", "Database failure");
//        }
//    }
    
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
