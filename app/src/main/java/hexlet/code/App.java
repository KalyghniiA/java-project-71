package hexlet.code;

import hexlet.code.differ.Differ;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "App", mixinStandardHelpOptions = true, version = "checksum 4.6.3")
public class App implements Callable {
    @Parameters(paramLabel = "filePath1", description = "path to first file")
    private static String filePath1;
    @Parameters(paramLabel = "filePath2", description = "path to second file")
    private static String filePath2;
    @Option(names = {"-f", "--format"}, description = {"output format [default: stylish]"})
    private static String format = "stylish";


    @Override
    public final Object call() throws Exception {
        Differ differ = new Differ(filePath1, filePath2, format);
        differ.generate();
        return null;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);

        System.exit(exitCode);

    }


}
