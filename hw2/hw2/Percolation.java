package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int[] intN;
    private int size;
    private WeightedQuickUnionUF uf;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        intN = new int[N * N];
        size = 0;
        uf = new WeightedQuickUnionUF(N * N);
    }

    private boolean isLegal(int row, int col) {
        return row >= 0 && row <= (N - 1) && col >= 0 && col <= (N - 1);
    }

    private boolean isLegal(int index) {
        return index < N * N && index >= 0;
    }

    private int xyTo1D(int row, int col) {
        return N * row + col;
    }

    private int perUnion(int index) {
        int n1 = 0, n2 = 0, n3 = 0, n4 = 0;
        if (isLegal(index + 1) && intN[index + 1] != 0) {
            uf.union(index, index + 1);
            n1 = intN[index + 1];
        }
        if (isLegal(index - 1) && intN[index - 1] != 0) {
            uf.union(index, index - 1);
            n2 = intN[index - 1];
        }
        if (isLegal(index + N) && intN[index + N] != 0) {
            uf.union(index, index + N);
            n3 = intN[index + N];
        }
        if (isLegal(index - N) && intN[index - N] != 0) {
            uf.union(index, index - N);
            n4 = intN[index - N];
        }
        if (n1 == -1 || n2 == -1 || n3 == -1 || n4 == -1) {
            return -1;
        }
        return intN[index];
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        } else if (!isLegal(row, col)) {
            throw new IndexOutOfBoundsException();
        } else {
            int index = xyTo1D(row, col);
            if (index < N && index >= 0) {
                intN[index] = -1;
            } else {
                intN[index] = 1;
            }
            size += 1;
            int newNum = perUnion(index);
            intN[index] = newNum;
            if (intN[index] == -1) {
                for (int i = 0; i < N * N; i++) {
                    if (uf.connected(i, index)) {
                        intN[i] = -1;
                    }
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isLegal(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int index = xyTo1D(row, col);
        return Math.abs(intN[index]) == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isLegal(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int index = xyTo1D(row, col);
        return intN[index] == -1;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return size;
    }

    // does the system percolate?
    public boolean percolates() {
        if (N == 1 || N == 2) {
            return  true;
        }
        for (int i = N * N - 1; i > N * N - N; i--) {
            if (intN[i] == -1) {
                return true;
            }
        }
        return false;
    }

    // use for unit testing (not required)
    public static void main(String[] args) {

    }
}
