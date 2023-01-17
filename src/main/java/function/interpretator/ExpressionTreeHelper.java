package function.interpretator;

import static function.interpretator.util.ExpressionUtils.isOperand;
import static function.interpretator.util.ExpressionUtils.obtainOperatorType;

import java.util.List;
import java.util.Map;

import function.interpretator.model.Node;
import function.interpretator.model.OperandNode;
import function.interpretator.model.OperatorNode;
import function.interpretator.structures.Stack;

public class ExpressionTreeHelper {

    private ExpressionTreeHelper() {}

    public static boolean solveExpressionTree(Node tree, Map<String, String> paramsPerArguments) {
        for (Map.Entry<String, String> entry : paramsPerArguments.entrySet()) {
            Node node = findNode(tree, entry.getKey());
            if (node != null) {
                node.setValue(getValue(entry.getValue()));
            }
        }
        return tree.getValue();
    }

    private static boolean getValue(String booleanValue) {
        return booleanValue.equals("1");
    }

    private static Node findNode(Node root, String value) {
        if (root == null) {
            return null; // no such node
        }

        if (value.equals(root.getName())) {
            return root; // the node itself contains the value
        }

        Node n = findNode(root.getLeft(), value); // search left sub-tree
        if (n != null) {
            return n; // we've found it in the left sub-tree
        }

        return findNode(root.getRight(), value); // search right sub-tree
    }

    public static Node buildExpressionTree(List<String> functionParams) {
        Stack<Node> st = new Stack<>();
        Node temp;
        for (String currentParam : functionParams) {
            if (isOperand(currentParam)) {
                temp = new OperandNode(currentParam);
                st.push(temp);
            } else {
                temp = new OperatorNode(currentParam, obtainOperatorType(currentParam));

                Node t1 = st.pop();
                Node t2 = st.pop();

                temp.setLeft(t2);
                temp.setRight(t1);

                st.push(temp);
            }
        }
        temp = st.pop();
        return temp;
    }
}
