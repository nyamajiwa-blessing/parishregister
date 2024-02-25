package parishregister;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class BaptismController implements Initializable 
{
    @FXML
    private TextField txtFullName;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDashboard;
    @FXML
    private TableView<Baptism> tblDisplay;
    @FXML
    private TableColumn<Baptism, Integer> colID;
    @FXML
    private TableColumn<Baptism, String> colFullName;
    @FXML
    private TableColumn<Baptism, String> colDateOfBirth;
    @FXML
    private TableColumn<Baptism, String> colPlaceOfResidence;
    @FXML
    private TableColumn<Baptism, String> colBaptismLocation;
    @FXML
    private TableColumn<Baptism, String> colBaptiserName;
    @FXML
    private TableColumn<Baptism, String> colMarriedTo;
    
    @FXML
    private TextField txtResidence;
    @FXML
    private TextField txtLocation;
    private TextField txtBaptised;
    @FXML
    private TextField txtMarried;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField txtBaptisedBy;
    @FXML
    private TextField txtDateOfBirth;
    
    public String id = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadTableData();
    }

    private void loadTableData()
    {
        Connection conn = DBConnection.getConnection();
        ObservableList<Baptism> list = FXCollections.observableArrayList();
        
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        colPlaceOfResidence.setCellValueFactory(new PropertyValueFactory<>("place"));
        colBaptismLocation.setCellValueFactory(new PropertyValueFactory<>("baptismLocation"));
        colBaptiserName.setCellValueFactory(new PropertyValueFactory<>("baptisor"));
        colMarriedTo.setCellValueFactory(new PropertyValueFactory<>("marriedTo"));
        
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM baptism");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                list.add(new Baptism(rs.getInt("id"), rs.getString("full_name"), rs.getString("birth_date"), 
                        rs.getString("residence"), rs.getString("baptism_location"),
                rs.getString("baptiser"), rs.getString("married_to")));
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

    @FXML
    private void saveDetails(ActionEvent event) 
    {
        Connection conn = DBConnection.getConnection();
        
    }

    @FXML
    private void clearDetails(ActionEvent event) 
    {
        txtFullName.setText("");
        txtResidence.setText("");
        txtLocation.setText("");
        txtBaptised.setText("");
        txtMarried.setText("");
        // dtpDateOfBirth
        
    }

    @FXML
    private void back(ActionEvent event) throws IOException
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
    
}
