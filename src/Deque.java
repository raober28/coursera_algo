import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    int N = 0;

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    private Node front = null;
    private Node back = null;

    public Deque() {

    }

    public boolean isEmpty() {
        return front == null;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new java.lang.IllegalArgumentException();

        Node newNode = new Node();
        newNode.item = item;
        Node oldNode = front;
        newNode.next = oldNode;
        front = newNode;
        N++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new java.lang.IllegalArgumentException();

        Node newNode = new Node();
        newNode.item = item;
        Node oldNode = back;
        oldNode.next = newNode;
        newNode.prev = oldNode;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Node node = front;
        front = front.next;
        return node.item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Node node = back;
        back = back.prev;
        return node.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    public class LinkedIterator implements Iterator<Item> {
        public boolean hasNext() {
            return front != null;
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            return front.item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
}
