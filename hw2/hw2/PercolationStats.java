package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] num;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        StdRandom.setSeed(N * N);
        num = new double[T];
        for (int i = 0; i < T; i++) {
            num[i] = StdRandom.getSeed();
        }
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        pf = new PercolationFactory();
        pf.make(N * N);
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
