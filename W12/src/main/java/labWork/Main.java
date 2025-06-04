package labWork;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface TestClass {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test {}

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java labWork.Main <Class-Name>");
            return;
        }

        String className = args[0];

        try{
            Class<?> loadedClass = Class.forName(className);
            analizeClass(loadedClass);
            runTestMethods(loadedClass);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void analizeClass(Class<?> clazz) {
        System.out.println("Analizing " + clazz.getSimpleName());

        System.out.println("Modifiers: " + clazz.getModifiers());

        System.out.println("Superclass: " + clazz.getSuperclass());

        Class<?>[] interfaces = clazz.getInterfaces();
        if(interfaces.length > 0) {
            System.out.println("Interfaces: " + Arrays.toString(interfaces));
        }

        System.out.println("\nMethods:");

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(getMethodPrototype(method));
        }
    }

    private static String getMethodPrototype(Method method) {
        StringBuilder prototype = new StringBuilder();
        //modifiers
        prototype.append(Modifier.toString(method.getModifiers())).append("");

        //return type
        prototype.append(method.getReturnType().getSimpleName()).append("");

        //name
        prototype.append(method.getName()).append("(");

        //parameters
        Class<?>[] params = method.getParameterTypes();
        for(int i=0; i<params.length; i++) {
            prototype.append(params[i].getSimpleName());
            if(i < params.length-1) {
                prototype.append(", ");
            }
        }


        prototype.append(")");

        //exceptions

        Class<?>[] exceptions = method.getExceptionTypes();
        if(exceptions.length > 0) {
            prototype.append(" throws ");
            for(int i=0; i<exceptions.length; i++) {
                prototype.append(exceptions[i].getSimpleName());
                if(i < exceptions.length-1) {
                    prototype.append(", ");
                }
            }
        }

        return prototype.toString();
    }

    private static void runTestMethods(Class<?> clazz) throws Exception {
        System.out.println("/nRunning Tests:");
        Method[] methods = clazz.getDeclaredMethods();
        int testRuns = 0;
        int testPasses = 0;

        for(Method method : methods) {
            if(method.isAnnotationPresent(Test.class)) {
                if(Modifier.isStatic(method.getModifiers()) && method.getParameterCount() == 0) {
                    testRuns++;
                    try{
                        System.out.println("Running Test: " + method.getName());
                        method.invoke(null);
                        System.out.println("Passed: " + testRuns);
                        testPasses++;
                    }
                    catch(Exception e) {
                        System.out.println("FAILURE");
                        System.err.println("Test Failed: " + method.getName());
                        e.getCause().printStackTrace();
                    }
                }
                else{
                    System.out.println("Skipping Test: " + method.getName());
                }
            }
        }

        System.out.println("\nResults: " + testRuns + "/" + testPasses);
    }
}
