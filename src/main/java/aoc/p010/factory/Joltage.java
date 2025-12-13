package aoc.p010.factory;

import java.util.Arrays;

public class Joltage {
    
    final int[] value;
    
    int remainder;
    int lowestJoltage = Integer.MAX_VALUE;
    int lowestJoltageId;
    
    
    public Joltage(String joltage) {
        value = Arrays.stream(joltage.split(","))
                .mapToInt(Integer::valueOf)
                .toArray();
        findRemainder();
    }
    
    
    public int getEffectivenessOf(Button button) {
        int sum = 0;
        for (int id : button.affectsIds) {
            sum += value[id];
        }
        return sum;
    }
    
    public boolean press(Button button) {
        for (int id : button.affectsIds) {
            value[id] -= lowestJoltage;
        }
        button.presses += lowestJoltage;
        findRemainder();
        
        return remainder == 0;
    }
    
    
    public int highestValue() {
        int max = 0;
        for (int n : value) {
            if (n > max) {
                max = n;
            }
        }
        return max;
    }
    
    
    private void findRemainder() {
        int sum = 0;
        for (int i = 0; i < value.length; i++) {
            int n = value[i];
            sum += n;
            if (n > 0 && n < lowestJoltage) {
                lowestJoltage = n;
                lowestJoltageId = i;
            }
        }
        if (lowestJoltage > sum) {
            lowestJoltage = sum;
        }
        remainder = sum;
    }
}
