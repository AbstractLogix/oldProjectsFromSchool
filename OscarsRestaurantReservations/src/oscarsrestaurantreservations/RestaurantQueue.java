/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarsrestaurantreservations;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Creates a Linked list implementation of a Queue
 * @author Oscar Miranda
 */
public class RestaurantQueue
{
    private Node front;
    private Node rear;
    private int size;
    /**
     * default constructor because why not
     */
    public RestaurantQueue() {}
    
    /**
     * this is the Node class
     */
    private static class Node
    {
        WalkIn data;
        Node next;
        /**
         * constructs a Node object
         * @param data Walk-in object
         * @param next reference to next node
         */
        Node(WalkIn data, Node next)
        {
            this.data = data;
            this.next = next;
        }
        void display()
        {
            System.out.println(data.toString());
        }
    }
    /**
     * adds a walkIn object to the queue
     * @param aCustWalkIn object being passed
     */
    public void addWalkin(WalkIn aCustWalkIn) 
    {
        Node node = new Node(aCustWalkIn, null);
        if(rear == null)
        {
            rear = node;
            front = node;
        } else {
            rear.next = node;
        }
        rear = node;
        size++;
    }
    /**
     * removes first node in queue
     * @return walkIn object
     */
    public WalkIn remove()
    {
        if(front == null)
        {
            throw new NoSuchElementException();
        }
        WalkIn data = front.data;
        front = front.next;
        if(front == null)
        {
            rear = null;
        }
        size--;
        return data;
    }
    /**
     * takes a peek at the next object in queue
     * @return next object in queue
     */
    public WalkIn peek()
    {
        if(front == null)
        {
            throw new NoSuchElementException();
        }
        return front.data;
    }
    /**
     * returns queue size
     * @return amount of objects in queue
     */
    public int getCount()
    {
        return size;
    }
    /**
     * checks to see if queue is empty
     * @return true if empty
     */
    public boolean isEmpty()
    {
        return size == 0;
    }
    /**
     * simulates a customer sitting down
     * @param aCustWalkin object being passed
     */
    public void fillEmptyTable(WalkIn aCustWalkin)
    {
        System.out.println("Here's your table " + aCustWalkin.getFirstName() +
                " " + aCustWalkin.getLastName());
    }
    /**
     * displays node and contents
     */
    public void display()
    {
        Node node = front;
        while(node != null)
        {
            node.display();
            System.out.println("Next: " + node.next);
            node = node.next;
            System.out.println();
        }   
    }
}
