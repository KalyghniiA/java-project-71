package hexlet.code.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class BuilderYML implements Builder {
    private String filePath1;
    private String filePath2;

    public BuilderYML(String path1, String path2) {
        this.filePath1 = path1;
        this.filePath2 = path2;
    }

    @Override
    public final ObjectMapper createObjectMapper() {
        return new YAMLMapper();
    }

    @Override
    public final String getPath1() {
        return filePath1;
    }

    @Override
    public final String getPath2() {
        return filePath2;
    }
}
