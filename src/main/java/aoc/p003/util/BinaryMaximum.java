package aoc.p003.util;


/**
 * Finds the index of the highest digit character in a String.
 */
public class BinaryMaximum {

    /**
     * Returns index of the highest leftmost digit in a number String.
     * @param source String that represents a Long number.
     * @return Index of the highest leftmost digit.
     */
    public int greatestIndex(String source) {
        if (!canSplit(source)) {
            return 0;
        }
        int maxIndex = 0;
        
        String[] parts = split(source);
        int offset = parts[0].length();
        
        int leftIndex = greatestIndex(parts[0]);
        int rightIndex = offset + greatestIndex(parts[1]);
        
        char leftChar = source.charAt(leftIndex);
        char rightChar = source.charAt(rightIndex);
        if (leftChar >= rightChar) {
            maxIndex = leftIndex;
        } else {
            maxIndex = rightIndex;
        }
        
        return maxIndex;
    }
    
    /**
     * Checks if a string can be halved.
     * @param source Source string to be tested.
     * @return true if the String contains at least 2 characters.
     */
    public boolean canSplit(String source) {
        return source.length() >= 2;
    }
    
    /**
     * Splits a string into two parts. <br>
     * Right part may be bigger that the first by 1 character.
     * @param source String to split.
     * @return String[2] that contains two parts of the string.
     */
    public String[] split(String source) {
        int length = source.length();
        int half = length / 2;
        String left = source.substring(0, half);
        String right = source.substring(half);
        return new String[] { left, right };
    }
    
}
