/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
public class RegistrationController {

    private List<Registration> registration = new ArrayList<>();
    private Registration thisRegistration = new Registration();

    public RegistrationController() {
        thisRegistration = new Registration();
        refreshR();

    }

    public void refreshR() {
        try {
            registration = new ArrayList<>();
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM registration");
            while (rs.next()) {
                Registration r = new Registration();
                r.setName(rs.getString("Name"));
                r.setPassword(rs.getString("Password"));
                r.setEmailAddress(rs.getString("Email"));
                r.setAge(rs.getInt("Age"));
                r.setContactNumber(rs.getInt("ContactNumber"));
                r.setHomeAddress(rs.getString("HomeAddress"));
                r.setZip(rs.getInt("Zip"));
                r.setState(rs.getString("State"));
                                                        
                registration.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Registration> getRegistration() {
        return registration;
    }

    public void setRegistration(List<Registration> registration) {
        this.registration = registration;
    }

    public Registration getThisRegistration() {
        return thisRegistration;
    }

    public void setThisRegistration(Registration thisRegistration) {
        this.thisRegistration = thisRegistration;
    }
    
    
    
     public String add(String name, String password, String emailAddress,int age,int contactNumber, String homeAddress, int zip, String state) {
        try {
             thisRegistration = new Registration();
            Connection conn = Database.getConnection();
            String sql = "INSERT INTO registration(Name, Password,Email,Age,ContactNumber,HomeAddress,Zip,State) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setString(3, emailAddress);
            pstmt.setInt(4, age);
            pstmt.setInt(5, contactNumber);
            pstmt.setString(6, homeAddress);
            pstmt.setInt(7, zip);
            pstmt.setString(8,state);
            
            pstmt.executeUpdate();
            registration.add(thisRegistration);
            thisRegistration = new Registration();
            refreshR();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }

}
