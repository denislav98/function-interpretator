package function.interpretator.structures;

public class Stack<T> {

    private int stackSize;
    private T[] stackArray;
    private int top;

    public Stack() {
        this(10);
    }

    /**
     * constructor to create stack with size
     */
    public Stack(int size) {
        this.stackSize = size;
        this.stackArray = (T[]) new Object[stackSize];
        this.top = -1;
    }

    /**
     * Adds new entry to the top of the stack
     */
    public void push(T entry) {
        if (this.isStackFull()) {
            this.increaseStackCapacity();
        }
        this.stackArray[++top] = entry;
    }

    /**
     * Removes an entry from the top of the stack.
     */
    public T pop() {
        if (this.isStackEmpty()) {
        }
        T entry = this.stackArray[top--];
        return entry;
    }

    /**
     * Returns top of the stack without removing it.
     */
    public T peek() {
        return stackArray[top];
    }

    /**
     * Returns true if the stack is empty
     */
    public boolean isStackEmpty() {
        return (top == -1);
    }

    /**
     * Returns true if the stack is full
     */
    public boolean isStackFull() {
        return (top == stackSize - 1);
    }

    /**
     * Increase stack capacity
     */
    private void increaseStackCapacity() {
        T[] newStack = (T[]) new Object[this.stackSize * 2];
        for (int i = 0; i < stackSize; i++) {
            newStack[i] = this.stackArray[i];
        }
        this.stackArray = newStack;
        this.stackSize = this.stackSize * 2;
    }
}