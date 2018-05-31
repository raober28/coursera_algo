package week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SocialNetworkClient {
    public static void main(String[] args) {

        int N = StdIn.readInt();
        SocialNetwork sn = new SocialNetwork(N);

        while (!StdIn.isEmpty()) {
            double t = StdIn.readDouble();
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            sn.makeFriends(p, q);
            int i = sn.countFriends(p);
            int j = sn.countFriends(q);

            if (i == N || j == N) {
                StdOut.println("All users becomes friends with each other at : " + t);
                return;
            }

        }
        StdOut.println("All users did not becomes friends");
    }
}
