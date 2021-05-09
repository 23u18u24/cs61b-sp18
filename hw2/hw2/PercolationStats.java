package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] num;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        num = new double[T];
        pf = new PercolationFactory();
        for (int i = 0; i < T; i++) {
            Percolation system = pf.make(N);
            while (!system.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!system.isOpen(row, col)) {
                    system.open(row, col);
                }
            }
            double threshold = (double) system.numberOfOpenSites() / (N * N);
            num[i] = threshold;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(num);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(num);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double aver = mean();
        double std = stddev();
        return aver - (1.96 * std) / Math.sqrt(num.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double aver = mean();
        double std = stddev();
        return aver + (1.96 * std) / Math.sqrt(num.length);
    }
}
