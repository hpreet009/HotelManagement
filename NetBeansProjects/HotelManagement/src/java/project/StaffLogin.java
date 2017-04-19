/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author c0688638
 */
@Named
@ApplicationScoped
public class StaffLogin {
     private int id;
    private String username;
    private String password;
    private boolean LoggedIn=true;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return LoggedIn;
    }

    public void setLoggedIn(boolean LoggedIn) {
        this.LoggedIn = LoggedIn;
    }
    
    
    public String click() throws SQLException {
        
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM staff");
            while (rs.next()) {
                if (username.equals(rs.getString("Username")) && password.equals(rs.getString("Password"))) {
                   LoggedIn = true;
                    return "staff";
                    
                }
                else if(username.equals("") && password.equals("")){
                    LoggedIn = false;
                    
                }
                else if (!username.equals(rs.getString("Username")) && !password.equals(rs.getString("Password"))) {
                   LoggedIn = false;;
                   
               }
            
        } 
        return null;
    }
    
    
}
