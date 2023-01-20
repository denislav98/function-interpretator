package function.interpretator.action;

import java.util.Scanner;

import function.interpretator.io.FunctionFileDAO;

public class DeleteFunctionAction implements IFunctionAction {

    private static final String ENTER_FUNCTION_NAME_TO_DELETE_MSG = "Enter function name to delete: ";
    private static final Scanner scanner = new Scanner(System.in);

    private final FunctionFileDAO functionFileDAO;

    public DeleteFunctionAction(FunctionFileDAO functionFileDAO) {
        this.functionFileDAO = functionFileDAO;
    }

    @Override
    public void execute() {
        System.out.println(ENTER_FUNCTION_NAME_TO_DELETE_MSG);
        String functionName = scanner.nextLine();
        functionFileDAO.delete(functionName);
    }
}
