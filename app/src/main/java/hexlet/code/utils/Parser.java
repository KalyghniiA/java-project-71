package hexlet.code.utils;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Parser {

    public static String parsing(Map<String, Object> file1, Map<String, Object> file2) {
        String answer = "{";

        Set<String> keys = new TreeSet<>(file1.keySet());
        keys.addAll(file2.keySet());

        for (String key: keys) {
            if (file1.containsKey(key) && file2.containsKey(key)) {
                if (file1.get(key) == null) {
                    if (file2.get(key) != null) {
                        answer += "\n  - " + key + ": null";
                        answer += "\n  + " + key + ": " + file2.get(key);
                        continue;
                    }
                    answer += "\n    " + key + ": null";
                    continue;
                }
                if (!file1.get(key).equals(file2.get(key))) {
                    answer += "\n  - " + key + ": " + file1.get(key);
                    answer += "\n  + " + key + ": " + file2.get(key);
                    continue;
                }
                answer += "\n    " + key + ": " + file1.get(key);
            }
            if (!file1.containsKey(key)) {
                answer += "\n  + " + key + ": " + file2.get(key);
            }
            if (!file2.containsKey(key)) {
                answer += "\n  - " + key + ": " + file1.get(key);
            }
        }


        answer += "\n}";

        return answer;
    }

}
