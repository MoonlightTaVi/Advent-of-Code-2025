package aoc.p010;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Elf {
    private final MachineReader factory;
    
    
    public long tryToStartEmMachines() {
        long allButtonPresses = 0;
        
        int len = factory.size();
        for (int i = 0; i < len; i++) {
            Machine machine = factory.getMachine(i);
            int combos = machine.getCombinations();
            
            long fewestPresses = Integer.MAX_VALUE;
            
            for (int j = 0; j < combos; j++) {
                boolean[] combo = machine.getCombination(j);
                boolean success = machine.press(combo);
                long presses = machine.countPresses(combo);
                
                if (success && presses < fewestPresses) {
                    fewestPresses = presses;
                }
            }
            
            allButtonPresses += fewestPresses;
        }
        
        return allButtonPresses;
    }
}
