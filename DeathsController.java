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

public class DeathsController implements Initializable 
{
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtNextOfKin;
    private DatePicker dtpDeathDate;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDashboard;
    @FXML
    private TableView<Deaths> tblDisplay;
    @FXML
    private TableColumn<Deaths, Integer> colID;
    @FXML
    private TableColumn<Deaths, String> colFirstname;
    @FXML
    private TableColumn<Deaths, String> colSurname;
    @FXML
    private TableColumn<Deaths, String> colAge;
    @FXML
    private TableColumn<Deaths, String> colNextOfKin;
    @FXML
    private TableColumn<Deaths, String> colDeathDate;
    @FXML
    private TableColumn<Deaths, String> colBurialDate;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField txtDeathDate;
    @FXML
    private TextField txtBurialDate;

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
        ObservableList<Deaths> list = FXCollections.observableArrayList();
        
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colNextOfKin.setCellValueFactory(new PropertyValueFactory<>("nextOfKin"));
        colDeathDate.setCellValueFactory(new PropertyValueFactory<>("deathDate"));
        colBurialDate.setCellValueFactory(new PropertyValueFactory<>("burialDate"));
        
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM deaths");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                list.add(new Deaths(rs.getInt("id"), rs.getString("first"), rs.getString("surname"), rs.getString("age"), rs.getString("next"),
                rs.getString("ddate"), rs.getString("bdate")));
            }
            
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
        if(txtFirstName.equals("") || txtSurname.equals("") || txtAge.equals("") || txtNextOfKin.equals(""))
        {
            warningBox("Please fill in all fields", "WARNING", "Incomplete Details");
        }
        else
        {
            Connection conn = DBConnection.getConnection();
            try
            {
                String sql = "INSERT INTO deaths(first,surname,age,next,ddate,bdate) VALUES(?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1, txtFirstName.getText());
                ps.setString(2, txtSurname.getText());
                ps.setString(3, txtAge.getText());
                ps.setString(4, txtNextOfKin.getText());
                ps.setString(5, txtDeathDate.getText());
                ps.setString(6, txtDeathDate.getText());
                
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
}
