/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarsrestaurantreservations;

/**
 *
 * @author Oscar Miranda
 * This class is for creating Walk-in objects which
 * consist of a first and last name of the customer
 * which will be added to the restaurant queue
 */
public class WalkIn
{
    private String firstName, lastName;
    /**
     * Constructs the walk-in object
     * @param firstName first name
     * @param lastName last name
     */
    WalkIn(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    /**
     * sets the first name
     * @param firstName passed first name
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    /**
     * sets last name
     * @param lastName passed last name 
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    /**
     * returns first name
     * @return first name of This object
     */
    public String getFirstName()
    {
        return firstName;
    }
    /**
     * returns last name
     * @return last name of This object
     */
    public String getLastName()
    {
        return lastName;
    }
    /**
     * overrides the toString() method
     * @return first name and last name of costumer
     */
    @Override
    public String toString()
    {
        return firstName +" "+ lastName;
    }
}
