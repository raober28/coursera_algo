package week1;


public class WeightedQuickUnion {
    private int[] id;
    private int[] size;

    public WeightedQuickUnion(int N) {
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    private int root(int i) {
        while (id[i] != i)
            i = id[i];
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if(i == j) return;

        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
    }

    //finds the largest element in the connected component list of given element
    public int find(int i) {
        int max = i;

        while(i != id[i])
        {
            if(id[i] > max)
                max = id[i];
            i = id[i];
        }
        return max;
    }
}
