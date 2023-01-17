package function.interpretator.model;

public enum OperatorType {

    AND("&"),
    OR("|"),
    NOT("!");

    private final String name;

    OperatorType(String name) {
        this.name = name;
    }

    public boolean evaluateResult(boolean a, boolean b) {
        switch (this) {
        case AND:
            return a && b;
        case OR:
            return a || b;
        case NOT:
            return !a && !b;
        default:
            throw new IllegalStateException("No such operation entered!");
        }
    }
}
