package hexlet.code.parser;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.utils.StatusData;
import hexlet.code.utils.StatusDataElement;

import java.io.IOException;
import java.util.Objects;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashMap;

import static hexlet.code.utils.Utils.getAbsolutePath;

public interface Builder {

    default Map<String, StatusDataElement> parsing() throws IOException {
        Map<String, StatusDataElement> result = new TreeMap<>();
        Map<String, Object> file1 = mappingFile(getPath1());
        Map<String, Object> file2 = mappingFile(getPath2());

        Set<String> keys = new HashSet<>(file1.keySet());
        keys.addAll(file2.keySet());

        for (String key: keys) {
            if (file1.containsKey(key) && file2.containsKey(key)) {
                result.put(
                        key,
                        Objects.equals(file1.get(key), file2.get(key))
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

    default Map<String, Object> mappingFile(String filePath) throws IOException {
        return createObjectMapper()
                .readValue(getAbsolutePath(filePath).toFile(), new TypeReference<HashMap<String, Object>>() {
                });
    }

    ObjectMapper createObjectMapper();
    String getPath1();
    String getPath2();
}
