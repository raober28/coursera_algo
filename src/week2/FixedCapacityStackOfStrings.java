package week2;

public class FixedCapacityStackOfStrings<Item> {
    private Item[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = (Item[]) new Object[capacity];
    }


    public boolean isEmpty() {
        return N == 0;
    }

    //used to index into array; then increment f
    public void push(Item item) {
        s[N++] = item;
    }

    //decrement f; then used to index into array
    public Item pop() {
        Item item = s[--N];
        s[N] = null;
        return item;
    }

}
