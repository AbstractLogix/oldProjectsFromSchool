/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarsrestaurantreservations;

import java.util.NoSuchElementException;

/**
 * This class constructs a Node object
 * @author Oscar Miranda
 */

class Node
{
    Reservation data;
    Node next;
    
    public Node(Reservation data)
    {
        this.data = data;
        next = null;
    }

    void display()
    {
        System.out.println(data.toString());
    }
    @Override
    public String toString()
    {
        Reservation data = this.data;
        return data.toString();
    }
}
/**
 * This class constructs a linked list
 * @author Oscar Miranda
 */
public class LinkedList
{
    public Node first;
    /**
     * constructor for linked list
     * sets first Node to null
     */
    LinkedList()
    {
        first = null;
    }
    /**
     * checks to see if the list is empty
     * @return true if first equals null
     */
    public boolean isEmpty()
    {
        return (first == null);
    }
    /**
     * inserts a new node at the beginning of the list
     * @param data Reservation object is passed 
     */
    public void insertFirst(Reservation data)
    {
        Node newLink = new Node(data);
        newLink.next = first;
        first = newLink;
    }
    /**
     * removes the first node in the linked list
     * @return the next node
     */
    public Node removeFirst()
    {
        Node linkRef = first;
        if(!isEmpty())
        {
            first = first.next;
        } else {
            System.out.println("Empty LinkedList");
        }
        return linkRef;
    }
    /**
     * this method traverses the list looking for
     * a matching Reservation object
     * @param data search criteria
     * @return the found node
     */
    public Node find(Reservation data)
    {
        Node theNode = first;
        if(!isEmpty())
        {
            while(!theNode.equals(data))
            {
                if(theNode.next == null)
                {
                    return null;
                } else {
                    theNode = theNode.next;
                }
            }
        } else {
            System.out.println("Empty LinkedList");
        }
        return theNode;
    }
    /**
     * removes the node containing the Reservation
     * passed
     * @param data Reservation 
     * @return returns next node
     */
    public Node removeNode(Reservation data)
    {
        Node currentNode = first;
        Node previousNode = first;
        while(!currentNode.equals(data))
        {
            if(currentNode.next == null)
            {
                return null;
            } else {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
        }
        if(currentNode == first)
        {
            first = first.next;
        } else {
            previousNode.next = currentNode.next;
        }
        return currentNode;
    }
    /**
     * Replaces Reservation object
     * @param old Reservation to be replaced
     * @param newReservation Reservation to replace old
     */
    public void replaceReservation(Reservation old, Reservation newReservation)
    {
        Node node = this.find(old);
        this.removeNode(old);
        this.insertFirst(newReservation);
    }
    /**
     * Displays the nodes in the list
     */
    public void display()
    {
        Node node = first;
        while(node != null)
        {
            node.display();
            System.out.println("Next: " + node.next);
            node = node.next;
            System.out.println("\n");
        }
    }
    /**
     * This method creates an instance of the LinkedListIterator();
     * @return LinkedListIterator
     */
    public LinkedListIteratorInter listIterator()
    {
        return new LinkedListIterator();
    }
    /**
     * This class creates a linked list iterator and implements the
     * LinkListIterator interface and it's abstract methods
     */
    private class LinkedListIterator implements LinkedListIteratorInter<Reservation>
    {
        private Node current, previous;
        /**
         * constructs the iterator sets
         * current to null and previous to null
         */
        public LinkedListIterator()
        {
            current = null;
            previous = null;
        }
        /**
         * checks to see if there is a next
         * @return 
         */
        @Override
        public boolean hasNext()
        {
            if(current == null)
            {
                return first != null;
            } else {
                return current.next != null;
            }  
        }
        /**
         * returns next element in the iteration
         * @return next node's Reservation object
         */
        @Override
        public Reservation next()
        {
            if(!hasNext()) throw new NoSuchElementException();
            previous = current;
            if(current == null)
            {
                current = first;
            } else {
                current = current.next;
            }
            return (Reservation) current.data;
        }
        /**
         * inserts a Reservation into the list 
         * in the correct position
         * @param toMake Reservation object being passed
         */
        @Override
        public void makeReservation(Reservation toMake)
        {
            boolean found = false;
            current = null;
            Reservation data;
            while(hasNext() && !found)
            {
                data = next();
                if(data.getReservationTime() > toMake.getReservationTime())
                {
                    found = true;
                }
            }
            if(found)
            {
                current = previous;
            }
            add(toMake);
        }
        /**
         * adds Reservation to the end of the list
         * @param data Reservation object being passed
         */
        @Override
        public void add(Reservation data)
        {
          if(first == null)
          {
             insertFirst(data);
          } else {
             Node tmp = first;
             while(tmp.next != null) tmp = tmp.next;
             tmp.next = new Node(data);
          }
        }
        /**
         * Displays contents of the list
         */
        @Override
        public void display()
        {
            while(this.hasNext())
            {
                System.out.print(this.next() + " ");
                System.out.println();
            }         
        }
    }

}

