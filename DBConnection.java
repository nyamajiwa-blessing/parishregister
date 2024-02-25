package parishregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static parishregister.GenericMethods.errorBox;

public class DBConnection
{
    private static Connection conn;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parish_register";
//    
//    public boolean createConnection(){
//        try{
//            conn = DriverManager.getConnection(DB_URL,"root","");
//            
//            if(conn != null){
//                return true;
//            }
//            else{
//                errorBox("Failed to create database connection","ERROR","Database Connection Failed");
//                return false;
//            }
//            
//        }
//        catch(Exception ex){
//            errorBox("Failed to create database connection","ERROR","Database Connection Failed");
//            return false;
//        }
//    }
    
    public static Connection getConnection()
    {
        
        try
        {
            conn = DriverManager.getConnection(DB_URL,"root","");
            // infoBox("Connection Established", "Success", "Database Connection");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            if(e.getErrorCode() == 0)
            {
                
            }
            return null;
        }
        return conn;
    }
}
