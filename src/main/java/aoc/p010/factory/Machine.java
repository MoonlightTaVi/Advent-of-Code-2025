package aoc.p010.factory;

import java.util.Arrays;


public abstract class Machine {
    
    public final char[] startedState;
    public final int[][] buttons;
    public final int[] requiredJoltage;
    
    public final long[] constraints;
    
    
    public Machine(String startedStateStr, String[] buttonsStr, String requiredJoltageStr) {
        startedState = startedStateStr.toCharArray();
        
        requiredJoltage = Arrays.stream(requiredJoltageStr.split(","))
                .mapToInt(Integer::valueOf)
                .toArray();
        
        buttons = new int[buttonsStr.length][];
        for (int i = 0; i < buttonsStr.length; i++) {
            buttons[i] = Arrays.stream(buttonsStr[i].split(","))
                    .mapToInt(Integer::valueOf)
                    .toArray();
        }
        
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
    
    
    public abstract long solve();
}
