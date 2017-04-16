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
                r.setCustomer_id(rs.getInt("customer_id"));
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
 public int getcustomerId(int id) {

        for (Registration v : registration) {
            if (v.getCustomer_id()== id) {
                return v.getCustomer_id();
            }
        }
        return 0;
    }
    public void setThisRegistration(Registration thisRegistration) {
        this.thisRegistration = thisRegistration;
    }
    
     public String Detail(Registration r) {
        thisRegistration = r;
        return "Account";
    }
    
    
     public String add(String name, String password, String emailaddress, int age, int contactNumber, String address, int zip, String State) {
        try {
             
            Connection conn = Database.getConnection();
            String sql = "INSERT INTO registration(Name,Password,Email,Age,ContactNumber,HomeAddress,Zip,State) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, thisRegistration.getName());
            pstmt.setString(2, thisRegistration.getPassword());
            pstmt.setString(3, thisRegistration.getEmailAddress());
            pstmt.setInt(4, thisRegistration.getAge());
            pstmt.setInt(5, thisRegistration.getContactNumber());
            pstmt.setString(6, thisRegistration.getHomeAddress());
            pstmt.setInt(7, thisRegistration.getZip());
            pstmt.setString(8, thisRegistration.getState());
             pstmt.executeUpdate();
            registration.add(thisRegistration);
            thisRegistration = new Registration();
            refreshR();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }
       public String updateDetail(int i) throws SQLException {
        try (Connection conn = Database.getConnection()) {
             String name = thisRegistration.getName();
             String password= thisRegistration.getPassword();
             String email = thisRegistration.getEmailAddress();
             int age = thisRegistration.getAge();
             int contactNumber = thisRegistration.getContactNumber();
             String homeAdddress = thisRegistration.getHomeAddress();
             int zip = thisRegistration.getZip();
             String state = thisRegistration.getState();
             i = thisRegistration.getCustomer_id();
            String sql = "UPDATE Products SET Name = ? , SET Password = ?, SET Email = ?, SET Age = ?, SET ContactNumber = ?, SET HomeAddress = ?, SET Zip= ?, SET State = ? WHERE customer_id  =" + i;
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.setInt(4, age);
            pstmt.setInt(5, contactNumber);
            pstmt.setString(6, homeAdddress);
            pstmt.setInt(7, zip);
            pstmt.setString(8, state);
            pstmt.executeUpdate();
            
              refreshR();
            }
       
        return "Account";
    }
       
       public String delete() throws SQLException {
        try (Connection conn = Database.getConnection()) {
              int id = thisRegistration.getCustomer_id();
            if (thisRegistration.getCustomer_id()>= 0) {
                String sql = "DELETE FROM registration  WHERE customer_id = " + id ;
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
            } else {
            }
            refreshR();
            return "index";
        }
    }
}
