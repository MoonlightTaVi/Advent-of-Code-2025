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
        
        long buttonsPressed = elfWorker.tryToStartEmMachines();
        
        Validation.print(
                "The fewest button presses required", 
                buttonsPressed
                );
        Validation.validate(pressesP1, buttonsPressed);
    }
    
}
