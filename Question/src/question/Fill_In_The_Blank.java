/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package question;

import java.util.ArrayList;

/**
 *
 * @author omiranda
 */
public class Fill_In_The_Blank extends Question
{
    private ArrayList<String> choices = new ArrayList();
    
    public void fillIn(String choice)
    {
        choices.add(choice);
        if(choices.equals(choice))
        {
            String choiceString = " " + choices.size();
            setAnswer(choiceString);
        }
    }
}
