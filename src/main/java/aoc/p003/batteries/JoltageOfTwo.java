package aoc.p003.batteries;

import aoc.p003.util.BinaryMaximum;


public class JoltageOfTwo implements JoltageStrategy {
    BinaryMaximum max = new BinaryMaximum();
    

    @Override
    public long getLargestJoltage(String batteryBank) {
        int leftLimit = batteryBank.length() - 1;
        
        String leftPart = batteryBank.substring(0, leftLimit);
        int leftMax = max.greatestIndex(leftPart);
        
        int rightStart = leftMax + 1;
        String rightPart = batteryBank.substring(rightStart);
        int rightMaxIndex = rightStart + max.greatestIndex(rightPart);
        
        int left = batteryBank.charAt(leftMax) - '0';
        int right = batteryBank.charAt(rightMaxIndex) - '0';
        
        int result = left * 10 + right;
        return result;
    }

}
