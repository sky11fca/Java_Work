package homework;


import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static TestStatistics runTests(List<Class<?>> classes) {
        TestStatistics stats = new TestStatistics();

        for (Class<?> clazz : classes) {
            // Check if class is public and has @Test annotation
            if (Modifier.isPublic(clazz.getModifiers())) {
                if (clazz.isAnnotationPresent(Test.class)) {
                    stats.totalTestClasses++;
                    runTestMethodsInClass(clazz, stats);
                } else {
                    // Check for test methods in non-annotated classes
                    runTestMethodsInClass(clazz, stats);
                }
            }
        }

        return stats;
    }

    private static void runTestMethodsInClass(Class<?> clazz, TestStatistics stats) {
        System.out.println("\nAnalyzing class: " + clazz.getName());
        printClassPrototype(clazz);

        try {
            Object instance = null;
            if (!Modifier.isAbstract(clazz.getModifiers())) {
                instance = clazz.getDeclaredConstructor().newInstance();
            }

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    stats.totalTests++;
                    System.out.println("\nRunning test: " + method.getName());

                    try {
                        if (Modifier.isStatic(method.getModifiers())) {
                            invokeStaticMethod(method);
                        } else if (instance != null) {
                            invokeInstanceMethod(instance, method);
                        }
                        stats.passedTests++;
                    } catch (Exception e) {
                        System.err.println("Test failed: " + e.getMessage());
                        stats.failedTests++;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error processing class " + clazz.getName() + ": " + e.getMessage());
        }
    }

    private static void invokeStaticMethod(Method method) throws Exception {
        if (method.getParameterCount() == 0) {
            method.invoke(null);
        } else {
            Object[] args = generateMockArguments(method);
            method.invoke(null, args);
        }
    }

    private static void invokeInstanceMethod(Object instance, Method method) throws Exception {
        if (method.getParameterCount() == 0) {
            method.invoke(instance);
        } else {
            Object[] args = generateMockArguments(method);
            method.invoke(instance, args);
        }
    }

    private static Object[] generateMockArguments(Method method) {
        Class<?>[] paramTypes = method.getParameterTypes();
        Object[] args = new Object[paramTypes.length];

        for (int i = 0; i < paramTypes.length; i++) {
            args[i] = getMockValue(paramTypes[i]);
        }

        return args;
    }

    private static Object getMockValue(Class<?> type) {
        if (type == int.class || type == Integer.class) {
            return 0;
        } else if (type == String.class) {
            return "testString";
        } else if (type == boolean.class || type == Boolean.class) {
            return false;
        } else if (type == long.class || type == Long.class) {
            return 0L;
        } else if (type == double.class || type == Double.class) {
            return 0.0;
        } else if (type == float.class || type == Float.class) {
            return 0.0f;
        }
        return null;
    }

    private static void printClassPrototype(Class<?> clazz) {
        System.out.println("\nClass prototype:");
        System.out.println(Modifier.toString(clazz.getModifiers()) + " class " + clazz.getSimpleName());

        System.out.println("\nMethods:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("  " + method.toString());
        }
    }
}