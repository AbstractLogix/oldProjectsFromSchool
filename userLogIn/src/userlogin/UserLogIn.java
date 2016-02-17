/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userlogin;

import java.util.Scanner;

/**
 *
 * @author omiranda
 */
public class UserLogIn
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException
    {
        String message = "Welcome to Login+";
        char[] chars = message.toCharArray();
        String user ="" , pass = "";
        
        
        User currentUser = new User(user, pass);
        
        //print a char from array slowly
        for(int i = 0; i < chars.length; i++){
            System.out.println(chars[i] + " ");
            Thread.sleep(100);
        }
        System.out.println("Please enter user name: ");
        Scanner in = new Scanner(System.in);
        currentUser.setUserName(in.next().trim());
        System.out.println("Please enter desired password: ");
        currentUser.setUserPass(in.next());
        System.out.println("Confirm password: ");
        if(!currentUser.getPass().equals(in.next())){
        System.out.println("Password does not match");
        }
    }
}
