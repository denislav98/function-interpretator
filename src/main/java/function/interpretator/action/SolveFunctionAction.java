package function.interpretator.action;

import static java.util.stream.Collectors.toMap;
import static function.interpretator.ExpressionTreeHelper.buildExpressionTree;
import static function.interpretator.ExpressionTreeHelper.solveExpressionTree;
import static function.interpretator.util.ExpressionUtils.filterOperands;
import static function.interpretator.util.FunctionParseUtils.parseFunctionArguments;
import static function.interpretator.util.FunctionParseUtils.parseFunctionName;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

import function.interpretator.io.FunctionFileDAO;
import function.interpretator.model.Function;
import function.interpretator.model.Node;

public class SolveFunctionAction implements IFunctionAction {

    private static final String ENTER_FUNCTION_NAME_TO_SOLVE_MSG = "Enter function definition to solve e.g func1(0,1)";

    private static final Scanner scanner = new Scanner(System.in);

    private final FunctionFileDAO functionFileDAO;

    public SolveFunctionAction(FunctionFileDAO functionFileDAO) {
        this.functionFileDAO = functionFileDAO;
    }

    @Override
    public void execute() {
        System.out.println(ENTER_FUNCTION_NAME_TO_SOLVE_MSG);
        String functionDefinition = scanner.nextLine(); // extract only func1
        String functionName = parseFunctionName(functionDefinition);
        List<String> functionArgs = parseFunctionArguments(functionDefinition);

        Function function = functionFileDAO.read(functionName);
        List<String> functionParams = function.getArguments();

        Node tree = buildExpressionTree(functionParams);
        Map<String, String> paramsPerArguments = getFunctionArgsWithParams(functionArgs,
                functionParams);

        boolean result = solveExpressionTree(tree, paramsPerArguments);
        System.out.printf("Result is: %s%n", result ? 1 : 0);
    }

    private Map<String, String> getFunctionArgsWithParams(List<String> args, List<String> params) {
        List<String> operands = filterOperands(params);
        if (args.size() != operands.size()) {
            throw new IllegalStateException(
                    "Mismatch in function arguments and expected parameters");
        }
        return IntStream.range(0, args.size()).boxed().collect(toMap(operands::get, args::get));
    }
}
