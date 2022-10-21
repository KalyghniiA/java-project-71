package hexlet.code.builder;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.utils.StatusData;
import hexlet.code.utils.StatusDataElement;

import java.io.IOException;
import java.util.TreeSet;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Objects;




public abstract class Builder {
    private Map<String, Object> file1;
    private Map<String, Object> file2;

    public Builder(String firstFile, String secondFile) throws IOException {
        this.file1 = getStructure(firstFile);
        this.file2 = getStructure(secondFile);
    }


    public final Map<String, StatusDataElement> diff() {
        Map<String, StatusDataElement> result = new LinkedHashMap<>();

        Set<String> keys = new TreeSet<>(file1.keySet());
        keys.addAll(file2.keySet());

        for (String key: keys) {
            result.put(key, getStatusElement(key));

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

    final Map<String, Object> getStructure(String file) throws IOException {

        return getObjectMapper()
                .readValue(
                       file,
                        new TypeReference<LinkedHashMap<String, Object>>() { });
    }

    public abstract ObjectMapper getObjectMapper();

}
