package aoc.p010.solvers;

import aoc.p010.factory.Machine;


/**
 * Uses bit masks of each machine button to solve the part 1 problem.
 */
public class BitmaskSolver {
    
    private final Machine machine;
    public final int finalStateMask;

    
    public BitmaskSolver(Machine machine) {
        this.machine = machine;
        finalStateMask = convertStateToMask(machine.startedState);
    }
    
    
    /**
     * Converts the indicators of the machine to a bit mask.
     * @param startedState Something like [' ', '#', '#', ' '].
     * @return Integer bit mask of this indicators ('#' corresponds
     * to a bit).
     */
    public int convertStateToMask(char[] startedState) {
        int result = 0;
        int start = startedState.length - 1;
        
        for (int i = start; i >= 0; i--) {
            int unit = startedState[i] == '#' ? 1 : 0;
            result += unit * Math.pow(2, i);
        }
        
        return result;
    }
    
    /**
     * Converts a button to its bit mask. Each bit corresponds to the
     * indicator it affects.
     * @param id ID of the button on the machine.
     * @return Integer mask of the button.
     */
    public int getButton(int id) {
        int[] arr = machine.buttons[id];
        
        int result = 0;
        for (int n : arr) {
            result = result | (1 << n);
        }
        
        return result;
    }
    
    
    /**
     * Returns the total number of possible pressed button combinations.
     * <br> Since the first combination has the ID of 0, the last
     * combination will be (n - 1), just like with arrays.
     * @return The total number of pressed/released 
     * combinations for buttons.
     */
    public int getCombinations() {
        return (int) Math.pow(2, machine.buttons.length);
    }
    
    /**
     * Returns an array that represents toggled state of each button
     * on the machine. {@code true} represents "toggled on", and
     * {@code false} is toggled off. <br>
     * The array has the same length as the number of buttons 
     * on the machine.
     * @param id ID of the combination to retrieve.
     * @return boolean array for the combination with the ID.
     */
    public boolean[] getCombination(int id) {
        int len = machine.buttons.length;
        int start = len - 1;
        boolean[] combination = new boolean[len];
        
        for (int i = start; i >= 0 ; i--) {
            combination[i] = (id & (1 << i)) != 0;
        }
        
        return combination;
    }
    
    
    /**
     * Applies a combination of button presses to the machine.
     * @param buttons Masks of buttons to be pressed.
     * @return True if the machine was started after these buttons
     * were pressed.
     */
    public boolean press(int... buttons) {
        int state = 0;
        
        for (int button : buttons) {
            state = state ^ button;
        }
        
        return state == finalStateMask;
    }
    
    /**
     * Applies a combination of button presses to the machine.
     * @param combination Boolean style combination of button presses,
     * where true corresponds to a pressed button (there's no need
     * to press the same button twice).
     * @return True if the machine was started after these buttons
     * were pressed.
     */
    public boolean press(boolean... combination) {
        int len = machine.buttons.length;
        int start = len - 1;
        int state = 0;
        
        for (int i = start; i >= 0; i--) {
            boolean toggled = combination[i];
            if (!toggled) {
                continue;
            }
            int button = getButton(i);
            state = state ^ button;
        }
        
        return state == finalStateMask;
    }
    
    
    /**
     * Counts how many total button presses is in the combination.
     * @param combination Boolean style combination of button presses,
     * where true corresponds to a pressed button (there's no need
     * to press the same button twice).
     * @return
     */
    public int countPresses(boolean... combination) {
        int count = 0;
        
        for (boolean combo : combination) {
            if (combo) {
                count++;
            }
        }
        
        return count;
    }
    
}
