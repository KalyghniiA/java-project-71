package hexlet.code.builder;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.data.Data;
import hexlet.code.utils.StatusData;
import hexlet.code.utils.StatusDataElement;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.Objects;
import java.util.HashMap;



public abstract class Builder {
    private Map<String, Object> file1;
    private Map<String, Object> file2;

    public Builder(String path1, String path2) throws IOException {
        this.file1 = getStructure(path1);
        this.file2 = getStructure(path2);
    }


    public final Map<String, StatusDataElement> diff() {
        Map<String, StatusDataElement> result = new TreeMap<>();

        Set<String> keys = new HashSet<>(file1.keySet());
        keys.addAll(file2.keySet());

        for (String key: keys) {
            result.put(key, getStatusElement(key));

            /*if (file1.containsKey(key) && file2.containsKey(key)) {
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
            }*/
        }

        return result;
    }

    private StatusDataElement getStatusElement(String key) {
        if (file1.containsKey(key) && file2.containsKey(key)) {
            if (Objects.equals(file1.get(key), file2.get(key))) {
                return new StatusDataElement(StatusData.NOT_CHANGED, file2.get(key));
            } else {
                return new StatusDataElement(StatusData.MODIFICATION, file1.get(key), file2.get(key));
            }
        }
        if (!file1.containsKey(key)) {
            return new StatusDataElement(StatusData.ADDED, file2.get(key));
        }
        if (!file2.containsKey(key)) {
            return new StatusDataElement(StatusData.DELETE, file1.get(key));
        }
        throw new Error("Error not element");
    }

    final Map<String, Object> getStructure(String filePath) throws IOException {

        return getObjectMapper()
                .readValue(
                        Files.readString(new Data(filePath).getAbsolutePath()),
                        new TypeReference<HashMap<String, Object>>() { });
    }

    public abstract ObjectMapper getObjectMapper();

}
