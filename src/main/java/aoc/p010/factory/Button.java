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
}
