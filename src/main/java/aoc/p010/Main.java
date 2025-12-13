package aoc.p010;

import aoc.Validation;


/**
 * Day 10. <br>
 * https://adventofcode.com/2025/day/10 <br> <br>
 */
public class Main {
    static long pressesP1 = 527;

    public static void main(String[] args) {
        MachineReader factory = new MachineReader();
        Elf elfWorker = new Elf(factory);
        
        long resultP1 = elfWorker.tryToStartEmMachines();
        
        Validation.print(
                "The fewest button presses required", 
                resultP1
                );
        Validation.validate(pressesP1, resultP1);
        
        long resultP2 = elfWorker.tryToFixEmJoltages();
        
        Validation.print(
                "The fewest button presses required", 
                resultP1
                );
        Validation.validate(pressesP1, resultP2);
        
        
    }
    
}
