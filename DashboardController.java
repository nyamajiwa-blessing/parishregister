package parishregister;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import static parishregister.GenericMethods.confirmBox;


public class DashboardController implements Initializable 
{
    @FXML
    private Button btnConfirmationReg;
    @FXML
    private Button btnMembership;
    @FXML
    private Button btnMarriage;
    @FXML
    private Button btnBaptism;
    @FXML
    private Button btnDeaths;
    @FXML
    private Button btnLogout;
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Button btnFinancials;
    @FXML
    private Button btnSearch;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    

    @FXML
    private void openFinancials(ActionEvent user) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("design/Financials.fxml"));
        root = loader.load();
        
        // LoginController loginController = loader.getController();
        stage = (Stage)((Node)user.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Parish Register | Add New User");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    private void confirmRegistration(ActionEvent register) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("design/ConfirmationRegister.fxml"));
        root = loader.load();
        
        // LoginController loginController = loader.getController();
        stage = (Stage)((Node)register.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Parish Register | Confirmation Register");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    private void checkMembers(ActionEvent members) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("design/Membership.fxml"));
        root = loader.load();
        
        // LoginController loginController = loader.getController();
        stage = (Stage)((Node)members.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Parish Register | Membership");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    private void viewMarriages(ActionEvent marriage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("design/Marriage.fxml"));
        root = loader.load();
        
        // LoginController loginController = loader.getController();
        stage = (Stage)((Node)marriage.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Parish Register | Marriage Register");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    private void viewBaptism(ActionEvent baptism) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("design/Baptism.fxml"));
        root = loader.load();
        
        // LoginController loginController = loader.getController();
        stage = (Stage)((Node)baptism.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Parish Register | Baptism");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    private void viewDeaths(ActionEvent deaths) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("design/Deaths.fxml"));
        root = loader.load();
        
        // LoginController loginController = loader.getController();
        stage = (Stage)((Node)deaths.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Parish Register | Deaths");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    private void logout(ActionEvent signoff) throws IOException 
    {
        confirmBox("We hate to see you go...", "EXIT APP?", "Confirm logout");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("design/ParishRegisterLogin.fxml"));
        root = loader.load();
        
        // LoginController loginController = loader.getController();
        stage = (Stage)((Node)signoff.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Parish Register | Admin Dashboard");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    private void searchSpecific(ActionEvent search) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("design/Search.fxml"));
        root = loader.load();
        
        // LoginController loginController = loader.getController();
        stage = (Stage)((Node)search.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Parish Register | Search");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
}
