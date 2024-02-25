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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static parishregister.GenericMethods.errorBox;


public class SearchController implements Initializable 
{
    @FXML
    private RadioButton rdbBaptism;
    @FXML
    private ToggleGroup searchTarget;
    @FXML
    private RadioButton rdbMarriage;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnClear;
    @FXML
    private TableView<Search> tblDisplay;
    @FXML
    private TableColumn<Search, String> colFullName;
    @FXML
    private TableColumn<Search, String> colLocation;
    @FXML
    private TableColumn<Search, String> colDate;
    @FXML
    private RadioButton rdbDeaths;
    @FXML
    private Button btnDashboard;
    
    private Parent root;
    private Stage stage;
    private Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        rdbBaptism.setToggleGroup(searchTarget);
        rdbDeaths.setToggleGroup(searchTarget);
        rdbMarriage.setToggleGroup(searchTarget);
    }
    
    

    public void search(String ref, String typ)
    {
	String custQuery = "";

	if(typ.equals("Baptism")){
		custQuery = 
			"SELECT full_name as fullname, baptism_location as location, birth_date as date "
			+ "FROM parish_register.baptism WHERE full_name like '%"+ref+"%'";
	}
	else if(typ.equals("Deaths")){
		custQuery = 
			"SELECT concat(first, ' ', surname) as fullname, '-' as location, ddate as date "
			+ "FROM parish_register.deaths WHERE concat_ws(first, surname) like '%"+ref+"%'";
	}
	else if(typ.equals("Marriage")){
		custQuery = 
			" SELECT concat(bride, ' - ', grooms_name) as fullname, place as location, married_date as date "
			+ "FROM parish_register.marriage WHERE concat_ws(bride, grooms_name) like '%"+ref+"%'";
	}

	loadTableData(custQuery);
    }
    
    private void loadTableData(String query)
    {
        System.out.println(query);
        
        Connection conn = DBConnection.getConnection();
        ObservableList<Search> list = FXCollections.observableArrayList();
        
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        try
        {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                list.add(new Search(rs.getString("fullname"), rs.getString("location"), rs.getString("date")));
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
    private void initSearch(ActionEvent event) 
    {
        String userInput = txtSearch.getText();
        String targetMarriage = rdbMarriage.getText();
        String targetDeaths = rdbDeaths.getText();
        String targetBaptism = rdbBaptism.getText();
        
        if(rdbMarriage.isSelected())
        {
            search(userInput, targetMarriage);
        }
        else if(rdbDeaths.isSelected())
        {
            search(userInput, targetDeaths);
        }
        else if(rdbBaptism.isSelected())
        {
            search(userInput, targetBaptism);
        }
        else
        {
            errorBox("Unable to process request","ERROR","System Error");
        }
    }

    @FXML
    private void clearTextBox(ActionEvent event) 
    {
        txtSearch.setText("");
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
