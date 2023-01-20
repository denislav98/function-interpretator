package function.interpretator.io;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import function.interpretator.model.Function;

public class FunctionFileDAO implements IFunctionFileDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionFileDAO.class);
    private static final Gson GSON = new Gson();

    private static final Path PATH_TO_FUNCTIONS = Paths
            .get("src", "main", "resources", "functions");

    @Override
    public Function read(String functionName) {
        try (Reader fileReader = new FileReader(getFunctionFilePath(functionName))) {
            return GSON.fromJson(fileReader, Function.class);
        } catch (IOException e) {
            LOGGER.error(format("Failed to read function '%s'", functionName));
            throw new RuntimeException(e);
        }
    }

    public void delete(String functionName) {
        try {
            String functionFilePath = getFunctionFilePath(functionName);
            Files.delete(Paths.get(functionFilePath));
        } catch (IOException e) {
            LOGGER.error(format("Failed to delete function '%s'", functionName));
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Function> readAll() {
        try (Stream<Path> paths = Files
                .walk(PATH_TO_FUNCTIONS)) { // четем всички файлове под /functions папката
            return paths.filter(Files::isRegularFile) // проверка за файл
                    .map(path -> { // за всеки път на файл
                        // /Users/denislavminchev/function-interpretator/src/main/resources/functions/func4.json
                        String functionName = path.toFile().getName()
                                .replace(".json", ""); // екстрактваме само името на функцията
                        return read(functionName); // четем функцията
                    })
                    .collect(toList());
        } catch (IOException e) {
            LOGGER.error("Failed to read all functions", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(String functionName, List<String> functionArgs) {
        try (Writer fileWriter = new FileWriter(getFunctionFilePath(functionName))) {
            GSON.toJson(new Function(functionName, functionArgs), fileWriter);
        } catch (IOException e) {
            LOGGER.error(format("Failed to write function '%s' with arguments '%s' ", functionName,
                    functionArgs));
            throw new RuntimeException(e);
        }
    }

    private String getFunctionFilePath(String functionName) {
        return PATH_TO_FUNCTIONS.toAbsolutePath() + "/" + functionName + ".json";
    }

}
