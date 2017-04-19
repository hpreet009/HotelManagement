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
public class RoomController {

 private List<Rooms> rooms = new ArrayList<>();
    private Rooms thisRoom = new Rooms();

    public RoomController() {
        thisRoom = new Rooms();
        refreshR();
    }

    public void refreshR() {
        try {
            rooms = new ArrayList<>();
            boolean statu = true;
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rooms Where status= " + statu);
            while (rs.next()) {
                Rooms r = new Rooms();
                r.setRoomNumber(rs.getInt("roomNumber"));
                r.setRoomType(rs.getString("roomType"));

                r.setStatus(rs.getBoolean("status"));
                r.setPrice(rs.getString("price"));

                rooms.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Rooms> getRooms() {
        return rooms;
    }

    public void setRooms(List<Rooms> rooms) {
        this.rooms = rooms;
    }

    public Rooms getThisRooms() {
        return thisRoom;
    }

    public int getRoomnumber(int id) {

        for (Rooms v : rooms) {
            if (v.getRoomNumber() == id) {
                return v.getRoomNumber();
            }
        }
        return 0;
    }

    public String book(Rooms r) {
        thisRoom = r;
        return "Booking";
    }

    public String booked(Rooms v) {
        thisRoom = v;
        return "editRoom";
    }

    public void setThisRooms(Rooms thisRooms) {
        this.thisRoom = thisRoom;
    }

    public String booking() throws SQLException {

        try (Connection conn = Database.getConnection()) {
            int i = thisRoom.getRoomNumber();
            String sql = "UPDATE rooms SET roomType=?, price = ?,status =? WHERE roomNumber=" + i;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, thisRoom.getRoomType());
            pstmt.setString(2, thisRoom.getPrice());
//            pstmt.setBoolean(3, thisRoom.isStatus());
//            pstmt.setInt(3, thisRoom.getRoomNumber());
            pstmt.executeUpdate();
            thisRoom = new Rooms();
            refreshR();
//            return "reservation";
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }

    public String updateDetail() throws SQLException {
        try (Connection conn = Database.getConnection()) {
            String Roomtype = thisRoom.getRoomType();
            String price = thisRoom.getPrice();

            String sql = "UPDATE rooms SET roomType = ? ,price = ? WHERE roomNumber=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);;
            pstmt.setString(1, Roomtype);
            pstmt.setString(2, price);

            pstmt.setInt(3, thisRoom.getRoomNumber());

            pstmt.executeUpdate();
            thisRoom = new Rooms();
            refreshR();
        }

        return "staff";
    }

    /**
     *
     * @param Username
     * @param password
     * @return
     */
    public String add(int n,String t,String p) {
        try {

            Connection conn = Database.getConnection();
            String sql = "INSERT INTO rooms(roomNumber,roomType,price) VALUES (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, thisRoom.getRoomNumber());
            pstmt.setString(2, thisRoom.getRoomType());
                    
            pstmt.setString(4, thisRoom.getPrice());
            pstmt.executeUpdate();
            rooms.add(thisRoom);
            thisRoom = new Rooms();
            refreshR();
        } catch (SQLException ex) {
            Logger.getLogger(project.RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "staff";
    }

    /**
     * Delete a Product From the Database and List
     */
    public String delete(int Id) throws SQLException {
        try (Connection conn = Database.getConnection()) {
            if (thisRoom.getRoomNumber() >= 0) {
                String sql = "DELETE FROM rooms WHERE roomNumber = " + Id;
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
