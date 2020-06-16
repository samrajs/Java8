package lambda;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

public class FileListDemo {
    public static void betterWay() {
        List<File> files =
                Stream.of(new File(".").listFiles())
                        .flatMap(file -> file.listFiles() == null ?
                                Stream.of(file) : Stream.of(file.listFiles()))
                        .collect(toList());
        files.stream().forEach(System.out::println);

        System.out.println("Count: " + files.size());
    }

    public static void main(String args[]) throws Exception {
        Files.list(Paths.get("."))
                .forEach(System.out::println);

        Files.newDirectoryStream(
                Paths.get("./src/main/java/lambda"), path -> path.toString().endsWith(".java"))
                .forEach(System.out::println);

        final File[] files = new File("./src/main/java/lambda").listFiles(File::isFile);
        Arrays.stream(files).forEach( file -> System.out.println(file.getName()));


        betterWay();
    }
}
