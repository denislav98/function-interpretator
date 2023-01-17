package function.interpretator.io;

import java.util.List;

import function.interpretator.model.Function;

public interface IFunctionFileDao {

    Function read(String functionName);

    List<Function> readAll();

    void write(String functionName, List<String> functionArgs);
}
