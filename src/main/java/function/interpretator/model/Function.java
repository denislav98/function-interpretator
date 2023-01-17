package function.interpretator.model;

import java.util.List;

public class Function {

    private String name;
    private List<String> arguments;

    public Function() {
    }

    public Function(String name, List<String> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
