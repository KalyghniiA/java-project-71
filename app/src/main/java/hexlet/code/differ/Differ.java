package hexlet.code.differ;

import hexlet.code.differ.differJSON.DifferJSON;

import java.io.IOException;

public class Differ {
    private static String filePath1;
    private static String filePath2;
    private static String format;
    public Differ(String path1, String path2, String form) {
        this.filePath1 = path1;
        this.filePath2 = path2;
        this.format = form;
    }

    public static void generate() throws IOException {

        if (!filePath1.substring(filePath1.lastIndexOf(".")).equals(filePath2.substring(filePath2.lastIndexOf(".")))) {
            throw new Error("different formats");
        }

        switch (format) {
            case "json":
                System.out.println(DifferJSON.differ(filePath1, filePath2));
                break;
            case "stylish":
                System.out.println(DifferJSON.differ(filePath1, filePath2));
                break;
            default:
                System.out.println("!");
        }
    }

}
