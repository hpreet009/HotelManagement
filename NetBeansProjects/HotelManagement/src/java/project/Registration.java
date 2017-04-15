/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author c0688638
 */
@Named
@ApplicationScoped
public class Registration {

    private String name;
    private String password;
    private String emailAddress;
    private int age;
    private int contactNumber;
    private String homeAddress;
    private int zip;
    private String state;
/**
 * Retrieve the name
 * @return  the name 
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
 * Retrieve the password
 * @return the password
 */
    public String getPassword() {
        return password;
    }
/**
 * Set the password
 * @param password  the password
 */
    public void setPassword(String password) {
        this.password = password;
    }
/**
 * Retrieve the EmailAddress
 * @return the EmailAddress
 */
    public String getEmailAddress() {
        return emailAddress;
    }
/**
 * Set the EmailAddress
 * @param emailAddress the EmailAddress
 */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
/**
 *  Retrieve the Age
 * @return the Age
 */
    public int getAge() {
        return age;
    }
/**
 * Set the Age
 * @param age the Age
 */
    public void setAge(int age) {
        this.age = age;
    }
/**
 * Retrieve the contactNumber
 * @return the contactNumber
 */
    public int getContactNumber() {
        return contactNumber;
    }
/**
 * Set the contactNumber
 * @param contactNumber the contactNumber
 */
    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }
/**
 * Retrieve the homeAddress
 * @return the homeAddress
 */
    public String getHomeAddress() {
        return homeAddress;
    }
/**
 * Set the homeAddress
 * @param homeAddress the homeAddress
 */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
/**
 *  Retrieve the zip
 * @return the zip
 */
    public int getZip() {
        return zip;
    }
/**
 * Set the zip
 * @param zip the zip
 */
    public void setZip(int zip) {
        this.zip = zip;
    }
/**
 * Set the state
 * @return  the state
 */
    public String getState() {
        return state;
    }
/**
 * Set the state
 * @param state the state
 */
    public void setState(String state) {
        this.state = state;
    }

}
