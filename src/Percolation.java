import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufBack;
    private int sz;
    private int N;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n < 0)
            throw new java.lang.IllegalArgumentException();

        grid = new boolean[n][n];

        // initially all the sets are blocked
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }

        this.N = n;
        int NSquare = n * n;
        sz = NSquare + 2;

        // creates two virtual objects and connect them with top and bottom
        uf = new WeightedQuickUnionUF(sz);
        ufBack = new WeightedQuickUnionUF(NSquare + 1);
        for (int i = 1; i <= n; i++) {
            uf.union(0, i);
            uf.union(sz - 1, NSquare + 1 - i);
            ufBack.union(0, i);
        }
    }

    // open site (row, col) if it is not open already
    public void open(int i, int j) {
        validateArgs(i, j);

        grid[i - 1][j - 1] = true;
        int idx = posToIndex(i, j);

        // after opening the set, connect it to it's opened neighbours
        if (i > 1 && isOpen(i - 1, j)) {
            uf.union(posToIndex(i - 1, j), idx);
            ufBack.union(posToIndex(i - 1, j), idx);
        }

        if (i < N && isOpen(i + 1, j)) {
            uf.union(posToIndex(i + 1, j), idx);
            ufBack.union(posToIndex(i + 1, j), idx);
        }

        if (j > 1 && isOpen(i, j - 1)) {
            uf.union(posToIndex(i, j - 1), idx);
            ufBack.union(posToIndex(i, j - 1), idx);
        }

        if (j < N && isOpen(i, j + 1)) {
            uf.union(posToIndex(i, j + 1), idx);
            ufBack.union(posToIndex(i, j + 1), idx);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int i, int j) {
        validateArgs(i, j);
        return grid[i - 1][j - 1];
    }

    // is site(row, col) full?
    public boolean isFull(int i, int j) {
        validateArgs(i, j);
        return isOpen(i, j) && ufBack.connected(posToIndex(i, j), 0);
    }

    // number of open sites
    public int numberOfOpenSites() {
        int num = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j])
                    num++;
            }
        }
        return num;
    }

    // does the system percolates
    public boolean percolates() {
        if (N == 1)
            return isOpen(1, 1); //corner case N = 1
        return uf.connected(0, sz - 1);
    }

    // cal respective index in the array for a position in grid
    private int posToIndex(int i, int j) {
        return (i - 1) * N + j;
    }

    private void validateArgs(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N)
            throw new java.lang.IllegalArgumentException();
    }

    public static void main(String[] args) {
        int N = 4;
        Percolation perc = new Percolation(N);
        int i = 0;
        int j = 0;
        int openNum = 0;
        while (!perc.percolates()) {
            i = StdRandom.uniform(1, N + 1);
            j = StdRandom.uniform(1, N + 1);
            if (perc.isOpen(i, j))
                continue;
            perc.open(i, j);
            openNum++;
        }

        System.out.println("openNum = [" + openNum + "]");
    }


}
