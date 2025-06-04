package homework;

import java.io.File;
import java.util.List;

public class Main
{
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Usage: java homework.Main <Class-Name/jar/folder>");
            return;
        }

        String path = args[0];
        File input = new File(path);

        List<Class<?>> classes = ClassAnalyser.loadClasses(input);
        TestStatistics stats = TestRunner.runTests(classes);

        System.out.println("\nTest Statistics: ");
        System.out.println(stats);
    }
}
