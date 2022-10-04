package hexlet.code.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

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

    public static Map<String, Object> mappingFile(String filePath) throws IOException {
        ObjectMapper objectMapper = filePath.substring(filePath.lastIndexOf(".")).equals("yml")
                ? new ObjectMapper()
                : new YAMLMapper();

        return objectMapper
                .readValue(getAbsolutePath(filePath).toFile(), new TypeReference<HashMap<String, Object>>() {

                });
    }

    /*
    при добавлении новых форматов
    private static ObjectMapper createObjectWrapper(String filePath) {
        String typeFile = filePath.substring(filePath.lastIndexOf("."));

        switch(typeFile) {
            case "yml":
                return new YAMLMapper();
            default:
                return new ObjectMapper();
        }
    }*/
}
