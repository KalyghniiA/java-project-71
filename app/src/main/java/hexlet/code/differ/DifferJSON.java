package hexlet.code.differ;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.utils.StatusData;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.utils.Parser.parsing;

public class DifferJSON {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static File createDifferToJSON(Map<String, Object> file1, Map<String, Object> file2) throws IOException {
        File resultFile = new File("src/test/resources/result.json");
        Map<String, Object> resultMap = new TreeMap<>();


        Map<String, StatusData> parseResult = parsing(file1, file2);

        for (Map.Entry<String, StatusData> elem: parseResult.entrySet()) {
            switch (elem.getValue()) {
                case ADDED:
                    resultMap.put(
                            "+" + elem.getKey(),
                            file2.get(elem.getKey())
                    );
                    break;
                case DELETE:
                    resultMap.put(
                            "-" + elem.getKey(),
                            file1.get(elem.getKey())
                    );
                    break;
                case MODIFICATION:
                    resultMap.put(
                            "-" + elem.getKey(),
                            file1.get(elem.getKey())
                    );
                    resultMap.put(
                            "+" + elem.getKey(),
                            file2.get(elem.getKey())
                    );
                    break;
                default:
                    resultMap.put(elem.getKey(), file1.get(elem.getKey()));
            }
        }

        if (!resultFile.createNewFile()) {
            resultFile.delete();
            resultFile.createNewFile();
        }

        objectMapper.writeValue(resultFile, resultMap);

        System.out.println("create file differ to path" + resultFile.getAbsolutePath());

        return resultFile;
    }
}
