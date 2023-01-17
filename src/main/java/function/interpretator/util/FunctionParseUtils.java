package function.interpretator.util;

import static java.lang.String.format;
import static java.util.Arrays.asList;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import function.interpretator.io.FunctionFileDAO;
import function.interpretator.model.Function;

public class FunctionParseUtils {

    private static final String INVALID_FUNCTION_DEFINITION_MSG = "Expected function definition is <func(a,b)>. Provided: %s";

    private FunctionFileDAO functionFileDAO;

    public FunctionParseUtils(FunctionFileDAO functionFileDAO) {
        this.functionFileDAO = functionFileDAO;
    }

    public static String parseFunctionName(String definition) {
        return getFunctionDefinitionParts(definition)[0];
    }

    public static List<String> parseFunctionArguments(String definition) {
        String functionArguments = getFunctionDefinitionParts(definition)[1];
        return new LinkedList<>(asList(functionArguments.split(",")));
    }

    public List<String> parseFunctionParameters(String parameters) {
        List<String> paramsArr = parseFunctionParametersFromStdin(parameters);

        List<String> finalParams = new LinkedList<>();
        for (String param : paramsArr) {
            if (param.contains("func")) {
                String functionName = parseFunctionName(param);
                Function function = functionFileDAO.read(functionName);
                List<String> functionParams = function.getArguments();
                if (functionParams != null) {
                    finalParams.addAll(functionParams);
                }
            } else {
                finalParams.add(param);
            }
        }
        return finalParams;
    }

    private static List<String> parseFunctionParametersFromStdin(String parameters) {
        return asList(parameters.split(" "));
    }

    private static String[] getFunctionDefinitionParts(String definition) {
        String[] parts = definition.split("[()]");

        if (parts.length != 2) {
            throw new IllegalArgumentException(format(INVALID_FUNCTION_DEFINITION_MSG, definition));
        }

        return parts;
    }
}
