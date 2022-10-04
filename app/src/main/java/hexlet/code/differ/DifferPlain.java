package hexlet.code.differ;

import hexlet.code.utils.StatusData;

import java.util.Map;

import static hexlet.code.utils.Parser.parsing;

public class DifferPlain {

    public static String createDifferToPlain(Map<String, Object> file1, Map<String, Object> file2) {
        StringBuilder result = new StringBuilder();
        Map<String, StatusData> parseResult = parsing(file1, file2);

        for (Map.Entry<String, StatusData> elem: parseResult.entrySet()) {
            Object value1 = file1.get(elem.getKey()) == null
                    ? file1.get(elem.getKey())
                    : file1.get(elem.getKey()).toString().charAt(0) == '{'
                        || file1.get(elem.getKey()).toString().charAt(0) == '['
                        ? "[complex value]"
                        : file1.get(elem.getKey());


            Object value2 = file2.get(elem.getKey()) == null
                    ? file2.get(elem.getKey())
                    : file2.get(elem.getKey()).toString().charAt(0) == '{'
                        || file2.get(elem.getKey()).toString().charAt(0) == '['
                        ? "[complex value]"
                        : file2.get(elem.getKey());


            switch (elem.getValue()) {
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
                default:
            }
        }


        return result.toString();
    }
}
