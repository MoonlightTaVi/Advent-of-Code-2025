package aoc.p010.factory;

import java.util.Arrays;

public class StartedMachine {
    final long[] requiredJoltage;
    final int[][] buttons;

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
    }
    
}
