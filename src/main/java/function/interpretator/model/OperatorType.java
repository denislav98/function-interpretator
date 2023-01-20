package function.interpretator.model;

public enum OperatorType {

    AND("&"),
    OR("|"),
    NOT("!");

    private final String name;

    OperatorType(String name) {
        this.name = name;
    }

    public boolean evaluateResult(Node left, Node right) {
        boolean a = left != null && left.getValue();
        boolean b = right != null && right.getValue();
        switch (this) {
        case AND:
            return a && b;
        case OR:
            return a || b;
        case NOT:
            return !b;
        default:
            throw new IllegalStateException("No such operation entered!");
        }
    }
}
