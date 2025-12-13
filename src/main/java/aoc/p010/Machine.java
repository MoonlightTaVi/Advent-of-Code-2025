package aoc.p010;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Machine {

    public final String startedState;
    public final String[] buttons;
    
    
    public int getStartedState() {
        int result = 0;
        char[] arr = startedState.toCharArray();
        int start = arr.length - 1;
        
        for (int i = start; i >= 0; i--) {
            int unit = arr[i] == '#' ? 1 : 0;
            result += unit * Math.pow(2, i);
        }
        
        return result;
    }
    
    public int getButton(int id) {
        int[] arr = Arrays.stream(buttons[id].split(","))
                .mapToInt(Integer::valueOf)
                .toArray();
        
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
        return (int) Math.pow(2, buttons.length);
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
        int len = buttons.length;
        int start = len - 1;
        boolean[] combination = new boolean[len];
        
        for (int i = start; i >= 0 ; i--) {
            combination[i] = (id & (1 << i)) != 0;
        }
        
        return combination;
    }
    
    
    public boolean press(int... buttons) {
        int state = 0;
        
        for (int button : buttons) {
            state = state ^ button;
        }
        
        return state == getStartedState();
    }
    
    public boolean press(boolean... combination) {
        int len = buttons.length;
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
        
        return state == getStartedState();
    }
}
