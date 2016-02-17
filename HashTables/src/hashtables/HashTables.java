package hashtables;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * This class implements a hash set using separate chaining.
 * @author omiranda
 */
public class HashTables
{
    private Node[] buckets;
    private int currentSize;
    
    public HashTables(int bucketsLength){
        buckets = new Node[bucketsLength];
        currentSize = 0;
    }
    
    public boolean contains(Object x){
        int h = x.hashCode();
        if(h < 0) { h = -h; }
        h = h % buckets.length;
        
        Node current = buckets[h];
        while(current != null){
            if(current.data.equals(x)){ return true;}
        }
        return false;
    }
    
    public boolean add(Object x) {
        int h = x.hashCode();
        if(h < 0) { h = -h; }
        h = h % buckets.length;
        
        Node current = buckets[h];
        while(current != null) {
            if(current.data.equals(x)) { return false; }
            current = current.next;
        }
        Node newNode = new Node();
        newNode.data = x;
        newNode.next = buckets[h];
        buckets[h] = newNode;
        currentSize++;
        return true;
    }
    
    public boolean remove(Object x) {
        int h = x.hashCode();
        if(h < 0) { h = -h; }
        h = h % buckets.length;
        
        Node current = buckets[h];
        Node previous = null;
        while(current != null) {
            if(current.data.equals(x)) {
                if(previous == null) { buckets[h] = current.next; }
                else { previous.next = current.next; }
                currentSize--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }
    
    public Iterator iterator() {
        return new HashSetIterator();
    }
    
    public int size() {
        return currentSize;
    }
    
    class Node {
        public Object data;
        public Node next;
    }
    
    class HashSetIterator implements Iterator{
        private int bucketIndex;
        private Node current;
        
        public HashSetIterator() {
            current = null;
            bucketIndex = -1;
        }
        
        public boolean hasNext() {
            if(current != null && current.next != null) { return true; }
            for(int b = bucketIndex + 1; b < buckets.length; b++) {
                if(buckets[b] != null) { return true; }
            }
            return false;
        }
        
        public Object next() {
            if(current != null && current.next != null) {
                current = current.next;
            } else {
                do {
                    bucketIndex++;
                    if(bucketIndex == buckets.length) {
                        throw new NoSuchElementException();
                    }
                    current = buckets[bucketIndex];
                }
                while(current == null);
            }
            return current.data;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
