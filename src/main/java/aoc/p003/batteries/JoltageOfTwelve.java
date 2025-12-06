package aoc.p003.batteries;

import aoc.p003.util.BinaryMaximum;


public class JoltageOfTwelve implements JoltageStrategy {
    final int MAX_LIMIT = 12;
    BinaryMaximum max = new BinaryMaximum();

    
    @Override
    public long getLargestJoltage(String batteryBank) {
        int length = batteryBank.length();
        int start = 0;
        int end = length;
        char[] digits = new char[MAX_LIMIT];
        
        for (int i = 0; i < MAX_LIMIT; i++) {
            int limit = MAX_LIMIT - i;
            int currentMaxIndex = -1;
            while (currentMaxIndex < 0) {
                String substring = batteryBank.substring(start, end);
                int tempMaxIndex = max.greatestIndex(substring) + start;
                if (tempMaxIndex > length - limit) {
                    end = tempMaxIndex;
                    continue;
                }
                currentMaxIndex = tempMaxIndex;
                start = currentMaxIndex + 1;
                end = length;
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
