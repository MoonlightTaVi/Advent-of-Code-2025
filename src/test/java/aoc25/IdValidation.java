package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aoc.p002.ids.HalvesValidator;
import aoc.p002.ranges.IdParser;
import aoc.p002.ranges.IdRange;
import aoc.p002.ranges.RangeValidator;


/**
 * JUnit tests for day 2.
 */
public class IdValidation {
    static String[] ranges;
    
    @BeforeAll
    public static void setup() {
        ranges = new IdParser("002.txt").idRanges;
    }
    
    
    RangeValidator validator;
    HalvesValidator idValidator = new HalvesValidator();;
    
    @BeforeEach
    public void reset() {
        validator = new RangeValidator(idValidator);
    }
    
    
    @Test
    public void splitTest() {
        String id = "11";
        String[] halves = idValidator.split(id);
        
        Assertions.assertEquals("1", halves[0]);
        Assertions.assertEquals("1", halves[1]);
    }
    
    @Test
    public void invalidationSuccess() {
        for (String range : new IdRange(ranges[0])) {
            validator.validate(range);
        }
        String[] expected = { "11", "22" };
        String[] resulted = validator.invalidIds.toArray(String[]::new);
        Assertions.assertArrayEquals(expected, resulted);
    }
    
    @Test
    public void sumIsValid() {
        for (String range : ranges) {
            IdRange idRange = new IdRange(range);
            validator.validateRange(idRange);
        }
        long sum = validator.sumInvalid();
        long expected = 1227775554;
        
        Assertions.assertEquals(expected, sum);
    }
}
