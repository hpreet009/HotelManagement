/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author c0687969
 */
@Named
@SessionScoped
public class Login implements Serializable {

    private String username ;
    private String password ;
    private boolean loggedIn;
    private user currentuser;

    
     public Login() {
        username = null;
        password = null;
        loggedIn = false;
        currentuser = null;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public user getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(user currentuser) {
        this.currentuser = currentuser;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String doLogin() {
        for (user u : Users.getInstance().getUsers()) {
            if (username.equals(u.getUsername())
                    && password.equals(u.getPassword())) {
                loggedIn = true;
                currentuser = u;
                return "reservation";
            }
                
            }
        currentuser = null;
        loggedIn = false;
        return "index";
    }
}

        



