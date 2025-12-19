package aoc.p010.factory;

import java.util.Arrays;


/**
 * Data object that parses the information from pre-parsed strings.
 */
public abstract class Machine {
    
    /**
     * Indicators that correspond to the started state of the machine.
     */
    public final char[] startedState;
    /**
     * Buttons on the machine and the indicators/joltages they impact.
     */
    public final int[][] buttons;
    /**
     * The joltage that corresponds to the proper 
     * working state of the machine.
     */
    public final int[] requiredJoltage;
    /**
     * The maximal constraints for presses of each button.
     */
    public final long[] constraints;
    
    
    public Machine(
            String startedStateStr, 
            String[] buttonsStr, 
            String requiredJoltageStr
            ) {
        
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
    
    
    /**
     * Prepares maximum constraints for button presses.
     * @return
     */
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
    
    
    /**
     * Solves a specific problem for this machine.
     * @return The answer to the problem.
     * @see IdleMachine
     * @see StartedMachine
     */
    public abstract long solve();
}
