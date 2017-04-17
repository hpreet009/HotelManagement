/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author c0687421
 */
public class Rooms {
    private int roomNumber;
    private String roomType;
   private String price;
    private boolean status;
/**
 * Set the roomNumber
 * @return the roomType
 */
    public int getRoomNumber() {
        return roomNumber;
    }
/**
 * Set the roomNumber
 * @param roomNumber the roomNumber
 */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
/**
 * Set the roomType
 * @return the roomType
 */
    public String getRoomType() {
        return roomType;
    }
/**
 * Set the roomType
 * @param roomType the roomType
 */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
/**
 * Set the price
 * @return  the price
 */
    public String getPrice() {
        return price;
    }
/**
 * Set the price 
 * @param price the price
 */
    public void setPrice(String price) {
        this.price = price;
    }

   /**
    * Set the status
    * @return  the status
    */

    public boolean isStatus() {
        return status;
    }
/**
 * Set the status
 * @param status the status
 */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
