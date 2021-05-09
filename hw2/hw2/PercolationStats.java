package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] experiments;
    private int experimentTimes;

    //perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        experimentTimes = T;
        experiments = new double[T];
        int totalSites = N * N;
        for (int i = 0; i < T; i++) {
            int openSites = 0;
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    openSites++;
                }
            }
            experiments[i] = (double) openSites / totalSites;
        }
    }

    //sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(experiments);
    }

    //sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(experiments);
    }

    //low endpoint of 95% confidence interval
    public double confidenceLow() {
        double mu = mean();
        double sigma = stddev();
        return mu - (1.96 * sigma / Math.sqrt(experimentTimes));
    }

    //high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mu = mean();
        double sigma = stddev();
        return mu + (1.96 * sigma / Math.sqrt(experimentTimes));
    }

}
