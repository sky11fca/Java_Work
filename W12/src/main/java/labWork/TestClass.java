//package labWork;



public class TestClass {
    public static void examplePublicMethod(){
        System.out.println("Public method");
    }

    private static void examplePrivateMethod(){
        System.out.println("Private method");
    }

    @Test
    public static void testPublicMethod(){
        System.out.println("Test1");
    }

    @Test
    public static void testPublicMethod2(){
        System.out.println("Test2");
        throw new RuntimeException("FAILURE");
    }

    @Test
    public void testWithNoStaticMethod(){

    }
    @Test
    public static void testWithStaticMethod(){

    }
}
