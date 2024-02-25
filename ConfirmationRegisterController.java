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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static parishregister.GenericMethods.infoBox;
import static parishregister.GenericMethods.warningBox;

public class ConfirmationRegisterController implements Initializable {

    @FXML
    private Label lblHeading;
    @FXML
    private Label lblFirstName;
    @FXML
    private TextField txtFirstName;
    @FXML
    private Label lblSurname;
    @FXML
    private TextField txtSurname;
    @FXML
    private Label lblFathers;
    @FXML
    private TextField txtFathers;
    @FXML
    private Label lblMothers;
    @FXML
    private TextField txtMothers;
    @FXML
    private Label lblPlace;
    @FXML
    private TextField txtPlace;
    @FXML
    private Label lblDate;
    private DatePicker dtpDate;
    @FXML
    private Label lblSponsor;
    @FXML
    private TextField txtSponsor;
    @FXML
    private Label lblLocation;
    @FXML
    private TextField txtBaptismLocation;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDashboard;
    @FXML
    private TableView<Confirmation> tblDisplay;
    @FXML
    private TableColumn<Confirmation, String> colFirstName;
    @FXML
    private TableColumn<Confirmation, String> colSurname;
    @FXML
    private TableColumn<Confirmation, String> colFathersName;
    @FXML
    private TableColumn<Confirmation, String> colMothersName;
    @FXML
    private TableColumn<Confirmation, String> colPlace;
    @FXML
    private TableColumn<Confirmation, String> colDate;
    @FXML
    private TableColumn<Confirmation, String> colSponsor;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TableColumn<Confirmation, String> colBaptismLocation;
    @FXML
    private TextField txtDate;
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
        ObservableList<Confirmation> list = FXCollections.observableArrayList();
        
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        colFathersName.setCellValueFactory(new PropertyValueFactory<>("fathersName"));
        colMothersName.setCellValueFactory(new PropertyValueFactory<>("mothersName"));
        colPlace.setCellValueFactory(new PropertyValueFactory<>("place"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        colSponsor.setCellValueFactory(new PropertyValueFactory<>("sponsor"));
        colBaptismLocation.setCellValueFactory(new PropertyValueFactory<>("baptismLocation"));
        
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM confirmation_register");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                // System.out.println(rs.getString("fname"));
                list.add(new Confirmation(rs.getString("fname"), rs.getString("surname"), rs.getString("mothers_name"), rs.getString("fathers_name"),
                rs.getString("place"), rs.getString("reg_date"), rs.getString("sponsor"), rs.getString("baptism_location")));
            }
            
            // Setting table data
            tblDisplay.setItems(list);
//            for(int i = 0; i < list.size(); i++)
//            {
//                System.out.println(list);
//            }
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
   
        if(txtFirstName.getText().equals("") || txtSurname.getText().equals("") || txtFathers.getText().equals("") || txtMothers.getText().equals("") || txtPlace.getText().equals("") ||
                txtDate.equals("") || txtSponsor.getText().equals("") || txtBaptismLocation.getText().equals(""))
        {
            warningBox("Please fill in all fields", "WARNING", "Incomplete Details");
        }
        else
        {
//            String firstName, surname, fathersName, mothersName, place, sponsor, baptismLocation, regDate;
//            firstName = txtFirstName.getText();
//            surname = txtSurname.getText();
//            fathersName = txtFathers.getText();
//            mothersName = txtMothers.getText();
//            place = txtPlace.getText();
//            sponsor = txtSponsor.getText();
//            baptismLocation = txtBaptismLocation.getText();
//            regDate = dtpDate.toString();
            
            Connection conn = DBConnection.getConnection();
            try
            {
                String sql = "INSERT INTO confirmation_register VALUES(?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
//                ps.setInt(1, 0);
                ps.setString(1, txtFirstName.getText());
                ps.setString(2, txtSurname.getText());
                ps.setString(3, txtMothers.getText());
                ps.setString(4, txtFathers.getText());
                ps.setString(5, txtPlace.getText());
                ps.setString(6, txtDate.getText());
                ps.setString(7, txtSponsor.getText());
                ps.setString(8, txtBaptismLocation.getText());
                
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
    private void clearTextBoxes(ActionEvent event) 
    {
        txtFirstName.setText("");
        txtSurname.setText("");
        txtFathers.setText("");
        txtMothers.setText("");
        txtPlace.setText("");
        txtSponsor.setText("");
        txtBaptismLocation.setText("");
        txtDate.setText("");
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
