package aoc.p010.factory;

import java.util.Arrays;

public class StartedMachine {
    public final long[] requiredJoltage;
    public final int[][] buttons;
    

    public final long[] constraints;
    final long[] pressed;

    
    
    public StartedMachine(String joltage, String[] buttonsStr) {
        requiredJoltage = Arrays.stream(joltage.split(","))
                .mapToLong(Long::valueOf)
                .toArray();
        buttons = new int[buttonsStr.length][];
        for (int i = 0; i < buttonsStr.length; i++) {
            buttons[i] = Arrays.stream(buttonsStr[i].split(","))
                    .mapToInt(Integer::valueOf)
                    .toArray();
        }
        pressed = new long[buttonsStr.length];
        constraints = initConstraints();
    }
    
    private long[] initConstraints() {
        long[] maxPresses = new long[buttons.length];
        for (int i = 0; i < buttons.length; i++) {
            long lowestValue = Integer.MAX_VALUE;
            for (int id : buttons[i]) {
                if (requiredJoltage[id] < lowestValue) {
                    lowestValue = requiredJoltage[id];
                }
            }
            maxPresses[i] = lowestValue;
        }
        return maxPresses;
    }
    
}
