package hexlet.code.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ParserJSON implements Parser {
    @Override
    public Map<String, Object> parse(String content) throws IOException {
        return new ObjectMapper()
                .readValue(
                        content,
                        new TypeReference<LinkedHashMap<String, Object>>() { });
    }
}
