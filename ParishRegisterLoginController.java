package parishregister;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import static parishregister.GenericMethods.errorBox;
import static parishregister.GenericMethods.infoBox;

public class ParishRegisterLoginController implements Initializable 
{   
    @FXML
    private ImageView imgLogo;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblHeader;
    @FXML
    private Label lblErrorMessage;
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    
    private static Connection conn;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parish_register";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        boolean status = createConnection();
        if(status)
        {
            infoBox("Database connection created successfully", "INFORMATION", "Connection Status");
        }
        else
        {
            errorBox("Failed to create database connection","ERROR","Database Connection Failed");
            System.exit(0);
        }
    }
    
    
    
    public boolean createConnection(){
        try{
            conn = DriverManager.getConnection(DB_URL,"root","");
            
            if(conn != null){
                return true;
            }
            else{
//                errorBox("Failed to create database connection","ERROR","Database Connection Failed");
                return false;
            }
            
        }
        catch(Exception ex){
//            errorBox("Failed to create database connection","ERROR","Database Connection Failed");
            return false;
        }
    }


    @FXML
    private void login(ActionEvent signin) throws IOException
    {
        String userName = txtUsername.getText();
        String passWord = pwdPassword.getText();
        
        if(userName.equals("") || passWord.equals(""))
        {
            lblErrorMessage.setVisible(true);
        }
        else
        {
            try
            {
                Connection conn = DBConnection.getConnection();
                String sql = "SELECT * FROM users WHERE uname = ? AND pword = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1, userName);
                ps.setString(2, passWord);
                
                ResultSet rs = ps.executeQuery();
                
                if(rs.next())
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("design/Dashboard.fxml"));
                    root = loader.load();
        
                    // LoginController loginController = loader.getController();
                    stage = (Stage)((Node)signin.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setTitle("Parish Register | Login");
                    stage.setScene(scene);
                    stage.show();
                    stage.centerOnScreen();
                }
                else
                {
                    errorBox("User not found. Please verify your login credentials", "ERROR", "Login failure");
                }
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
