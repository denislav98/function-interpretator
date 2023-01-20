package function.interpretator;

import static function.interpretator.model.OperatorType.NOT;
import static function.interpretator.util.ExpressionUtils.isOperand;
import static function.interpretator.util.ExpressionUtils.obtainOperatorType;

import java.util.List;
import java.util.Map;

import function.interpretator.model.Node;
import function.interpretator.model.OperandNode;
import function.interpretator.model.OperatorNode;
import function.interpretator.model.OperatorType;
import function.interpretator.structures.Stack;

public class ExpressionTreeHelper {

    private ExpressionTreeHelper() {}

    // решаване на дърво
    // func1(a,b,c) -> 1,1,0
    // KEY - VALUE
    // а -> 1
    // b -> 1
    // c -> 0
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


    //   &
    //  / \
    // a   b
    // рекурсивно търсене на даден възел(node)
    // проверка дали не е корен на дървото
    // ако не е корен - търсим първо в лявото под-дърво
    // ако не е корен и не е в лявото под-дърво - търсим в дясното под-дърво
    private static Node findNode(Node root, String value) { // tree и а
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
                OperatorType operatorType = obtainOperatorType(currentParam);
                temp = new OperatorNode(currentParam, operatorType);

                if (operatorType.name().equals(NOT.name())) {
                    Node t1 = st.pop();
                    temp.setRight(t1);
                } else {
                    Node t1 = st.pop();
                    Node t2 = st.pop();

                    temp.setLeft(t2);
                    temp.setRight(t1);
                }

                st.push(temp);
            }
        }
        temp = st.pop();
        return temp;
    }
}
