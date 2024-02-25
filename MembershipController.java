package parishregister;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.event.ChangeListener;
import static parishregister.GenericMethods.infoBox;
import static parishregister.GenericMethods.warningBox;

/**
 * FXML Controller class
 *
 * @author Munyaradzi Nyamajiwa
 */
public class MembershipController implements Initializable 
{

    @FXML
    private TextField txtDiocese;
    @FXML
    private TextField txtParish;
    @FXML
    private TextField txtPlace;
    @FXML
    private TextField txtHome;
    @FXML
    private TextField txtSpouse;
    @FXML
    private TextField txtChildren;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDashboard;
    @FXML
    private TableView<Membership> tblDisplay;
    @FXML
    private TableColumn<Membership, Integer> colID;
    @FXML
    private TableColumn<Membership, String> colDiocese;
    @FXML
    private TableColumn<Membership, String> colParish;
    @FXML
    private TableColumn<Membership, String> colPlace;
    @FXML
    private TableColumn<Membership, String> colHome;
    @FXML
    private TableColumn<Membership, String> colSpouse;
    @FXML
    private TableColumn<Membership, String> colBaptised;
    @FXML
    private TableColumn<Membership, String> colMarried;
    @FXML
    private TableColumn<Membership, String> colChildren;
    
    // private ObservableList data;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField txtBaptised;
    @FXML
    private TextField txtMarried;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadTableData();
    }    

    @FXML
    private void saveDetails(ActionEvent event) 
    {
        if(txtDiocese.getText().equals("") || txtParish.getText().equals("") || txtPlace.getText().equals("") || txtHome.getText().equals("") || 
                txtSpouse.getText().equals("")||
                txtChildren.getText().equals(""))
        {
            warningBox("Please fill in all fields","WARNING","Incomplete fields");
        }
        else
        {
//            String diocese = txtDiocese.getText();
//            String parish = txtParish.getText();
//            String place = txtPlace.getText();
//            String home = txtHome.getText();
//            String spouse = txtSpouse.getText();
//            String marital = married.getSelectedToggle().toString();
//            String baptism = baptised.getSelectedToggle().toString();
//            String kids = txtChildren.getText();
            try 
            {
                Connection conn = DBConnection.getConnection();
                String sql = "INSERT INTO membership(diocese, parish, place, home, spouse, baptised, married, children) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, txtDiocese.getText());
                ps.setString(2, txtParish.getText());
                ps.setString(3, txtPlace.getText());
                ps.setString(4, txtHome.getText());
                ps.setString(5, txtSpouse.getText());
                ps.setString(6, txtBaptised.getText());
                ps.setString(7, txtMarried.getText());
                ps.setString(8, txtChildren.getText());
                
                ps.execute();
                infoBox("Registration entry has been updated successful", "SUCCESS", "Confrimation");
                loadTableData();
            } 
            
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void clearDetails(ActionEvent event) 
    {
        txtDiocese.setText("");
        txtParish.setText("");
        txtPlace.setText("");
        txtHome.setText("");
        txtChildren.setText("");
        txtSpouse.setText("");
        txtMarried.setText("");
        txtBaptised.setText("");
    }

    @FXML
    private void backToDash(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("design/Dashboard.fxml"));
        root = loader.load();
        
        // LoginController loginController = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("EquipTrack | Admin Dashboard");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
    
    private void loadTableData()
    {
        Connection conn = DBConnection.getConnection();
        ObservableList<Membership> list = FXCollections.observableArrayList();
        
        // Setting cell value factories to populate table with database query result set
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDiocese.setCellValueFactory(new PropertyValueFactory<>("diocese"));
        colParish.setCellValueFactory(new PropertyValueFactory<>("parish"));
        colPlace.setCellValueFactory(new PropertyValueFactory<>("place"));
        colHome.setCellValueFactory(new PropertyValueFactory<>("home"));
        colSpouse.setCellValueFactory(new PropertyValueFactory<>("spouse"));
        colBaptised.setCellValueFactory(new PropertyValueFactory<>("baptised"));
        colMarried.setCellValueFactory(new PropertyValueFactory<>("married"));
        colChildren.setCellValueFactory(new PropertyValueFactory<>("numberOfChildren"));
        
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM membership");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                list.add(new Membership(rs.getInt("id"), rs.getString("diocese"), rs.getString("parish"), rs.getString("place"), rs.getString("home"),
                rs.getString("spouse"), rs.getString("baptised"), rs.getString("married"), rs.getString("children")));
            }
            
            // Setting table data
            tblDisplay.setItems(list);
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
