package function.interpretator.action;

import function.interpretator.io.FunctionFileDAO;
import function.interpretator.model.Function;

public class PrintFunctionsAction implements IFunctionAction {

    private final FunctionFileDAO functionFileDAO;

    public PrintFunctionsAction(FunctionFileDAO functionFileDAO) {
        this.functionFileDAO = functionFileDAO;
    }

    @Override
    public void execute() {
        for (Function function : functionFileDAO.readAll()) {
            System.out.println(function.getName() + " -> " + function.getArguments());
        }
    }
}
