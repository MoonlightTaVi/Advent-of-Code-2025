package aoc;


/**
 * Handy validation and console output methods for the Main classes.
 */
public class Validation {
    
    /**
     * Prints a message with the title of the task and the answer to
     * the task to the console.
     * @param title Title of the task.
     * @param printedResult Calculated solution result of the task.
     */
    public static void print(String title, Object printedResult) {
        System.out.printf("[%s]%n", title);
        System.out.printf("The answer is: %s%n", printedResult.toString());
    }

    
    /**
     * Compares the expected result of the task to the obtained result.
     * The idea is similar to a unit test.
     * <br> The method is useful when the result is already known, but
     * some changes were made to the code, and it is necessary to check
     * that the answer is still the same.
     * @param <T>
     * @param expected
     * @param result
     */
    public static <T> void validate(T expected, T result) {
        if (expected.equals(result)) {
            System.out.println("[It is a valid answer]");
        } else {
            System.out.println("[The answer is INVALID]");
        }
    }
    
}
