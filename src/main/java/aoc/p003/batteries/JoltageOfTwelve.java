package aoc.p003.batteries;

import aoc.p003.util.BinaryMaximum;


/**
 * The number of this joltage contains 12 digits.
 */
public class JoltageOfTwelve implements JoltageStrategy {
    final int MAX_LIMIT = 12;
    BinaryMaximum max = new BinaryMaximum();

    
    @Override
    public long getLargestJoltage(String batteryBank) {
        char[] digits = new char[MAX_LIMIT];
        
        int length = batteryBank.length();
        int start = 0;
        
        for (int i = 0; i < MAX_LIMIT; i++) {
            // Limit starts from 12 and gets decremented during iteration
            int limit = MAX_LIMIT - i;
            
            int currentMaxIndex = -1;
            while (currentMaxIndex < 0) {
                // Limit by 12-offset from the right + at least 1-chat long
                // The offset is decremented each step
                int end = length - limit + 1;
                
                String substring = batteryBank.substring(start, end);
                currentMaxIndex = max.greatestIndex(substring) + start;
                
                // Continue from the next position
                start = currentMaxIndex + 1;
            }
            
            digits[i] = batteryBank.charAt(currentMaxIndex);
        }
        
        return getJoltage(digits);
    }
    
    public long getJoltage(char... digits) {
        long result = 0;
        int max = digits.length - 1;
        for (int n = max; n >= 0; n--) {
            int i = max - n;
            int number = digits[i] - '0';
            result += number * Math.pow(10, n);
        }
        return result;
    }

}
