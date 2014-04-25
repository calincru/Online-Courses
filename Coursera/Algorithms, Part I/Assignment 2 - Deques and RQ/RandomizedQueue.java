import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
   
    private Item[] r;
    private int N = 0;
    
    public RandomizedQueue() {
        r = (Item[]) new Object[2];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
   
    public int size() {
        return N;
    }
   
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        
        if (N == r.length) resize(2*r.length);
        r[N++] = item;
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        
        int position = StdRandom.uniform(0, N);
        Item item = r[position];
        
        exch(position, N-1);
        r[N-1] = null;
        N--;
        
        if (N > 0 && N == r.length/4) resize(r.length/2); 
        return item;
    }
   
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        
        return r[StdRandom.uniform(0, N)];
    }
    
    private void exch(int i, int j) {
        Item temp = r[i];
        r[i] = r[j];
        r[j] = temp;
    }
    
    private void resize(int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = r[i];
        }
        r = temp;
    }

    public Iterator<Item> iterator() {
        if (N == 0) return new ArrayIterator(0);
        return new ArrayIterator(StdRandom.uniform(0, N));
    }
   
    private class ArrayIterator implements Iterator<Item> {
        private int current;
        private int count;
        
        public ArrayIterator(int position) {
            current = position;
            count = 0;
        }
        
        public boolean hasNext()   { return count < N; }
        public void remove()       { throw new UnsupportedOperationException(); }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            
            Item item = r[current % N];
            current++;
            count++;
            
            return item;
        }
    }
    
    public static void main(String[] args) {
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        
        r.enqueue("AA");
        r.enqueue("BB");
        r.enqueue("CC");
        r.enqueue("DD");
        r.dequeue();
        r.dequeue();
        r.dequeue();
        r.dequeue();
      
        for (String x : r) {
            StdOut.print(x + " ");
        }
        StdOut.println();
    }
}
