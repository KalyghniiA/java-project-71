package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;

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
            case "json":
                System.out.println(parsing(mappingFile(mapperJSON, filePath1), mappingFile(mapperJSON, filePath2)));
                break;
            case "yml":
                System.out.println(parsing(mappingFile(mapperYAML, filePath1), mappingFile(mapperYAML, filePath2)));
                break;
            default:
                System.out.println("!");
        }


    }

}
