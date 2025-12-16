package aoc.p010;

import aoc.p010.factory.*;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Elf {
    private final MachineFactory factory;
    
    
    public long tryToStartEmMachines() {
        long allButtonPresses = 0;
        
        int len = factory.size();
        for (int i = 0; i < len; i++) {
            Machine machine = factory.getMachine(i);
            allButtonPresses += machine.solve();
        }
        
        return allButtonPresses;
    }
    
    
    public long tryToFixEmJoltages() {
        long allButtonPresses = 0;
        
        int len = factory.size();
        for (int i = 0; i < len; i++) {
            //Machine machine = factory.getStartedMachine(i);
            //allButtonPresses += machine.solve();
        }
        
        return allButtonPresses;
    }
}
