package hexlet.code.differ;

import hexlet.code.utils.StatusDataElement;

import java.util.List;
import java.util.Map;


public class DifferPlain {

    public static String createDifferToPlain(Map<String, StatusDataElement> resultDiff) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, StatusDataElement> elem: resultDiff.entrySet()) {

            Object value1 = getValue(elem.getValue().getValueElement());


            Object value2 = getValue(elem.getValue().getNewValueElement());

            switch (elem.getValue().getStatus()) {
                case ADDED:
                    result.append("Property \'"
                            + elem.getKey()
                            + "\' was added with value: "
                            + value1
                            + "\n");
                    break;
                case DELETE:
                    result.append("Property \'" + elem.getKey() + "\' was removed\n");
                    break;
                case MODIFICATION:
                    result.append("Property \'"
                            + elem.getKey()
                            + "\' was updated. From "
                            + value1
                            + " to "
                            + value2
                            + "\n");
                    break;
                case NOT_CHANGED:
                    break;
                default:
                    throw new Error("unknown status");
            }
        }


        return result.toString().trim();
    }

    private static Object getValue(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value;
    }
}
