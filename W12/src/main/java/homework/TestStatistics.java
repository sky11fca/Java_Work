package homework;

public class TestStatistics {
    public int totalTestClasses;
    public int totalTests;
    public int passedTests;
    public int failedTests;

    @Override
    public String toString() {
        return String.format(
                "Test Classes: %d\nTotal Tests: %d\nPassed: %d\nFailed: %d\nSuccess Rate: %.2f%%",
                totalTestClasses,
                totalTests,
                passedTests,
                failedTests,
                (totalTests == 0) ? 0 : (passedTests * 100.0 / totalTests)
        );
    }
}