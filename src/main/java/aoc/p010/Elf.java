package aoc.p010;

import aoc.p010.factory.*;
import lombok.RequiredArgsConstructor;


/**
 * The main class that solves day 10 problems.
 * @see #tryToStartEmMachines()
 * @see #tryToFixEmJoltages()
 */
@RequiredArgsConstructor
public class Elf {
    private final MachineFactory factory;
    
    
    /**
     * Part 1 of the day 10.
     * @return
     */
    public long tryToStartEmMachines() {
        long allButtonPresses = 0;
        
        int len = factory.size();
        for (int i = 0; i < len; i++) {
            Machine machine = factory.getIdleMachine(i);
            allButtonPresses += machine.solve();
        }
        
        return allButtonPresses;
    }
    
    
    /**
     * Part 2 of the day 10.
     * @return
     */
    public long tryToFixEmJoltages() {
        long allButtonPresses = 0;
        
        int len = factory.size();
        for (int i = 0; i < len; i++) {
            Machine machine = factory.getStartedMachine(i);
            allButtonPresses += machine.solve();
        }
        
        return allButtonPresses;
    }
}
