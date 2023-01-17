package function.interpretator;

import java.util.Scanner;

import function.interpretator.action.DefineFunctionAction;
import function.interpretator.action.IFunctionAction;
import function.interpretator.action.PrintFunctionsAction;
import function.interpretator.action.SolveFunctionAction;
import function.interpretator.io.FunctionFileDAO;

public class Main {

    public static void main(String[] args) {
        FunctionFileDAO dao = new FunctionFileDAO();
        while (true) {
            try {
                System.out.println("Options: \n" +
                        "1. DEFINE function\n" +
                        "2. SOLVE function \n" +
                        "3. PRINT functions \n" +
                        "4. EXIT");

                int choice = Integer.parseInt(new Scanner(System.in).nextLine());

                if (choice == 1) {
                    IFunctionAction defineAction = new DefineFunctionAction(dao);
                    defineAction.execute();
                } else if (choice == 2) {
                    IFunctionAction action = new SolveFunctionAction(dao);
                    action.execute();
                } else if (choice == 3) {
                    IFunctionAction action = new PrintFunctionsAction(dao);
                    action.execute();
                } else if (choice == 4) {
                    return;
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

