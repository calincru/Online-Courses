/**
 * @name: Cruceru Calin-Cristian
 * @Programming Assignment 1: Percolation
 */

public class PercolationStats {
    
    private int testsNumber;
    private double[] fractions;
    
    public PercolationStats(int N, int T) {
        if (!isValid(N, T)) 
            throw new IllegalArgumentException("Bad N or T!");
      
        testsNumber = T;
        fractions = new double[T];
        for (int i = 0; i < testsNumber; i++) {
            Percolation grid = new Percolation(N);
            int opened = 0;
            
            while (!grid.percolates()) {
                int row = StdRandom.uniform(1, N + 1);
                int column = StdRandom.uniform(1, N + 1);
                
                if (!grid.isOpen(row, column)) {
                    grid.open(row, column);
                    opened++;
                }
            }
            fractions[i] = (double) opened / (N * N);
        }
    }
    
    public double mean() {
        return StdStats.mean(fractions);
    }
    
    public double stddev() {
        return StdStats.stddev(fractions);
    }
    
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(testsNumber);
    }
    
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(testsNumber);
    }
    
    private boolean isValid(int N, int T) {
        return N > 0 && T > 0;
    }
    
    public static void main(String[] args) {
        PercolationStats PS = new PercolationStats(Integer.parseInt(args[0]), 
                                                   Integer.parseInt(args[1]));
        StdOut.println("mean                    = " + PS.mean());
        StdOut.println("stddev                  = " + PS.stddev());
        StdOut.println("95% confidence interval = " 
                       + PS.confidenceLo() + ", " +  PS.confidenceHi());
    }
}
