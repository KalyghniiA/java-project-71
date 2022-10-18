package hexlet.code.data;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Data {
    private String filePath;
    private static final String CATALOG_URI = "src/test/resources/";

    public Data(String path) {
        this.filePath = path;
    }


    public final Path getAbsolutePath() {

        return filePath.indexOf("/") != -1 || filePath.indexOf("https") != -1
                ? Paths.get(filePath).toAbsolutePath()
                : Paths.get(CATALOG_URI + filePath).toAbsolutePath();
    }

}
