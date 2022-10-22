package hexlet.code.builder;

import hexlet.code.utils.StatusData;
import hexlet.code.utils.StatusDataElement;

import java.io.IOException;
import java.util.TreeSet;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Objects;


public class Builder {

    public static Map<String, StatusDataElement> diff(
            Map<String, Object> data1,
            Map<String, Object> data2)
            throws IOException {
        Map<String, StatusDataElement> result = new LinkedHashMap<>();

        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key: keys) {
            if (data1.containsKey(key) && data2.containsKey(key)) {
                result.put(
                        key,
                        Objects.equals(data1.get(key), data2.get(key))
                                ? new StatusDataElement(StatusData.NOT_CHANGED, data2.get(key))
                                : new StatusDataElement(StatusData.MODIFICATION, data1.get(key), data2.get(key)));
                continue;
            }
            if (!data1.containsKey(key)) {
                result.put(key, new StatusDataElement(StatusData.ADDED, data2.get(key)));
            }
            if (!data2.containsKey(key)) {
                result.put(key, new StatusDataElement(StatusData.DELETE, data1.get(key)));
            }

        }

        return result;
    }

}
