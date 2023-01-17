package function.interpretator.model;

public class OperatorNode extends Node {

    private final OperatorType type;

    public OperatorNode(String data, OperatorType type) {
        super(data);
        this.type = type;
    }

    @Override
    public boolean getValue() {
        return type.evaluateResult(getLeft().getValue(), getRight().getValue());
    }
}
