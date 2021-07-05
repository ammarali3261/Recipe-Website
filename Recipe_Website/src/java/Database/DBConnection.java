package Database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *This class manages the connection of the server to the mySQL Database. It 
 * contains the necessary methods for achieving a successful connection.
* 
 * 
 * @author Ammar Ali Moazzam
 */
public class DBConnection {
    
    private static Connection conn;
    //gives the url of the schema in Database
    private static final String URL = "jdbc:mysql://localhost:3306/recipe_website";
    
    //gives the path for the mysql driver
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //username for the database
    private static final String USER = "username";
    //password for the database
    private static final String PASS = "password";
    
    
    //connection method that makes the connection to the database
    public static Connection connect() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch(ClassNotFoundException cnfe){
            System.err.println("ERROR: " + cnfe.getMessage());
        }
        catch(InstantiationException ie){
            System.err.println("ERROR: " + ie.getMessage());
        }
        catch(IllegalAccessException iae){
            System.err.println("ERROR: " + iae.getMessage());
        }
        
        conn = DriverManager.getConnection(URL, USER, PASS);
        return conn;
    }
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(conn != null && !conn.isClosed())
            return conn;
        connect();
        return conn;
    }
}
