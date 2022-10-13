package hexlet.code.differ;


import hexlet.code.utils.StatusDataElement;

import java.util.Map;



public class DifferStylish {
    public static String createDifferToStylish(Map<String, StatusDataElement> resultDiff) {
        StringBuilder result = new StringBuilder("{\n");


        for (Map.Entry<String, StatusDataElement> elem: resultDiff.entrySet()) {
            switch (elem.getValue().getStatus()) {
                case ADDED:
                    result.append("  + " + elem.getKey() + ": " + elem.getValue().getValueElement() + "\n");
                    break;
                case DELETE:
                    result.append("  - " + elem.getKey() + ": " + elem.getValue().getValueElement() + "\n");
                    break;
                case MODIFICATION:
                    result.append("  - " + elem.getKey() + ": " + elem.getValue().getValueElement() + "\n");
                    result.append("  + " + elem.getKey() + ": " + elem.getValue().getNewValueElement() + "\n");
                    break;
                case NOT_CHANGED:
                    result.append("    " + elem.getKey() + ": " + elem.getValue().getValueElement() + "\n");
                    break;
                default:
                    throw new Error("unknown status");
            }
        }

        result.append("}");

        return result.toString();
    }
}
