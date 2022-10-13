package hexlet.code.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class Utils {
    private static final String CATALOG_URI = "src/test/resources/";
    public static Path getAbsolutePath(String path) {

        return path.indexOf("/") != -1 || path.indexOf("https") != -1
                ? Paths.get(path).toAbsolutePath()
                : Paths.get(CATALOG_URI + path).toAbsolutePath();
    }

    public static Map<String, Object> mappingFile(String filePath, ObjectMapper objectMapper) throws IOException {


        return objectMapper
                .readValue(Files.readString(getAbsolutePath(filePath)), new TypeReference<HashMap<String, Object>>() {

                });
    }

    public static boolean isValidationFormat(String filePath1, String filePath2) {
        return getFileFormat(filePath1).equals(getFileFormat(filePath2));
    }

    public static String getFileFormat(String filePath) {
        return filePath.substring(filePath.lastIndexOf(".") + 1);
    }

}
