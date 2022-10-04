package hexlet.code.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class Utils {
    private static final String CATALOG_URI = "src/test/resources/";
    public static Path getAbsolutePath(String path) {
        return path.indexOf("/") != -1
                ? Paths.get(path).toAbsolutePath()
                : Paths.get(CATALOG_URI + path).toAbsolutePath();
    }

    public static Map<String, Object> mappingFile(ObjectMapper objectMapper, String filePath) throws IOException {
        return objectMapper
                .readValue(getAbsolutePath(filePath).toFile(), new TypeReference<HashMap<String, Object>>() {

                });
    }
}
