package hexlet.code.utils;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Parser {

    public static Map<String, StatusDataElement> parsing(Map<String, Object> file1, Map<String, Object> file2) {
        Map<String, StatusDataElement> result = new TreeMap<>();
        Set<String> keys = new HashSet<>(file1.keySet());
        keys.addAll(file2.keySet());

        for (String key: keys) {
            if (file1.containsKey(key) && file2.containsKey(key)) {
                if (file1.get(key) == null) {
                    result.put(key, file2.get(key) == null
                            ? new StatusDataElement(StatusData.NOT_CHANGED, file2.get(key))
                            : new StatusDataElement(StatusData.MODIFICATION, file1.get(key), file2.get(key)));
                    continue;
                }
                result.put(
                        key,
                        file1.get(key).equals(file2.get(key))
                                ? new StatusDataElement(StatusData.NOT_CHANGED, file2.get(key))
                                : new StatusDataElement(StatusData.MODIFICATION, file1.get(key), file2.get(key)));
                continue;
            }
            if (!file1.containsKey(key)) {
                result.put(key, new StatusDataElement(StatusData.ADDED, file2.get(key)));
            }
            if (!file2.containsKey(key)) {
                result.put(key, new StatusDataElement(StatusData.DELETE, file1.get(key)));
            }
        }

        return result;
    }
}
