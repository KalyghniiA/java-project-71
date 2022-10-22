package hexlet.code;


import hexlet.code.data.Data;

import hexlet.code.parser.Parser;
import hexlet.code.parser.ParserJSON;
import hexlet.code.parser.ParserYML;
import hexlet.code.utils.StatusDataElement;
import java.io.IOException;
import java.util.Map;

import static hexlet.code.builder.Builder.diff;
import static hexlet.code.formatter.FormatterJSON.createFormatToJSON;
import static hexlet.code.formatter.FormatterPlain.createFormatToPlain;
import static hexlet.code.formatter.FormatterStylish.createFormatToStylish;
import static hexlet.code.utils.Utils.isValidationFormat;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        if (!isValidationFormat(filePath1, filePath2)) {
            throw new Error("different formats");
        }

        Data data1 = new Data(filePath1);
        Data data2 = new Data(filePath2);
        Parser parser = getParser(data1.getFormat());
        Map<String, StatusDataElement> parserResult =
                diff(
                        parser.parse(data1.getData()),
                        parser.parse(data2.getData()));


        return formatResult(format, parserResult);
    }

    private static Parser getParser(String format) {
        switch (format) {
            case "json":
                return new ParserJSON();
            case "yml":
                return new ParserYML();
            default:
                throw new Error("format not specified!!");
        }
    }

    private static String formatResult(String format,  Map<String, StatusDataElement> resultDiff) throws IOException {
        switch (format.toLowerCase()) {
            case "json":
                return createFormatToJSON(resultDiff);
            case "plain":
                return createFormatToPlain(resultDiff);
            case "stylish":
                return createFormatToStylish(resultDiff);
            default:
                throw new Error("format not specified");
        }
    }

}
