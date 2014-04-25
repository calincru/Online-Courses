/**
 * @name: Cruceru Calin-Cristian
 * @Programming Assignment 1: Percolation
 */

public class Percolation {
 
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    private int size;
    private boolean[][] aux;
    
    public Percolation(int N) {
        size = N;
        aux = new boolean[N+1][N+1];
        uf = new WeightedQuickUnionUF(N*N + 2);
        uf2 = new WeightedQuickUnionUF(N*N + 2);
        
        for (int i = 1; i <= N; i++) {
            uf.union(0, i);
            uf2.union(0, i);
            uf.union(N*N + 1, N*N + 1 - i);
        }
    } 
    
    public void open(int i, int j) {
        if (!isValid(i, j)) { 
            throw new IndexOutOfBoundsException("Exceeded the grid space!");
        }
        
        if (isOpen(i, j)) return;
        if (j < size && isOpen(i, j+1)) { 
            uf.union(xyTo1D(i, j), xyTo1D(i, j+1)); 
            uf2.union(xyTo1D(i, j), xyTo1D(i, j+1)); 
        }
        if (j > 1 && isOpen(i, j-1)) { 
            uf.union(xyTo1D(i, j), xyTo1D(i, j-1)); 
            uf2.union(xyTo1D(i, j), xyTo1D(i, j-1)); 
        }
        if (i > 1 && isOpen(i-1, j)) { 
            uf.union(xyTo1D(i, j), xyTo1D(i-1, j)); 
            uf2.union(xyTo1D(i, j), xyTo1D(i-1, j)); 
        }
        if (i < size && isOpen(i+1, j)) { 
            uf.union(xyTo1D(i, j), xyTo1D(i+1, j)); 
            uf2.union(xyTo1D(i, j), xyTo1D(i+1, j)); 
        }
        
        aux[i][j] = true;
    }
    
    public boolean isOpen(int i, int j) {
        if (!isValid(i, j)) { 
            throw new IndexOutOfBoundsException("Exceeded the grid space!");
        }      
  
        return aux[i][j];
    }
    
    public boolean isFull(int i, int j) {
        if (!isValid(i, j)) { 
            throw new IndexOutOfBoundsException("Exceeded the grid space!");
        }        
        
        if (i == 1) return aux[i][j];
        return uf2.connected(0, xyTo1D(i, j));
    }
    
    public boolean percolates() {
        if (size == 1 && aux[1][1]) 
            return true;
        else if (size == 1)
            return false;
        return uf.connected(0, size * size + 1);
    }
    
    private boolean isValid(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size) return false;
        return true;
    }
    
    private int xyTo1D(int i, int j) {
        return size * (i-1) + j;
    }
}
