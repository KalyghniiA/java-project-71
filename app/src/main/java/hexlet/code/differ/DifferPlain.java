package hexlet.code.differ;

import hexlet.code.utils.StatusDataElement;

import java.util.List;
import java.util.Map;


public class DifferPlain {

    public static String createDifferToPlain(Map<String, StatusDataElement> resultDiff) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, StatusDataElement> elem: resultDiff.entrySet()) {

            Object value1 = getValue(elem.getValue().getOldValueElement());
            Object value2 = getValue(elem.getValue().getNewValueElement());

            switch (elem.getValue().getStatus()) {
                case ADDED:
                    result
                            .append(String.format("Property \'%s\' was added with value: %s", elem.getKey(), value1))
                            .append("\n");
                    break;
                case DELETE:
                    result
                            .append(String.format("Property \'%s\' was removed", elem.getKey()))
                            .append("\n");
                    break;
                case MODIFICATION:
                    result
                            .append(String.format(
                                    "Property \'%s\' was updated. From %s to %s",
                                    elem.getKey(),
                                    value1,
                                    value2))
                            .append("\n");

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
        return String.valueOf(value);
    }
}
