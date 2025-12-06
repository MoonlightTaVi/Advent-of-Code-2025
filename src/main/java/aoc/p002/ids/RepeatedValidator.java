package aoc.p002.ids;


/**
 * Day 2 part 2. <br>
 * This validation strategy treats an ID as invalid if it consists
 * of one part that is repeated 2 or more times.
 */
public class RepeatedValidator implements IdValidatorIF {

    @Override
    public boolean validate(String id) {
        boolean isInvalid = false;
        // Start from 2 sub-parts
        int divisor = 2;
        int length = id.length();
        // Repeat until a sub-part is greater
        //  than a half of the full ID
        while (length / divisor >= 1) {
            String part = getSubpart(id, divisor);
            if (isRepeated(id, part)) {
                isInvalid = true;
                break;
            }
            divisor++;
        }
        return !isInvalid;
    }
    
    /**
     * Returns the first sub-part of the ID, dividing the ID by divisor.
     * @param id Full ID.
     * @param divisor Number of desired parts within the ID.
     * @return First sub-part from the division of this ID by divisor.
     */
    public String getSubpart(String id, int divisor) {
        int partLength = id.length() / divisor;
        return id.substring(0, partLength);
    }
    
    /**
     * Checks if the full ID consists of a repeated sub-part.
     * @param fullId Full ID.
     * @param idPart Sub-part of this ID.
     * @return true if {@code fullId} is an {@code idPart} that is
     * repeated several times.
     */
    public boolean isRepeated(String fullId, String idPart) {
        int fullLength = fullId.length();
        int partLength = idPart.length();
        // Number of parts
        int divisor = fullLength / partLength;
        
        // If the division has a remainder, it is false by default
        boolean result = checkDivision(fullLength, divisor, partLength);
        
        if (result) {
            for (int i = 0; i < fullLength; i += partLength) {
                String part = fullId.substring(i, i + partLength);
                // All parts must be equal for an invalid ID
                if (!part.equals(idPart)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
    
    
    /**
     * Checks if a dividend can be divided by the divisor without any
     * remainder.
     * @param dividend Full number.
     * @param divisor A number that the dividend is divided by.
     * @param qoutient The result of the division.
     * @return true if {@code divisor * qoutient == dividend}.
     */
    private boolean checkDivision(
            int dividend, 
            int divisor, 
            int qoutient
            ) {
        return divisor * qoutient == dividend;
    }
}
