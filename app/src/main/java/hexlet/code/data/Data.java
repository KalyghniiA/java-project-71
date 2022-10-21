package hexlet.code.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.utils.Utils.getFileFormat;


public class Data {
    private String filePath1;
    private String filePath2;
    private static final String CATALOG_URI = "src/test/resources/";

    public Data(String path1, String path2) {
        this.filePath1 = path1;
        this.filePath2 = path2;
    }


    private Path getAbsolutePath(String filePath) {

        return filePath.indexOf("/") != -1 || filePath.indexOf("https") != -1
                ? Paths.get(filePath).toAbsolutePath()
                : Paths.get(CATALOG_URI + filePath).toAbsolutePath();
    }

    private String generateFileToString(String filePath) throws IOException {
        return Files.readString(getAbsolutePath(filePath));
    }

    public final String getFirstData() throws IOException {
        return generateFileToString(filePath1);
    }

    public final String getSecondData() throws IOException {
        return generateFileToString(filePath2);
    }

    public final String getFormat() {
        return getFileFormat(filePath1);
    }
}
