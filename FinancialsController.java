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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static parishregister.GenericMethods.infoBox;
import static parishregister.GenericMethods.warningBox;


public class FinancialsController implements Initializable 
{

    @FXML
    private TableColumn<Financials, Integer> colID;
    @FXML
    private TableColumn<Financials, String> colFullName;
    @FXML
    private TableColumn<Financials, String> colAmountPaid;
    @FXML
    private TableColumn<Financials, String> colMonth;
    @FXML
    private TableColumn<Financials, String> colWeek;
    @FXML
    private TextField txtFullName;
    @FXML
    private TextField txtAmountPaid;
    @FXML
    private TextField txtMonth;
    @FXML
    private TextField txtWeek;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDashboard;
    @FXML
    private TableView<Financials> tblDisplay;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadTableData();
    }    
    
    private void loadTableData()
    {
        Connection conn = DBConnection.getConnection();
        ObservableList<Financials> list = FXCollections.observableArrayList();
        
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colAmountPaid.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colWeek.setCellValueFactory(new PropertyValueFactory<>("week"));
        
        
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM financials");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                list.add(new Financials(rs.getInt("id"), rs.getString("full_name"), rs.getString("amount"), rs.getString("month"), rs.getString("week")));
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
        if(txtFullName.equals("") || txtAmountPaid.equals("") || txtWeek.equals("") || txtMonth.equals(""))
        {
            warningBox("Please fill in all fields","WARNING","Incomplete fields");
        }
        
        else
        {
            try
            {
                Connection conn = DBConnection.getConnection();
                String sql = "INSERT INTO financials(full_name, amount, month, week) VALUES(?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1, txtFullName.getText());
                ps.setString(2, txtAmountPaid.getText());
                ps.setString(3, txtMonth.getText());
                ps.setString(4, txtWeek.getText());
                
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
    private void clear(ActionEvent event) 
    {
        txtFullName.setText("");
        txtAmountPaid.setText("");
        txtMonth.setText("");
        txtWeek.setText("");
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


