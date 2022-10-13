package hexlet.code.parser;

import com.fasterxml.jackson.databind.ObjectMapper;


public class BuilderJSON implements Builder {
    private String filePath1;
    private String filePath2;

    public BuilderJSON(String path1, String path2) {
        this.filePath1 = path1;
        this.filePath2 = path2;
    }


    @Override
    public final ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }

    @Override
    public final String getPath1() {
        return this.filePath1;
    }

    @Override
    public final String getPath2() {
        return this.filePath2;
    }


}
