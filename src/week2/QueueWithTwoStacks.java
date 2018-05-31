package week2;

public class QueueWithTwoStacks<Item> {
    private ResizingArrayStack<Item> stack1 = new ResizingArrayStack<Item>();
    private ResizingArrayStack<Item> stack2 = new ResizingArrayStack<Item>();

    public void enqueue(Item item) {
        stack1.push(item);
    }

    public Item dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
