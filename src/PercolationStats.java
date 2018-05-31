import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double[] results;
    private int trials;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException();

        this.trials = trials;
        results = new double[trials];

        Percolation perc;
        for (int k = 0; k < trials; k++) {
            perc = new Percolation(n);

            int i = 0;
            int j = 0;
            int openNum = 0;
            double NSquare = n * n;

            while (!perc.percolates()) {
                i = StdRandom.uniform(1, n + 1);
                j = StdRandom.uniform(1, n + 1);

                if (perc.isOpen(i, j))
                    continue;
                perc.open(i, j);
                openNum++;
            }
            results[i] = openNum / NSquare;  // estimates of the percolation threshold
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        StdOut.printf("mean                     = ", +ps.mean());
        StdOut.printf("stddev                   = ", +ps.stddev());
        StdOut.printf("95% confidence interval  = " + ps.confidenceLo() +
                ',' + ps.confidenceHi());

    }
}
