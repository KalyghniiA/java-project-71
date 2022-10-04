package hexlet.code.utils;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Parser {

    public static Map<String, StatusData> parsing(Map<String, Object> file1, Map<String, Object> file2) {
        Map<String, StatusData> result = new TreeMap<>();
        Set<String> keys = new HashSet<>(file1.keySet());
        keys.addAll(file2.keySet());

        for (String key: keys) {
            if (file1.containsKey(key) && file2.containsKey(key)) {
                if (file1.get(key) == null) {
                    result.put(key, file2.get(key) == null ? StatusData.NOT_CHANGED : StatusData.MODIFICATION);
                    continue;
                }
                result.put(
                        key,
                        file1.get(key).equals(file2.get(key)) ? StatusData.NOT_CHANGED : StatusData.MODIFICATION);
                continue;
            }
            if (!file1.containsKey(key)) {
                result.put(key, StatusData.ADDED);
            }
            if (!file2.containsKey(key)) {
                result.put(key, StatusData.DELETE);
            }
        }

        return result;
    }
}
