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
 * @author c0687421
 */

  @Named
  @ApplicationScoped
 public class StaffController {

    private List<StaffLogin> stafflogin = new ArrayList<>();
    private StaffLogin thisStaff = new StaffLogin();

    public StaffController() {
        thisStaff = new StaffLogin();
        refreshR();

    }

    public void refreshR() {
        try {
            stafflogin = new ArrayList<>();
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Staff");
            while (rs.next()) {
                StaffLogin r = new StaffLogin();
                r.setId(rs.getInt("id"));
                r.setUsername(rs.getString("Username"));
                r.setPassword(rs.getString("Password"));
                
                                                        
                stafflogin.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(project.RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<StaffLogin> getStafflogin() {
        return stafflogin;
    }

    public void setStafflogin(List<StaffLogin> stafflogin) {
        this.stafflogin = stafflogin;
    }

    public StaffLogin getThisStafflogin() {
        return thisStaff;
    }
 public int getId(int id) {

        for (StaffLogin v : stafflogin) {
            if (v.getId()== id) {
                return v.getId();
            }
        }
        return 0;
    }
  public String getUsername() {
        for (StaffLogin p : stafflogin) {

            return p.getUsername();

        }
        return null;
    }
    public void setThisStafflogin(StaffLogin thisStaff) {
        this.thisStaff = thisStaff;
    }
    
     public String Detail(StaffLogin p) {
        thisStaff = p;
        return "editStaff";
    }
    
    
     public String add(String Username, String password ) {
        try {
             
            Connection conn = Database.getConnection();
            String sql = "INSERT INTO staff(Username,Password) VALUES (?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, thisStaff.getUsername());
            pstmt.setString(2, thisStaff.getPassword());
           
             pstmt.executeUpdate();
            stafflogin.add(thisStaff);
            thisStaff = new StaffLogin();
            refreshR();
        } catch (SQLException ex) {
            Logger.getLogger(project.RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "staff";
    }
       public String updateDetail() throws SQLException {
        try (Connection conn = Database.getConnection()) {
            String name = thisStaff.getUsername();
            String password = thisStaff.getPassword();
            String sql = "UPDATE staff SET Username = ? ,Password = ? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setInt(3, thisStaff.getId());
           
             pstmt.executeUpdate();
            thisStaff=new StaffLogin();
              refreshR();
            }
       
        return "staff";
    }
       
  /**
     * Delete a Product From the Database and List
     */
    public String delete(int Id) throws SQLException {
        try (Connection conn = Database.getConnection()) {
            if (thisStaff.getId()>= 0) {
                String sql = "DELETE FROM staff WHERE id= " + Id;
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
            } else {
                return "booked";
            }
            refreshR();
            return "staff";
        }
    }
}
