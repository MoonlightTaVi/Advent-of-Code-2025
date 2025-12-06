package aoc.p002.ids;


/**
 * A strategy to validate an ID. <br>
 * An ID is a Long number stored in a String.
 */
public interface IdValidatorIF {

    /**
     * Validates an ID.
     * @param id ID to validate.
     * @return true if the ID is valid, false otherwise.
     */
    public boolean validate(String id);
    
}
