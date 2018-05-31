package week2;

import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityQueueOfStrings {
    private String[] items;
    private int f, l;

    public FixedCapacityQueueOfStrings(int capacity) {
        items = new String[capacity];
        f = l = 0;
    }

    public boolean isEmpty() {
        return f == l;
    }

    public void enqueue(String item) {
        items[l++] = item;
    }

    public String dequeue() {
        String item = items[f++];
        if (f == items.length)
            f = l = 0;
        return item;
    }

    public void peek() {
        for (int i = f; i < l; i++) {
            StdOut.print(items[i] + " -> ");
        }
    }

    public static void main(String[] args) {
        FixedCapacityQueueOfStrings queue = new FixedCapacityQueueOfStrings(10);
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        queue.peek();
        StdOut.println();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        queue.peek();
    }

}
