/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author c0687969
 */
public class Database {
     public static Connection getConnection() throws SQLException {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        String server = "ipro.lambton.on.ca";
        String username =  "";
        String password = "";
        String database = username;
        String jdbc = String.format("jdbc:derby://localhost:1527/HotelManagement [ on APP]", server, database);
        return DriverManager.getConnection(jdbc, username, password);
    }
    
}
