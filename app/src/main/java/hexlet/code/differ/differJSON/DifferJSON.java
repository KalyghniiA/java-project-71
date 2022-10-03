package hexlet.code.differ.differJSON;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

import static hexlet.code.utils.Utils.getAbsolutePath;

public class DifferJSON {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String differ(String filePath1, String filePath2) throws IOException {

        Map<String, Object> file1 = mappingFile(filePath1);
        Map<String, Object> file2 = mappingFile(filePath2);

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

    private static Map<String, Object> mappingFile(String filePath) throws IOException {
        return objectMapper
                .readValue(getAbsolutePath(filePath).toFile(), new TypeReference<SortedMap<String, Object>>() {

                });
    }
}
