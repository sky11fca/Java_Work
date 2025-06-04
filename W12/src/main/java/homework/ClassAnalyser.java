package homework;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassAnalyser {
    public static List<Class<?>> loadClasses(File input) {
        List<Class<?>> classes = new ArrayList<>();

        if (input.isFile()) {
            if (input.getName().endsWith(".class")) {
                loadClassFromFile(input, classes);
            } else if (input.getName().endsWith(".jar")) {
                loadClassesFromJar(input, classes);
            }
        } else if (input.isDirectory()) {
            loadClassesFromDirectory(input, classes);
        }

        return classes;
    }

    private static void loadClassFromFile(File classFile, List<Class<?>> classes) {
        try {
            String className = getClassNameFromFile(classFile);
            Class<?> clazz = Class.forName(className);
            classes.add(clazz);
        } catch (Exception e) {
            System.err.println("Error loading class: " + e.getMessage());
        }
    }

    private static void loadClassesFromDirectory(File directory, List<Class<?>> classes) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    loadClassesFromDirectory(file, classes);
                } else if (file.getName().endsWith(".class")) {
                    loadClassFromFile(file, classes);
                }
            }
        }
    }

    private static void loadClassesFromJar(File jarFile, List<Class<?>> classes) {
        try (JarFile jar = new JarFile(jarFile)) {
            URL[] urls = {new URL("jar:file:" + jarFile.getAbsolutePath() + "!/")};
            URLClassLoader cl = URLClassLoader.newInstance(urls);

            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class")) {
                    String className = entry.getName()
                            .replace("/", ".")
                            .replace(".class", "");
                    try {
                        Class<?> clazz = cl.loadClass(className);
                        classes.add(clazz);
                    } catch (ClassNotFoundException e) {
                        System.err.println("Could not load class: " + className);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error processing JAR file: " + e.getMessage());
        }
    }

    private static String getClassNameFromFile(File classFile) {
        // This is a simplified version - adjust based on your actual classpath
        String path = classFile.getAbsolutePath();
        String classpath = System.getProperty("java.class.path");

        // Find where in the classpath the file is located
        for (String cpEntry : classpath.split(File.pathSeparator)) {
            if (path.startsWith(cpEntry)) {
                path = path.substring(cpEntry.length() + 1);
                break;
            }
        }

        path = path.replace(File.separator, ".")
                .replace(".class", "");

        return path;
    }
}
