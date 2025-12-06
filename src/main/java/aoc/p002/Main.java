package aoc.p002;

import aoc.p002.ids.*;
import aoc.p002.ranges.*;


/**
 * https://adventofcode.com/2025/day/2 <br> <br>
 * An ID is a Long number represented as String. <br>
 * An ID range is two IDs separated with a '-' sign. <br>
 * An ID may be invalid (dependent on one of the validation strategies).
 * <br>
 * The answer to the task is the Long sum of the invalid IDs.
 * @see HalvesValidator
 * @see RepeatedValidator
 */
public class Main {

    public static void main(String... args) {
        RangeValidator validator;
        validator = new RangeValidator(new RepeatedValidator());
        
        String[] ranges = new IdParser("002.txt").idRanges;
        for (String range : ranges) {
            IdRange idRange = new IdRange(range);
            validator.validateRange(idRange);
        }
        System.out.printf(
                "Sum of invalid ids (using %s): %d",
                validator.strategy.getClass().getSimpleName(),
                validator.sumInvalid()
                );
    }
    
}
