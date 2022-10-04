package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static hexlet.code.utils.Utils.getAbsolutePath;
import static hexlet.code.utils.Utils.mappingFile;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class UtilsTest {
    @Test
    public void getAbsolutePathTest1() {
        String path = "test1.json";
        Path absolutePath = Paths.get("src/test/resources/" + path).toAbsolutePath();

        assertEquals(getAbsolutePath(path), absolutePath);
    }
    @Test
    public void getAbsolutePathTest2() {
        String path = "src/test/resources/test1.json";
        Path absolutePath = Paths.get(path).toAbsolutePath();

        assertEquals(getAbsolutePath(path), absolutePath);
    }

    @Test
    public void mappingFileJsonTest() throws IOException {
        String path = "test1.json";
        final int age = 37;

        Map<String, Object> testResult = new HashMap<>(Map.of(
                "name", "Иван",
                "age", age,
                "mother", "Ольга",
                "children1", "Маша",
                "children2", "Игорь",
                "children3", "Таня",
                "married", true,
                "dog", "Jack"));


        assertEquals(testResult, mappingFile(path));

    }
}
