package aoc.p010;

import aoc.Validation;


/**
 * Day 10. <br>
 * https://adventofcode.com/2025/day/10 <br> <br>
 */
public class Main {
    static long pressesP1 = 527;

    // 19926 is too high
    public static void main(String[] args) {
        MachineFactory factory = new MachineFactory();
        Elf elfWorker = new Elf(factory);
        
        long resultP1 = elfWorker.tryToStartEmMachines();
        
        Validation.print(
                "The fewest button presses required (start)", 
                resultP1
                );
        Validation.validate(pressesP1, resultP1);
        
        long resultP2 = elfWorker.tryToFixEmJoltages();
        
        Validation.print(
                "The fewest button presses required (joltage)", 
                resultP2
                );
        Validation.validate(pressesP1, resultP2);
        
        
    }
    
}
