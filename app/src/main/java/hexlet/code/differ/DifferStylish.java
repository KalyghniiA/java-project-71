package hexlet.code.differ;


import hexlet.code.utils.StatusDataElement;

import java.util.Map;



public class DifferStylish {
    public static String createDifferToStylish(Map<String, StatusDataElement> resultDiff) {
        StringBuilder result = new StringBuilder("{");


        for (Map.Entry<String, StatusDataElement> elem: resultDiff.entrySet()) {
            switch (elem.getValue().getStatus()) {
                case ADDED:
                    result.append("\n  + " + elem.getKey() + ": " + elem.getValue().getValueElement());
                    break;
                case DELETE:
                    result.append("\n  - " + elem.getKey() + ": " + elem.getValue().getValueElement());
                    break;
                case MODIFICATION:
                    result.append("\n  - " + elem.getKey() + ": " + elem.getValue().getValueElement());
                    result.append("\n  + " + elem.getKey() + ": " + elem.getValue().getNewValueElement());
                    break;
                case NOT_CHANGED:
                    result.append("\n    " + elem.getKey() + ": " + elem.getValue().getValueElement());
                    break;
                default:
                    throw new Error("unknown status");
            }
        }

        result.append("\n}");

        return result.toString();
    }
}
