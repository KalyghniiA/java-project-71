package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.utils.StatusData;

import java.io.IOException;
import java.util.Map;

import static hexlet.code.utils.Parser.parsing;
import static hexlet.code.utils.Utils.mappingFile;

public class Differ {

    private static ObjectMapper mapperJSON = new ObjectMapper();
    private static ObjectMapper mapperYAML = new YAMLMapper();
    private static String filePath1;
    private static String filePath2;
    private static String format;
    public Differ(String path1, String path2, String form) {
        this.filePath1 = path1;
        this.filePath2 = path2;
        this.format = form;
    }

    public static void generate() throws IOException {
        if (!filePath1.substring(filePath1.lastIndexOf(".")).equals(filePath2.substring(filePath2.lastIndexOf(".")))) {
            throw new Error("different formats");
        }

        switch (format.toLowerCase()) {
            default:
                System.out.println(createDifferToStylish(mappingFile(filePath1), mappingFile(filePath2)));
        }
    }

    static String createDifferToStylish(Map<String, Object> file1, Map<String, Object> file2) {
        StringBuilder result = new StringBuilder("{");
        Map<String, StatusData> parseResult = parsing(file1, file2);

        for (Map.Entry<String, StatusData> elem: parseResult.entrySet()) {
            switch (elem.getValue()) {
                case ADDED:
                    result.append("\n  + " + elem.getKey() + ": " + file2.get(elem.getKey()));
                    break;
                case DELETE:
                    result.append("\n  - " + elem.getKey() + ": " + file1.get(elem.getKey()));
                    break;
                case MODIFICATION:
                    result.append("\n  - " + elem.getKey() + ": " + file1.get(elem.getKey()));
                    result.append("\n  + " + elem.getKey() + ": " + file2.get(elem.getKey()));
                    break;
                default:
                    result.append("\n    " + elem.getKey() + ": " + file1.get(elem.getKey()));
            }
        }

        result.append("\n}");

        return result.toString();
    }
}
