package aoc;


/**
 * Handy validation for the Main classes.
 */
public class Validation {

    public static <T> void validate(T expected, T result) {
        if (expected.equals(result)) {
            System.out.println("[It is a valid answer]");
        } else {
            System.out.println("[The answer is INVALID]");
        }
    }
    
}
