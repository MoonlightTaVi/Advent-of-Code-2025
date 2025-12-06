package aoc.p002;

import aoc.p002.ids.*;
import aoc.p002.ranges.*;


/**
 * https://adventofcode.com/2025/day/2
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
