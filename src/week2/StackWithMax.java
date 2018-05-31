package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackWithMax implements Iterable<Integer> {
    private Integer[] s;
    private int N = 0;
    private int first = 0;
    int max;

    public StackWithMax() {
        s = new Integer[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(int item) {
        if (N == s.length)
            resize(2 * s.length);

        if (N == 0)
            max = item;
        else {
            if (item > max)
                max = item;
        }

        s[N++] = item;
    }

    public int pop() {
        int item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4)
            resize(s.length / 4);

        return item;
    }

    private void resize(int capacity) {
        Integer[] copy = new Integer[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Integer> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return s[--i];
        }
    }

    public Integer maximum() {
        return max;
    }
}

