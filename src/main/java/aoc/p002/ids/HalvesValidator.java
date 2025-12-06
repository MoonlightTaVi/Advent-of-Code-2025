package aoc.p002.ids;


/**
 * This strategy assumes that an ID is invalid if it is made of
 * one number repeated twice (i.e. left and right parts are equal).
 */
public class HalvesValidator implements IdValidatorIF {

    @Override
    public boolean validate(String id) {
        if (id.length() % 2 == 1) {
            return true;
        }
        
        String[] halves = split(id);
        // Must not be equal
        return !halves[0].equals(halves[1]);
    }

    /**
     * Splits an ID into halves by its middle index.
     * @param id ID to split.
     * @return String[2] array with two halves of the ID.
     */
    public String[] split(String id) {
        String[] halves = new String[2];
        halves[0] = id.substring(0, id.length() / 2);
        halves[1] = id.substring(id.length() / 2);
        return halves;
    }
    
}
