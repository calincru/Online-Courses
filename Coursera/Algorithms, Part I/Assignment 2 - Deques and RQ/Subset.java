
public class Subset {
	
	public static void main(String[] args) {
	
		RandomizedQueue<String> r = new RandomizedQueue<String>();
		int k = Integer.parseInt(args[0]);
		
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			r.enqueue(item);
		}

		int count = 0;
		for (String x : r) {
			StdOut.print(x);
			if (++count > k ) break;
			StdOut.println();
		}
	}
}
