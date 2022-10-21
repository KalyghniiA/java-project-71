package hexlet.code;

import hexlet.code.builder.BuilderJSON;
import hexlet.code.builder.BuilderYML;
import hexlet.code.data.Data;
import hexlet.code.utils.StatusDataElement;

import java.io.IOException;
import java.util.Map;

import static hexlet.code.differ.DifferJSON.createDifferToJSON;
import static hexlet.code.differ.DifferPlain.createDifferToPlain;
import static hexlet.code.differ.DifferStylish.createDifferToStylish;
import static hexlet.code.utils.Utils.getFileFormat;
import static hexlet.code.utils.Utils.isValidationFormat;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        if (!isValidationFormat(filePath1, filePath2)) {
            throw new Error("different formats");
        }

        Data data = new Data(filePath1, filePath2);

        return formatResult(format, createResultDiff(data.getFirstData(), data.getSecondData()));
    }

    private static Map<String, StatusDataElement> createResultDiff(String filePath1, String filePath2)
            throws IOException {
        switch (getFileFormat(filePath1)) {
            case "json":
                return new BuilderJSON(filePath1, filePath2).diff();
            case "yml":
                return new BuilderYML(filePath1, filePath2).diff();
            default:
                throw new Error("format not specified!!");
        }
    }

    private static String formatResult(String format,  Map<String, StatusDataElement> resultDiff) throws IOException {
        System.out.println(format);
        switch (format.toLowerCase()) {
            case "json":
                return createDifferToJSON(resultDiff);
            case "plain":
                return createDifferToPlain(resultDiff);
            case "stylish":
                return createDifferToStylish(resultDiff);
            default:
                throw new Error("format not specified");
        }
    }

}
