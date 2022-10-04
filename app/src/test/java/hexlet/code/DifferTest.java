package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static hexlet.code.differ.DifferPlain.createDifferToPlain;
import static hexlet.code.differ.DifferStylish.createDifferToStylish;
import static hexlet.code.utils.Utils.mappingFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void differTestJSON1() throws IOException {
        String result = "{\n"
                + "    age: 37\n"
                + "  + cat: dog\n"
                + "    children1: Маша\n"
                + "    children2: Игорь\n"
                + "    children3: Таня\n"
                + "  - dog: Jack\n"
                + "  + dog: null\n"
                + "  - married: true\n"
                + "  + married: false\n"
                + "    mother: Ольга\n"
                + "    name: Иван\n"
                + "}";


        String testingResult = createDifferToStylish(
                mappingFile("test1.json"),
                mappingFile("test2.json"));

        assertEquals(result, testingResult);
    }

    @Test
    public void differTestJSON2() throws IOException {
        String result = "{\n"
                + "    age: 37\n"
                + "  - cat: dog\n"
                + "    children1: Маша\n"
                + "    children2: Игорь\n"
                + "    children3: Таня\n"
                + "  - dog: null\n"
                + "  + dog: Jack\n"
                + "  - married: false\n"
                + "  + married: true\n"
                + "    mother: Ольга\n"
                + "    name: Иван\n"
                + "}";


        String testingResult = createDifferToStylish(
                mappingFile("test2.json"),
                mappingFile("test1.json"));

        assertEquals(result, testingResult);
    }

    @Test
    public void differTestJSON3() throws IOException {
        String result = "{\n"
                + "    age: 37\n"
                + "    children1: Маша\n"
                + "    children2: Игорь\n"
                + "    children3: Таня\n"
                + "    dog: Jack\n"
                + "    married: true\n"
                + "    mother: Ольга\n"
                + "    name: Иван\n"
                + "}";


        String testingResult = createDifferToStylish(
                mappingFile("test1.json"),
                mappingFile("test1.json"));

        assertEquals(result, testingResult);
    }

    @Test
    public void differTestYAML() throws IOException {
        String result = "{\n"
                + "    ansible_host: localhost\n"
                + "    app_fqdn: suyos01.svl.ibm.com\n"
                + "    custom_hostname: suyos01c.svl.ibm.com\n"
                + "  - default_gateway1: {vlan=1080, gateway=9.30.20.1}\n"
                + "  + default_gateway1: {vlan=1280, gateway=9.30.20.1}\n"
                + "  - default_gateway2: {vlan=4080, gateway1=9.30.20.1, gateway2=9.30.20.1}\n"
                + "  + default_gateway2: {vlan=4180, gateway1=9.30.20.1, gateway2=9.30.20.1}\n"
                + "    default_gateway3: {vlan=4010, gateway=9.30.20.1}\n"
                + "  - external_connection_enabled: false\n"
                + "  + external_connection_enabled: true\n"
                + "    ip: 9.30.16.143\n"
                + "    partner_switch: false\n"
                + "    subnet: 9.30.16.0\n"
                + "  - timezone: America/Boston\n"
                + "  + timezone: America/Los_Angeles\n"
                + "}";

        String testResult = createDifferToStylish(
                mappingFile("test1.yml"),
                mappingFile("test2.yml"));

        assertEquals(result, testResult);
    }

    @Test
    public void differNestedStructureJSON() throws IOException {
        String result = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";

        String testResult = createDifferToStylish(
                mappingFile("test3.json"),
                mappingFile("test4.json")
        );

        assertEquals(result, testResult);
    }

    @Test
    public void differJSONPlainFormat() throws IOException {
        String result = "\n"
                + "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'null'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: 'null'\n"
                + "Property 'obj1' was added with value: 'null'\n"
                + "Property 'setting1' was updated. From Some value to Another value\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to none";

        String testResult = createDifferToPlain(
                mappingFile("test3.json"),
                mappingFile("test4.json")
        );

        assertEquals(result, testResult);
    }
}
