package week1;

public class SocialNetwork {
    private int[] users;
    private int[] friends;

    public SocialNetwork(int N) {
        users = new int[N];
        friends = new int[N];
        for (int i = 0; i < N; i++) {
            users[i] = i;
            friends[i] = 1;
        }
    }

    private int root(int i) {
        while (users[i] != i)
            i = users[i];
        return i;
    }
    public int countFriends(int i)
    {
        return friends[i];
    }

    public void makeFriends(int f1, int f2) {
        int i = root(f1);
        int j = root(f2);

        //f1 and f2 are already friends
        if (i == j) return;

        if (friends[i] < friends[j]) {
            users[i] = j;
            friends[j] += friends[i];
        } else {
            users[j] = i;
            friends[i] += friends[j];
        }
    }
}
