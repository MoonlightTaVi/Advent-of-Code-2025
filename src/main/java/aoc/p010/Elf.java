package aoc.p010;

import aoc.p010.factory.*;
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
    
    
    public long tryToFixEmJoltages() {
        long allButtonPresses = 0;
        
        int len = factory.size();
        for (int i = 0; i < len; i++) {
            StartedMachine machine = factory.getStartedMachine(i);
            
            while (machine.hasNotFinished()) {
                int comboId = machine.getPrioritizedCombinationId();
                boolean[] buttonStates = machine.getCombination(comboId);
                int countButtons = machine.countButtons(buttonStates);
                
                long[] combo = machine.combinations[comboId];
                long diff = machine.apply(combo);
                
                allButtonPresses += diff * countButtons;
            }
        }
        
        return allButtonPresses;
    }
}
