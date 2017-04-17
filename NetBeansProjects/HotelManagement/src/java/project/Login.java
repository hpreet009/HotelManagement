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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author c0688638
 */
@Named
@ApplicationScoped
public class Login {

    private String name;
    private String password;
    private boolean LoggedIn = true  ;
/**
 * Set the name
 * @return the name
 */
    public String getName() {
        return name;
    }
/**
 * Set the name
 * @param name the name
 */
    public void setName(String name) {
        this.name = name;
    }
/**
 * Set the password
 * @return the password
 */
    public String getPassword() {
        return password;
    }
/**
 * Set the password
 * @param password the password
 */
    public void setPassword(String password) {
        this.password = password;
    }
/**
 * Set the LoggedIn
 * @return  the LoggedIn
 */
    public boolean isLoggedIn() {
        return LoggedIn;
    }
/**
 * Set the LoggedIn
 * @param LoggedIn the LoggedIn
 */
    public void setLoggedIn(boolean LoggedIn) {
        this.LoggedIn = LoggedIn;
    }
    
    public String go() throws SQLException {
        
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM registration");
            while (rs.next()) {
                if (name.equals(rs.getString("Name")) && password.equals(rs.getString("Password"))) {
                   LoggedIn = true;
                    return "reservation";
                    
                }else if(name.equals("") && password.equals("")){
                    LoggedIn = false;
                    //return "index";
                }
                else if (!name.equals(rs.getString("Name")) && !password.equals(rs.getString("Password"))) {
                   LoggedIn = false;
                  // return "index";
                }
            
        } 
        return null;
    }
    }

