package hexlet.code;

import hexlet.code.differ.differJSON.DifferJSON;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferJSONTest {

    private final DifferJSON differJSON = new DifferJSON();

    @Test
    public void differTest1() throws IOException {
        String result = "{\n"
                + "    age: 37\n"
                + "  - cat: dog\n"
                + "    children: [Маша, Игорь, Таня]\n"
                + "    dog: null\n"
                + "  - married: false\n"
                + "  + married: true\n"
                + "    mother: {name=Ольга, age=58}\n"
                + "    name: Иван\n"
                + "}";


        String testingResult = differJSON.differ("test2.json", "test1.json");

        assertEquals(result, testingResult);
    }

    @Test
    public void differTest2() throws IOException {
        String result = "{\n"
                + "    age: 37\n"
                + "  + cat: dog\n"
                + "    children: [Маша, Игорь, Таня]\n"
                + "    dog: null\n"
                + "  - married: true\n"
                + "  + married: false\n"
                + "    mother: {name=Ольга, age=58}\n"
                + "    name: Иван\n"
                + "}";


        String testingResult = differJSON.differ("test1.json", "test2.json");
        assertEquals(result, testingResult);
    }

    @Test
    public void differTest3() throws IOException {
        String result = "{\n"
                + "    age: 37\n"
                + "    children: [Маша, Игорь, Таня]\n"
                + "    dog: null\n"
                + "    married: true\n"
                + "    mother: {name=Ольга, age=58}\n"
                + "    name: Иван\n"
                + "}";


        String testingResult = differJSON.differ("test1.json", "test1.json");
        assertEquals(result, testingResult);
    }
}
