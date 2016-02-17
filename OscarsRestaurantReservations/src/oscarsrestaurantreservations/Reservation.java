/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarsrestaurantreservations;

/**
 *
 * @author Oscar Miranda
 * This class creates Reservation objects
 * this objects consist of a party name and a time
 */
public class Reservation
{
    private String partyName;
    private int reservationTime;
    /**
     * Constructs the Reservation object
     * @param partyName name of party
     * @param reservationTime reservation time
     */
    Reservation(String partyName, int reservationTime)
    {
        this.partyName = partyName;
        this.reservationTime = reservationTime;
    }
    /**
     * Sets the party Name
     * @param partyName Party name being passed
     */
    public void setPartyName(String partyName)
    {
        this.partyName = partyName;
    }
    /**
     * sets the reservation time
     * @param reservationTime reservation time being passed
     */
    public void setReservationTime(int reservationTime)
    {
        this.reservationTime = reservationTime;
    }
    /**
     * returns party's name
     * @return party name
     */
    public String getPartyName()
    {
        return partyName;
    }
    /**
     * returns reservation time
     * @return time of reservation
     */
    public double getReservationTime()
    {
        return reservationTime;
    }
    /**
     * overrides the toString method
     * @return party name and time of reservation
     */
    @Override
    public String toString()
    {
        return partyName + " Reservation at : " + reservationTime;
    }
}
