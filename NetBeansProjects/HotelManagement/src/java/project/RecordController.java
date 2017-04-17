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
 * @author c0689321
 */
@Named
@ApplicationScoped
public class RecordController {
     private List<Record> record = new ArrayList<>();
    private Record thisRecord = new Record();

    public RecordController() {
        thisRecord = new Record();
        refreshR();

    }

    public void refreshR() {
        try {
            record = new ArrayList<>();
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Record");
            while (rs.next()) {
               Record r = new Record();
               r.setRoomType(rs.getString("RoomType"));
               r.setPrice(rs.getString("Price"));
               r.setCardType(rs.getString("CardType"));
               r.setCardHolder(rs.getString("CardholderName"));
               r.setCardNumber(rs.getInt("CardNumber"));
                                                        
                record.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Record> getRecord() {
        return record;
    }

    public void setRecord(List<Record> record) {
        this.record = record;
    }

    public Record getThisRecord() {
        return thisRecord;
    }

    public void setThisRecord(Record thisRecord) {
        this.thisRecord = thisRecord;
    }
    
    
    
     public String add(String roomtype, String price, String cardType,String cardHolder, int cardNumber) {
        try {
             thisRecord = new Record();
            Connection conn = Database.getConnection();
            String sql = "INSERT INTO Record(RoomType, Price, CardType, CardholderName, CardNumber) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,roomtype);
            pstmt.setString(2, price);
            pstmt.setString(3, cardType);
            pstmt.setString(4, cardHolder);
            pstmt.setInt(5, cardNumber);
           
            
            pstmt.executeUpdate();
            record.add(thisRecord);
            thisRecord = new Record();
            refreshR();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "booked";
    }

    
}
