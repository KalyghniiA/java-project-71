package hexlet.code.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    private static final String CATALOG_URI = "src/test/resources/";
    public static Path getAbsolutePath(String path) {
        return path.indexOf("/") != -1
                ? Paths.get(path).toAbsolutePath()
                : Paths.get(CATALOG_URI + path).toAbsolutePath();
    }
}
