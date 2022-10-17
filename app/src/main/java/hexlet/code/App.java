package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "App", mixinStandardHelpOptions = true, version = "checksum 4.6.3")
public class App implements Callable {
    @Parameters(paramLabel = "filePath1", description = "path to first file")
    private static String filePath1;
    @Parameters(paramLabel = "filePath2", description = "path to second file")
    private static String filePath2;
    @Option(names = {"-f", "--format"}, description = {"output format [default: stylish]"}, defaultValue = "stylish")
    private static String format;


    @Override
    public final Object call() throws Exception {
        try {
            System.out.println(Differ.generate(filePath1, filePath2, format));
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);

        System.exit(exitCode);

    }


}
