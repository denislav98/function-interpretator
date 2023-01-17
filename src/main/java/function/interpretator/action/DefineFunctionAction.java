package function.interpretator.action;

import static function.interpretator.util.FunctionParseUtils.parseFunctionArguments;
import static function.interpretator.util.FunctionParseUtils.parseFunctionName;

import java.util.List;
import java.util.Scanner;

import function.interpretator.io.FunctionFileDAO;
import function.interpretator.util.FunctionParseUtils;
import function.interpretator.validators.FunctionValidator;

public class DefineFunctionAction implements IFunctionAction {

    private static final String ENTER_FUNCTION_DEFINITION_MSG = "Enter function definition e.g func1(a,b)";
    private static final String ENTER_FUNCTION_PARAMS_MSG = "Enter function params in Infix notation e.g. a b &";
    private static final String SUCCESSFULLY_DEFINED_FUNCTION_MSG = "Successfully DEFINED function: %s with parameters %s%n";

    private static final Scanner scanner = new Scanner(System.in);

    private final FunctionFileDAO functionFileDAO;
    private final FunctionValidator functionValidator;
    private final FunctionParseUtils functionParseUtils;

    public DefineFunctionAction(FunctionFileDAO functionFileDAO) {
        this.functionFileDAO = functionFileDAO;
        this.functionValidator = new FunctionValidator(functionFileDAO);
        this.functionParseUtils = new FunctionParseUtils(functionFileDAO);
    }

    @Override
    public void execute() {
        System.out.println(ENTER_FUNCTION_DEFINITION_MSG);
        String functionDefinition = scanner.nextLine();
        String functionName = parseFunctionName(functionDefinition);
        List<String> functionArgs = parseFunctionArguments(functionDefinition);

        System.out.println(ENTER_FUNCTION_PARAMS_MSG); // func2(a, b, c): "func1(a, b) | c"
        String functionParamsFromStdin = scanner.nextLine(); // "func1(a,b) | c"

        List<String> functionParams = functionParseUtils
                .parseFunctionParameters(functionParamsFromStdin);
        functionValidator.validateParameters(functionArgs, functionParams);

        functionFileDAO.write(functionName, functionParams);

        System.out.printf(SUCCESSFULLY_DEFINED_FUNCTION_MSG, functionDefinition, functionParams);
    }

}
