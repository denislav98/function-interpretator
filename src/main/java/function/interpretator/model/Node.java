package function.interpretator.model;

public abstract class Node {

    private final String name;
    private boolean value;
    private Node left;
    private Node right;

    public Node(String name) {
        this(name, null, null);
    }

    public Node(String name, Node left, Node right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
