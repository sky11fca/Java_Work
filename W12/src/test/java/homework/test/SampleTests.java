package homework.test;

import org.junit.Test;

public class SampleTests {
    @Test
    public void testAddition() {
        System.out.println("Testing addition");
        assert 1 + 1 == 2;
    }

    @Test
    public void testWithParameters(int value, String text) {
        System.out.println("Testing with parameters: " + value + ", " + text);
    }

    public void notATest() {
        System.out.println("This should not run");
    }
}