package hexlet.code.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.utils.StatusDataElement;


import java.io.IOException;
import java.util.Map;


public class FormatterJSON {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static String createFormatToJSON(Map<String, StatusDataElement> resultDiff) throws IOException {

        /*Map<String, Object> resultMap = new TreeMap<>();


        for (Map.Entry<String, StatusDataElement> elem: resultDiff.entrySet()) {

            switch (elem.getValue().getStatus()) {
                case ADDED:
                    resultMap.put(
                            "+" + elem.getKey(),
                            elem.getValue().getOldValueElement()
                    );
                    break;
                case DELETE:
                    resultMap.put(
                            "-" + elem.getKey(),
                            elem.getValue().getOldValueElement()
                    );
                    break;
                case MODIFICATION:
                    resultMap.put(
                            "-" + elem.getKey(),
                            elem.getValue().getOldValueElement()
                    );
                    resultMap.put(
                            "+" + elem.getKey(),
                            elem.getValue().getNewValueElement()
                    );
                    break;
                case NOT_CHANGED:
                    resultMap.put(elem.getKey(), elem.getValue().getOldValueElement());
                    break;
                default:
                    throw new Error("unknown status");
            }
        }


        String result = objectMapper.writeValueAsString(resultMap);*/

        return objectMapper.writeValueAsString(resultDiff);
    }
}
