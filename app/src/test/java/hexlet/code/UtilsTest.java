package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.utils.Utils.getAbsolutePath;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UtilsTest {
    @Test
    public void getAbsolutePathTest1() {
        String path = "test1.json";
        Path absolutePath = Paths.get("/Users/aleksandrkaluznyj/IdeaProjects/app/src/test/resources/test1.json");

        assertEquals(getAbsolutePath(path), absolutePath);
    }
    @Test
    public void getAbsolutePathTest2() {
        String path = "src/test/resources/test1.json";
        Path absolutePath = Paths.get("/Users/aleksandrkaluznyj/IdeaProjects/app/src/test/resources/test1.json");

        assertEquals(getAbsolutePath(path), absolutePath);
    }
}
