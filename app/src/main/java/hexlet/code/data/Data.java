package hexlet.code.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.utils.Utils.getFileFormat;


public final class Data {
    private String filePath;
    private static final String CATALOG_URI = "src/test/resources/";

    public Data(String path1) {
        this.filePath = path1;

    }


    private Path getAbsolutePath(String path) {

        return path.indexOf("/") != -1 || path.indexOf("https") != -1
                ? Paths.get(path).toAbsolutePath()
                : Paths.get(CATALOG_URI + path).toAbsolutePath();
    }

    private String generateFileToString(String path) throws IOException {
        return Files.readString(getAbsolutePath(path));
    }

    public String getData() throws IOException {
        return generateFileToString(filePath);
    }


    public String getFormat() {
        return getFileFormat(filePath);
    }

}
