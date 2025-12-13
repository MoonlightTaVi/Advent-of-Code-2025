package aoc.p010.factory;

import java.util.Arrays;


public class Button {

    final int[] affectsIds;
    int maxPresses = Integer.MAX_VALUE;
    
    int presses = 0;
    
    
    public Button(String buttonStr) {
        affectsIds = Arrays.stream(buttonStr.split(","))
                .mapToInt(Integer::valueOf)
                .toArray();
    }
    
    
    public void setMaxJoltage(int[] joltage) {
        for (int id : affectsIds) {
            if (joltage[id] < maxPresses) {
                maxPresses = joltage[id];
            }
        }
    }

    public boolean pressAndBreak() {
        presses++;
        if (presses > maxPresses) {
            presses = 0;
            return false;
        }
        return true;
    }
    
    public void affectJoltage(int[] joltage) {
        for (int id : affectsIds) {
            joltage[id] += presses;
        }
    }
}
