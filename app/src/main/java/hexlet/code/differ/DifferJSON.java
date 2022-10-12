package hexlet.code.differ;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.utils.StatusDataElement;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class DifferJSON {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static File createDifferToJSON(Map<String, StatusDataElement> resultDiff) throws IOException {
        File resultFile = new File("src/test/resources/result.json");
        Map<String, Object> resultMap = new TreeMap<>();


        for (Map.Entry<String, StatusDataElement> elem: resultDiff.entrySet()) {

            switch (elem.getValue().getStatus()) {
                case ADDED:
                    resultMap.put(
                            "+" + elem.getKey(),
                            elem.getValue().getValueElement()
                    );
                    break;
                case DELETE:
                    resultMap.put(
                            "-" + elem.getKey(),
                            elem.getValue().getValueElement()
                    );
                    break;
                case MODIFICATION:
                    resultMap.put(
                            "-" + elem.getKey(),
                            elem.getValue().getValueElement()
                    );
                    resultMap.put(
                            "+" + elem.getKey(),
                            elem.getValue().getNewValueElement()
                    );
                    break;
                case NOT_CHANGED:
                    resultMap.put(elem.getKey(), elem.getValue().getValueElement());
                    break;
                default:
                    throw new Error("unknown status");
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
