package week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackOfStrings {


    public StackOfStrings() {

    }

    void push(String item) {

    }

    /*String pop() {

    }*/

    /*boolean isEmpty() {

    }*/

    public static void main(String[] args) {
        StackOfStrings stack = new StackOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.println("stack.pop()");
            else StdOut.println("stack.push(s)");
        }
    }
}
