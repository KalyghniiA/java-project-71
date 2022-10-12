package hexlet.code.differ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.utils.StatusDataElement;

import java.io.IOException;
import java.util.Map;

import static hexlet.code.differ.DifferJSON.createDifferToJSON;
import static hexlet.code.differ.DifferPlain.createDifferToPlain;
import static hexlet.code.differ.DifferStylish.createDifferToStylish;
import static hexlet.code.utils.Parser.parsing;
import static hexlet.code.utils.Utils.createObjectMapper;
import static hexlet.code.utils.Utils.isValidationFormat;
import static hexlet.code.utils.Utils.mappingFile;

public class Differ {

    private static ObjectMapper mapperJSON = new ObjectMapper();
    private static ObjectMapper mapperYAML = new YAMLMapper();
    private String filePath1;
    private String filePath2;
    public Differ(String path1, String path2) {
        this.filePath1 = path1;
        this.filePath2 = path2;
    }

    public final void generate(String format) throws IOException {
        if (!isValidationFormat(filePath1, filePath2)) {
            throw new Error("different formats");
        }

        ObjectMapper objectMapper = createObjectMapper(filePath1);
        Map<String, StatusDataElement> resultDiff = parsing(
                mappingFile(filePath1, objectMapper),
                mappingFile(filePath2, objectMapper));

        switch (format.toLowerCase()) {
            case "json":
                createDifferToJSON(resultDiff);
                break;
            case "plain":
                System.out.println(createDifferToPlain(resultDiff));
                break;
            case "stylish":
                System.out.println(createDifferToStylish(resultDiff));
            default:
                throw new Error("format not specified");
        }
    }



}
