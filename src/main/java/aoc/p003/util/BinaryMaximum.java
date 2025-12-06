package aoc.p003.util;


public class BinaryMaximum {

    public int greatestIndex(String source) {
        int firstMaxNumber = Integer.MIN_VALUE;
        int firstMaxIndex = 0;
        
        if (canSplit(source)) {
            int maxNumber = Integer.MIN_VALUE;
            int maxIndex = 0;
            
            String[] parts = split(source);
            int offset = parts[0].length();
            
            int leftIndex = greatestIndex(parts[0]);
            int rightIndex = offset + greatestIndex(parts[1]);
            
            char leftChar = source.charAt(leftIndex);
            char rightChar = source.charAt(rightIndex);
            
            
            if (leftChar >= rightChar) {
                maxNumber = leftChar - '0';
                maxIndex = leftIndex;
            } else {
                maxNumber = rightChar - '0';
                maxIndex = rightIndex;
            }
            
            if (maxNumber > firstMaxNumber) {
                firstMaxNumber = maxNumber;
                firstMaxIndex = maxIndex;
            } else if (maxNumber == firstMaxNumber && maxIndex < firstMaxIndex) {
                firstMaxIndex = maxIndex;
            }
        }
        
        return firstMaxIndex;
    }
    
    public boolean canSplit(String source) {
        return source.length() >= 2;
    }
    
    public String[] split(String source) {
        int length = source.length();
        int half = length / 2;
        String left = source.substring(0, half);
        String right = source.substring(half);
        return new String[] { left, right };
    }
    
}
