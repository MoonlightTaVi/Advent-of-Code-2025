package aoc.p002.ranges;

import java.util.ArrayList;
import java.util.List;

import aoc.p002.ids.IdValidatorIF;


/**
 * Validates each ID in IdRange and collects all invalid ones.
 * Can be used to retrieve the sum of invalid IDs.
 */
public class RangeValidator {
    /** ID validation strategy. */
    public IdValidatorIF strategy;
    /** 
     * All the invalid IDs are collected here 
     * after the {@code validate()} call.
     */
    public List<String> invalidIds = new ArrayList<>();
    
    
    /**
     * Creates an instance of RangeValidator with specific validation
     * strategy.
     * @param strategy Validator for a single ID.
     */
    public RangeValidator(IdValidatorIF validator) {
        this.strategy = validator;
    }


    /**
     * Validates a range of IDs (as IdRange) and collects 
     * the invalid ones to a List<String>.
     * @param idRange Range of IDs.
     */
    public void validateRange(IdRange idRange) {
        for (String id : idRange) {
            validate(id);
        }
    }


    /**
     * Validates a range of IDs (as String[]) and collects 
     * the invalid ones to a List<String>.
     * @param idRange Range of IDs.
     */
    public void validateAll(String... ids) {
        for (String id : ids) {
            validate(id);
        }
    }
    
    /**
     * Validates a single ID and collects it if it is invalid.
     * @param id ID to validate.
     * @return true if the ID is valid.
     */
    public boolean validate(String id) {
        boolean isValid = strategy.validate(id);
        if (!isValid) {
            invalidIds.add(id);
        }
        return isValid;
    }
    
    
    /**
     * Returns the sum of all invalid IDs.
     * @return The sum of {@code invalidIds}.
     * @see #invalidIds
     */
    public long sumInvalid() {
        long sum = 0;
        for (String id : invalidIds) {
            sum += Long.valueOf(id);
        }
        return sum;
    }
}
