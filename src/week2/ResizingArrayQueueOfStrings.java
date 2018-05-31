package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code ResizingArrayQueue} class represents a first-in-first-out (FIFO)
 * queue of generic items.
 * It supports the usual <em>enqueue</em> and <em>dequeue</em>
 * operations, along with methods for peeking at the first item,
 * testing if the queue is empty, and iterating through
 * the items in FIFO order.
 * <p>
 * This implementation uses a resizing array, which double the underlying array
 * when it is full and halves the underlying array when it is one-quarter full.
 * The <em>enqueue</em> and <em>dequeue</em> operations take constant amortized time.
 * The <em>size</em>, <em>peek</em>, and <em>is-empty</em> operations takes
 * constant time in the worst case.
 * <p>
 */
public class ResizingArrayQueueOfStrings<Item> implements Iterable<Item> {
    private Item[] items;     // queue elements
    private int n;              // total no of elements in the queue
    private int first;          // index of the first element of the queue
    private int last;           // index of the next available slot

    /**
     * Initialize empty queue
     */
    public ResizingArrayQueueOfStrings() {
        items = (Item[]) new Object[2];
        first = last = 0;
        n = 0;
    }

    /**
     * Is this queue empty?
     *
     * @return true if queue empty, false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * @return the number of items in the queue
     */
    public int size() {
        return n;
    }

    //resize the underlying array
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = items[(first + 1) % items.length];
        }
        items = copy;
        first = 0;
        last = n;
    }

    /**
     * Adds the item to this queue
     *
     * @param item the item to add
     */
    public void enqueue(Item item) {
        if (n == items.length)
            resize(items.length * 2);   // double the size of array and  recopy to front of array

        items[last++] = item;

        if (last == items.length) last = 0;     // wrap-around
        n++;
    }


    /**
     * Removes and returns the least recently added to the queue
     *
     * @return the least recently added to the queue
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = items[first];
        items[first] = null;                // to avoid loitering
        n--;
        first++;
        if (first == items.length) first = 0;       // wrap-around

        if (n > 0 && n == items.length / 4) resize(items.length / 2);    // shrink size of array
        return item;
    }


    /**
     * @return the element least recently added to the queue
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return items[first];
    }


    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }


    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = items[(first + i) % items.length];
            i++;
            return item;
        }
    }

}