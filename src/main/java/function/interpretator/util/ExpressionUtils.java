package function.interpretator.util;

import static java.lang.String.format;
import static function.interpretator.model.OperatorType.AND;
import static function.interpretator.model.OperatorType.NOT;
import static function.interpretator.model.OperatorType.OR;

import java.util.ArrayList;
import java.util.List;

import function.interpretator.model.OperatorType;

public class ExpressionUtils {

    private ExpressionUtils() {}

    public static boolean isOperand(String value) { // проверка за операнд - пр: а -> true    & -> false
        if (value == null || value.length() != 1) {
            return false;
        }

        return Character.isLetter(value.charAt(0));
    }

    public static OperatorType obtainOperatorType(String str) { // определя вида на оператора
        switch (str) {
        case "&":
            return AND;
        case "|":
            return OR;
        case "!":
            return NOT;
        default:
            throw new IllegalArgumentException(format("Invalid operator given: '%s'", str));
        }
    }

    public static List<String> filterOperands(List<String> arguments) { // връща списък от операнди - [a,b,&,c,!] -> [a,b,c]
        List<String> operands = new ArrayList<>();
        for (String parameter : arguments) {
            if (isOperand(parameter)) {
                operands.add(parameter);
            }
        }
        return operands;
    }
}
