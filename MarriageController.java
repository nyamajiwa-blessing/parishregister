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
import static parishregister.GenericMethods.infoBox;
import static parishregister.GenericMethods.warningBox;

public class MarriageController implements Initializable 
{
    @FXML
    private TextField txtBrideName;
    @FXML
    private TextField txtGroomName;
    @FXML
    private TextField txtPlace;
    @FXML
    private TextField txtWitness1;
    @FXML
    private TextField txtWitness2;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDashboard;
    @FXML
    private TableView<Marriage> tblDisplay;
    @FXML
    private TableColumn<Marriage, Integer> colID;
    @FXML
    private TableColumn<Marriage, String> colBridesName;
    @FXML
    private TableColumn<Marriage, String> colGroomsName;
    @FXML
    private TableColumn<Marriage, String> colPlace;
    @FXML
    private TableColumn<Marriage, String> colMarried;
    @FXML
    private TableColumn<Marriage, String> colWitness1;
    @FXML
    private TableColumn<Marriage, String> colWitness2;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField txtMarriedDate;

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
        ObservableList<Marriage> list = FXCollections.observableArrayList();
        
        // Setting cell value factories to populate table with database query result set
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBridesName.setCellValueFactory(new PropertyValueFactory<>("bridesName"));
        colGroomsName.setCellValueFactory(new PropertyValueFactory<>("groomsName"));
        colPlace.setCellValueFactory(new PropertyValueFactory<>("place"));
        colMarried.setCellValueFactory(new PropertyValueFactory<>("marriedDate"));
        colWitness1.setCellValueFactory(new PropertyValueFactory<>("witnessOne"));
        colWitness2.setCellValueFactory(new PropertyValueFactory<>("witnessTwo"));
        
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM marriage");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                list.add(new Marriage(rs.getInt("id"), rs.getString("bride"), rs.getString("grooms_name"), rs.getString("place"), rs.getString("married_date"),
                rs.getString("witness1"), rs.getString("witness2")));
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
        if(txtBrideName.equals("") || txtGroomName.equals("") || txtPlace.equals("") || txtWitness1.equals("") || txtWitness2.equals("") ||
                txtMarriedDate.equals(""))
        {
            warningBox("Please fill in all fields","WARNING","Incomplete fields");
        }
        else
        {
            try
            {
                Connection conn = DBConnection.getConnection();
                String sql = "INSERT INTO marriage(bride, grooms_name, place, married_date, witness1, witness2) VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1, txtBrideName.getText());
                ps.setString(2, txtGroomName.getText());
                ps.setString(3, txtPlace.getText());
                ps.setString(4, txtWitness1.getText());
                ps.setString(5, txtWitness2.getText());
                ps.setString(6, txtMarriedDate.getText());
                
                ps.execute();
                infoBox("Registration entry has been updated successful", "SUCCESS", "Confrimation");
                loadTableData();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void clearDetails(ActionEvent event) 
    {
        txtBrideName.setText("");
        txtGroomName.setText("");
        txtPlace.setText("");
        txtWitness1.setText("");
        txtWitness2.setText("");
        txtMarriedDate.setText("");
    }

    @FXML
    private void btnDashboard(ActionEvent event) throws IOException
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
