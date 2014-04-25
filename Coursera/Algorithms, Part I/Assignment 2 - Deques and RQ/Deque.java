import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int N;
    private Node<Item> first;
    private Node<Item> last;
    
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    public Deque() {
        first = null;
        last = null;
        N = 0;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return N;
    }
    
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        
        Node<Item> newfirst = new Node<Item>();
        
        newfirst.next = first;
        newfirst.previous = null;
        newfirst.item = item;
        
        if (isEmpty()) { 
            last = newfirst; 
            first = newfirst;           
        }
        else { 
            first.previous = newfirst; 
            first = newfirst; 
        }
        N++;
    }
    
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        
        Node<Item> newlast = new Node<Item>();
        
        newlast.item = item;
        newlast.next = null;
        newlast.previous = last;
        
        if (isEmpty())
            first = newlast;
        else 
            last.next = newlast;
        
        last = newlast;
        
        N++;
    }
    
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        else           first.previous = null;
        N--;
        
        return item;
    }
    
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        
        Node<Item> temp = last;
        last = last.previous;
        if (last != null)
            last.next = null;
        else 
            first = null;
       
        Item item = temp.item;
        temp.previous = null;
        temp.next = null;
        temp = null;
        N--;
        
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }
    
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        
        public ListIterator(Node<Item> first) {
            current = first;
        }
        
        public boolean hasNext()   { return current != null;                    }
        public void remove()       { throw new UnsupportedOperationException(); }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            
            Item item = current.item;
            current = current.next;
            
            return item;
        }
    }
    
    public static void main(String[] args)   {
        Deque<String> d = new Deque<String>();
        
        d.addFirst("AA");
        d.addFirst("BB");
        d.addLast("CC");
        d.addLast("DD");
        d.addFirst("EE");
        d.addLast("FF");
        d.removeLast();
        d.removeLast();
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
        d.removeLast();
       
        for (String x : d) {
            StdOut.print(x + " ");
        }
        StdOut.println();
        
    }
}
