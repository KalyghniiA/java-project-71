package hexlet.code;

import hexlet.code.parser.BuilderJSON;
import hexlet.code.parser.BuilderYML;
import hexlet.code.utils.StatusDataElement;

import java.io.IOException;
import java.util.Map;

import static hexlet.code.differ.DifferJSON.createDifferToJSON;
import static hexlet.code.differ.DifferPlain.createDifferToPlain;
import static hexlet.code.differ.DifferStylish.createDifferToStylish;
import static hexlet.code.utils.Utils.getFileFormat;
import static hexlet.code.utils.Utils.isValidationFormat;

public class Differ {
    /*private String filePath1;
    private String filePath2;
    public Differ(String path1, String path2) {
        this.filePath1 = path1;
        this.filePath2 = path2;
    }*/

    /*public final void generate(String format) throws IOException {
        if (!isValidationFormat(filePath1, filePath2)) {
            throw new Error("different formats");
        }

        Map<String, StatusDataElement> resultDiff;

        switch (getFileFormat(filePath2)) {
            case "json":
                resultDiff = new BuilderJSON(filePath1, filePath2).parsing();
                break;
            case "yml":
                resultDiff = new BuilderYML(filePath1, filePath2).parsing();
                break;
            default:
                throw new Error("format not specified");
        }

        switch (format.toLowerCase()) {
            case "json":
                System.out.println(createDifferToJSON(resultDiff));
                break;
            case "plain":
                System.out.println(createDifferToPlain(resultDiff));
                break;
            case "stylish":
                System.out.println(createDifferToStylish(resultDiff));
                break;
            default:
                throw new Error("format not specified");
        }
    }*/

    public static String generate(String filePath1, String filePath2) throws IOException {
        if (!isValidationFormat(filePath1, filePath2)) {
            throw new Error("different formats");
        }

        Map<String, StatusDataElement> resultDiff;

        switch (getFileFormat(filePath2)) {
            case "json":
                resultDiff = new BuilderJSON(filePath1, filePath2).parsing();
                break;
            case "yml":
                resultDiff = new BuilderYML(filePath1, filePath2).parsing();
                break;
            default:
                throw new Error("format not specified");
        }


        return createDifferToStylish(resultDiff);
    }

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        if (!isValidationFormat(filePath1, filePath2)) {
            throw new Error("different formats");
        }

        System.out.println(getFileFormat(filePath1));

        Map<String, StatusDataElement> resultDiff;

        switch (getFileFormat(filePath1)) {
            case "json":
                resultDiff = new BuilderJSON(filePath1, filePath2).parsing();
                break;
            case "yml":
                resultDiff = new BuilderYML(filePath1, filePath2).parsing();
                break;
            default:
                throw new Error("format not specified");
        }

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
