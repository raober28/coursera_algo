package week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class UF_Client {

    public static void main(String[] args) {

        int N = StdIn.readInt();
        UF uf = new UF(N);
        while(!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if(!uf.connected(p , q))
            {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
            StdOut.println(p + " " + q);
        }
    }
}
