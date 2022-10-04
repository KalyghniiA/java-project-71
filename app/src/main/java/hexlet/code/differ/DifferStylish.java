package hexlet.code.differ;

import hexlet.code.utils.StatusData;

import java.util.Map;

import static hexlet.code.utils.Parser.parsing;

public class DifferStylish {
    public static String createDifferToStylish(Map<String, Object> file1, Map<String, Object> file2) {
        StringBuilder result = new StringBuilder("{");
        Map<String, StatusData> parseResult = parsing(file1, file2);

        for (Map.Entry<String, StatusData> elem: parseResult.entrySet()) {
            switch (elem.getValue()) {
                case ADDED:
                    result.append("\n  + " + elem.getKey() + ": " + file2.get(elem.getKey()));
                    break;
                case DELETE:
                    result.append("\n  - " + elem.getKey() + ": " + file1.get(elem.getKey()));
                    break;
                case MODIFICATION:
                    result.append("\n  - " + elem.getKey() + ": " + file1.get(elem.getKey()));
                    result.append("\n  + " + elem.getKey() + ": " + file2.get(elem.getKey()));
                    break;
                default:
                    result.append("\n    " + elem.getKey() + ": " + file1.get(elem.getKey()));
            }
        }

        result.append("\n}");

        return result.toString();
    }
}
