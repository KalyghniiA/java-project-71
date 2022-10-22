package hexlet.code.formatter;


import hexlet.code.utils.StatusDataElement;

import java.util.Map;



public class FormatterStylish {
    public static String createFormatToStylish(Map<String, StatusDataElement> resultDiff) {
        StringBuilder result = new StringBuilder("{\n");


        for (Map.Entry<String, StatusDataElement> elem: resultDiff.entrySet()) {
            switch (elem.getValue().getStatus()) {
                case ADDED:
                    result
                            .append(String.format("  + %s: %s", elem.getKey(), elem.getValue().getOldValueElement()))
                            .append("\n");
                    break;
                case DELETE:
                    result
                            .append(String.format("  - %s: %s", elem.getKey(), elem.getValue().getOldValueElement()))
                            .append("\n");
                    break;
                case MODIFICATION:
                    result
                            .append(String.format("  - %s: %s", elem.getKey(), elem.getValue().getOldValueElement()))
                            .append("\n");
                    result
                            .append(String.format("  + %s: %s", elem.getKey(), elem.getValue().getNewValueElement()))
                            .append("\n");
                    break;
                case NOT_CHANGED:
                    result
                            .append(String.format("    %s: %s", elem.getKey(), elem.getValue().getOldValueElement()))
                            .append("\n");
                    break;
                default:
                    throw new Error("unknown status");
            }
        }

        result.append("}");

        return result.toString();
    }
}
