package function.interpretator.validators;

import static java.lang.String.format;
import static function.interpretator.util.ExpressionUtils.isOperand;
import static function.interpretator.util.ExpressionUtils.obtainOperatorType;
import static function.interpretator.util.FunctionParseUtils.parseFunctionName;

import java.util.ArrayList;
import java.util.List;

import function.interpretator.io.FunctionFileDAO;
import function.interpretator.model.Function;

public class FunctionValidator {

    private final FunctionFileDAO functionFileDAO;

    public FunctionValidator(FunctionFileDAO functionFileDAO) {
        this.functionFileDAO = functionFileDAO;
    }

    public void validateParameters(List<String> functionArgs, List<String> functionParams) {
        List<String> operands = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        for (String parameter : functionParams) {
            if (isOperand(parameter)) {
                operands.add(parameter);
            } else {
                obtainOperatorType(parameter);
                operators.add(parameter);
            }

            if (parameter.contains("func")) {
                String funcName = parseFunctionName(parameter);
                Function innerFunction = functionFileDAO.read(funcName);
                List<String> funcParams = innerFunction.getArguments();
                if (funcParams == null) {
                    throw new IllegalArgumentException(
                            format("No such function defined: %s", funcName));
                }
                operands.addAll(funcParams);
            }
        }

        int argsSize = functionArgs.size();
        int operandsSize = operands.size();

        if (argsSize != operandsSize) {
            List<String> notDefinedOperands = new ArrayList<>(operands);
            notDefinedOperands.removeAll(functionArgs);
            throw new IllegalStateException(
                    format("Failed to define function.The following operands: '%s' are not defined",
                            notDefinedOperands));
        }
    }
}
