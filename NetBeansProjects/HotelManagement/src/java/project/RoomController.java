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

    public String book(Rooms r) {
        thisRoom = r;
        return "Booking";
    }

    public void setThisRooms(Rooms thisRooms) {
        this.thisRoom = thisRoom;
    }

    public String booking() throws SQLException {

        try (Connection conn = Database.getConnection()) {

            String sql = "UPDATE rooms SET status = 0 WHERE roomNumber=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            thisRoom = new Rooms();
            refreshR();
//            return "reservation";
        }
        
        catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }

}
