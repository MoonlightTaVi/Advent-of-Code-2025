package aoc25;

import org.junit.jupiter.api.*;

import aoc.p002.ids.*;
import aoc.p002.ranges.*;


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
    
    @Test
    public void subpartIsRepeated() {
        RepeatedValidator rptValidator = new RepeatedValidator();
        String id = "121212";
        String part = "12";
        boolean result = rptValidator.isRepeated(id, part);
        Assertions.assertTrue(result);
    }
    
    @Test
    public void repeatedValidation() {
        validator.validator = new RepeatedValidator();
        String[] ids = { "11", "121121", "12122" };
        validator.validateAll(ids);
        // First & second are invalid
        Assertions.assertEquals(2, validator.invalidIds.size());
    }
    
    @Test
    public void repeatedValidationSum() {
        validator.validator = new RepeatedValidator();
        
        for (String range : ranges) {
            IdRange idRange = new IdRange(range);
            validator.validateRange(idRange);
        }
        long sum = validator.sumInvalid();
        long expected = 4174379265L;
        
        Assertions.assertEquals(expected, sum);
    }
}
