package hexlet.code.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;

public class BuilderYML extends Builder {

    public BuilderYML(String path1, String path2) throws IOException {
        super(path1, path2);
    }

    @Override
    public final ObjectMapper getObjectMapper() {
        return new YAMLMapper();
    }

}
