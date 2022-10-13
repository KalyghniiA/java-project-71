package hexlet.code.differ;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.utils.StatusDataElement;


import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class DifferJSON {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static String createDifferToJSON(Map<String, StatusDataElement> resultDiff) throws IOException {

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


        String result = objectMapper.writeValueAsString(resultMap);

        return result;
    }
}
