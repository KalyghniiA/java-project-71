package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static hexlet.code.utils.Parser.parsing;
import static hexlet.code.utils.Utils.mappingFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private ObjectMapper mapperJSON = new ObjectMapper();
    private ObjectMapper mapperYAML = new YAMLMapper();

    @Test
    public void differTest1() throws IOException {
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


        String testingResult = parsing(
                mappingFile(mapperJSON, "test1.json"),
                mappingFile(mapperJSON, "test2.json"));

        assertEquals(result, testingResult);
    }

    @Test
    public void differTest2() throws IOException {
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


        String testingResult = parsing(
                mappingFile(mapperJSON, "test2.json"),
                mappingFile(mapperJSON, "test1.json"));

        assertEquals(result, testingResult);
    }

    @Test
    public void differTest3() throws IOException {
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


        String testingResult = parsing(
                mappingFile(mapperJSON, "test1.json"),
                mappingFile(mapperJSON, "test1.json"));

        assertEquals(result, testingResult);
    }

    @Test
    public void differTestYAML() throws IOException {
        String result = "{\n"
                + "    ansible_host: localhost\n"
                + "    app_fqdn: suyos01.svl.ibm.com\n"
                + "    custom_hostname: suyos01c.svl.ibm.com\n"
                + "    default_gateway: true\n"
                + "  - external_connection_enabled: false\n"
                + "  + external_connection_enabled: true\n"
                + "    gateway: 9.30.20.1\n"
                + "    ip: 9.30.16.143\n"
                + "    partner_switch: false\n"
                + "    subnet: 9.30.16.0\n"
                + "  - timezone: America/Boston\n"
                + "  + timezone: America/Los_Angeles\n"
                + "    vlan: 4080\n"
                + "}";

        String testResult = parsing(
                mappingFile(mapperYAML, "test1.yml"),
                mappingFile(mapperYAML, "test2.yml"));

        assertEquals(result, testResult);
    }
}
