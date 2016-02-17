package linkedlist;
import java.util.*;

public class LinkedList {
    private Node first;
    private Node last;
    
    public LinkedList(){ first = null; }
    
    public Object getFirst(){
        if(first == null) { throw new NoSuchElementException(); }
        return first.data;
    }
    
    public void addFirst(Object element) {
        Node newNode = new Node();
        newNode.data = element;
        newNode.next = first;
        first = newNode;
    }
    
    public Object removeFirst() {
        if(first == null) { throw new NoSuchElementException(); }
        Object element = first.data;
        first = first.next;
        return element;
        }
    
    /**
     * List Iterator
     * @return 
     */
    public ListIterator listIterator() {
        return new LinkedListIterator();
    }
    
    class LinkedListIterator implements ListIterator {
        public Node position;
        public Node previous;
        public boolean isAfterNext;
        // should these be private? ?? ? 
        public LinkedListIterator() {
            position = null;
            previous = null;
            isAfterNext = false;
        }
        
        @Override
        public Object next() {
            if(!hasNext()) {throw new NoSuchElementException();}
            previous = position; // Remember for remove
            isAfterNext = true;
            
            if(position == null) {
                position = first;
            } else {
                position = position.next;
            }
            return position.data;
        }
        
        @Override
        public boolean hasNext() {
            if(position == null) {
                return first != null;
            } else {
                return position.next != null;
            }
        }
        
        @Override
        public void remove() {
            if(!isAfterNext) {throw new IllegalStateException();}
            
            if(position == first) {
                removeFirst();
            } else {
                previous.next = position.next;
            }
            position = previous;
            
            isAfterNext = false;
        }
        
        @Override
        public void add(Object element) {
            if(position == null) {
                addFirst(element);
                position = first;
            } else {
                Node newNode = new Node();
                newNode.data = element;
                newNode.next = position.next;
                position.next = newNode;
                position = newNode;
            }
            isAfterNext = false;
        }
        
        @Override
        public void set(Object element) {
            if(!isAfterNext) {throw new IllegalStateException();}
            position.data = element;
        }

        @Override
        public boolean hasPrevious()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object previous()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int nextIndex()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int previousIndex()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    /**
     * Node class
     */
    class Node {
        public Node next;
        public Object data;
        public Node previous;
    }
}
    

