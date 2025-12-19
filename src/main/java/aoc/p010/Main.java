package aoc.p010;

import aoc.Validation;


/**
 * Day 10. <br>
 * https://adventofcode.com/2025/day/10 <br> <br>
 * There's a factory. In the factory there're machines. <br>
 * The machines need to be started first. We must face the requirements
 * of their indicators' state. <br>
 * Then we must configure the joltages on each machine -> prepare
 * the matrix of each machine, transform it to its RREF, brute-force
 * the free variables.
 */
public class Main {
    static long pressesP1 = 527;
    static long pressesP2 = 19810;

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
        Validation.validate(pressesP2, resultP2);
        
        
    }
    
}
