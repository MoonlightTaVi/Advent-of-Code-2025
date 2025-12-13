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
}
