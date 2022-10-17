package hexlet.code.builder;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public class BuilderJSON extends Builder {

    public BuilderJSON(String path1, String path2) throws IOException {
        super(path1, path2);
    }

    @Override
    public final ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

}
