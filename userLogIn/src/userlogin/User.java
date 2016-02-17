/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userlogin;

/**
 *
 * @author omiranda
 */
public class User
{
    private String userName, userPass;
    public int userID;
    
    public User(String userName, String userPass){
        this.userName = userName;
        this.userPass = userPass;
    }
    public void setUserName(String name){
        this.userName = name;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setUserPass(String pass){
        this.userPass = pass;
    }
    public String getPass(){
        return this.userPass;
    }
    
}
