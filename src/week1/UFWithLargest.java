package week1;

public class UFWithLargest {

    private int[] id;
    private int[] sz;
    private int[] large;

    public UFWithLargest(int N) {

        id = new int[N];
        sz = new int[N];
        large = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            large[i] = i;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // find the largest element, for the give element connected tree.
    public int find(int i) {
        return large[root(i)];
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        //i and j are already part of same forest
        if (i == j) return;

        int i_sz = sz[i];
        int j_sz = sz[j];

        int i_lar = large[i];
        int j_lar = large[j];

        // i's tree is smaller, thus it will be hanged to j's tree
        if (i_sz < j_sz) {
            id[i] = j;
            sz[j] += i_sz;

            if (i_lar > j_lar)
                large[j] = i_lar;
        }
        // j's tree is smaller, thus it will be hanged to ii's tree
        else {
            id[j] = i;
            sz[i] += j_sz;

            if (j_lar > i_lar)
                large[i] = j_lar;
        }
    }
}
