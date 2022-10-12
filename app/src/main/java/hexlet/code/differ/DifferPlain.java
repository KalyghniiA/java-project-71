package hexlet.code.differ;

import hexlet.code.utils.StatusDataElement;

import java.util.Map;


public class DifferPlain {

    public static String createDifferToPlain(Map<String, StatusDataElement> resultDiff) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, StatusDataElement> elem: resultDiff.entrySet()) {

            Object value1 = elem.getValue().getValueElement() == null
                ? elem.getValue().getValueElement()
                : elem.getValue().getValueElement().toString().charAt(0) == '{'
                    || elem.getValue().getValueElement().toString().charAt(0) == '['
                    ? "[complex value]"
                    : elem.getValue().getValueElement();

            Object value2 = elem.getValue().getNewValueElement() == null
                    ? elem.getValue().getNewValueElement()
                    : elem.getValue().getNewValueElement().toString().charAt(0) == '{'
                    || elem.getValue().getNewValueElement().toString().charAt(0) == '['
                    ? "[complex value]"
                    : elem.getValue().getNewValueElement();

            switch (elem.getValue().getStatus()) {
                case ADDED:
                    result.append("\nProperty \'"
                            + elem.getKey()
                            + "\' was added with value: \'"
                            + value1
                            + "\'");
                    break;
                case DELETE:
                    result.append("\nProperty \'" + elem.getKey() + "\' was removed");
                    break;
                case MODIFICATION:
                    result.append("\nProperty \'"
                            + elem.getKey()
                            + "\' was updated. From "
                            + value1
                            + " to "
                            + value2);
                    break;
                case NOT_CHANGED:
                    break;
                default:
                    throw new Error("unknown status");
            }
        }


        return result.toString();
    }
}
