package function.interpretator.util;

import static java.lang.String.format;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import function.interpretator.io.FunctionFileDAO;
import function.interpretator.model.Function;
import function.interpretator.structures.MyLinkedList;

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
        MyLinkedList<String> arguments = new MyLinkedList<>();
        for (String argument : split(functionArguments, ",")) {
            arguments.add(argument);
        }
        return arguments;
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
        return asList(split(parameters, " "));
    }

    private static String[] getFunctionDefinitionParts(String definition) {
        String[] parts = definition.split("[()]");

        if (parts.length != 2) {
            throw new IllegalArgumentException(format(INVALID_FUNCTION_DEFINITION_MSG, definition));
        }

        return parts;
    }

    public static String[] split(String string, String delem) {
        List<String> list = new ArrayList<>();
        char[] charArr = string.toCharArray();
        char[] delemArr = delem.toCharArray();
        int counter = 0;
        for (int i = 0; i < charArr.length; i++) {
            int k = 0;
            for (int j = 0; j < delemArr.length; j++) {
                if (charArr[i + j] == delemArr[j]) {
                    k++;
                } else {
                    break;
                }
            }
            if (k == delemArr.length) {
                String s = "";
                while (counter < i) {
                    s += charArr[counter];
                    counter++;
                }
                counter = i = i + k;
                list.add(s);
            }
        }
        String s = "";
        if (counter < charArr.length) {
            while (counter < charArr.length) {
                s += charArr[counter];
                counter++;
            }
            list.add(s);
        }
        return list.toArray(new String[list.size()]);
    }
}
