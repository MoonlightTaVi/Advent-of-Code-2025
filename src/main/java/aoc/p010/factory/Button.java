package aoc.p010.factory;

import java.util.Arrays;


public class Button {

    final int[] affectsIds;
    int maxPresses = 0;
    
    int presses = 0;
    
    
    public Button(String buttonStr) {
        affectsIds = Arrays.stream(buttonStr.split(","))
                .mapToInt(Integer::valueOf)
                .toArray();
    }
    
    public Button(Button sourceButton, int maxPresses) {
        affectsIds = sourceButton.affectsIds;
        this.maxPresses = maxPresses;
    }
    
    
    public boolean pressAndBreak() {
        presses++;
        // When reached maximum, move to the next button in the loop
        if (presses > maxPresses) {
            presses = 0;
            return false;
        }
        // Else, break out of the button loop
        return true;
    }
    
    
    public boolean affectsAll(int[] joltage) {
        for (int id : affectsIds) {
            if (joltage[id] == 0) {
                return false;
            }
        }
        return true;
    }
}
