/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarsrestaurantreservations;

import java.util.Iterator;

/**
 *
 * @author omiranda
 * @param <Reservation>
 */
public interface LinkedListIteratorInter<Reservation> extends Iterator<Reservation>
{
    void add(Reservation e);
    @Override
    boolean hasNext();
    @Override
    Reservation next();
    void makeReservation(Reservation e);

    public void display();
}
