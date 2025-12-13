package aoc.p010;

import aoc.p010.factory.Bruteforce;
import aoc.p010.factory.Button;
import aoc.p010.factory.Dashboard;
import aoc.p010.factory.Machine;
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
            Dashboard dashboard = factory.getDashboard(i);
            
            while (!dashboard.shouldBruteforce()) {
                dashboard.press();
            }
            Bruteforce brute = new Bruteforce(
                    dashboard.joltage, 
                    dashboard.buttons
                    );
            
            long fewestPresses = Integer.MAX_VALUE;
            while (brute.canContinue()) {
                if (brute.isResultSuccessful() && brute.lastPressesCount < fewestPresses) {
                    fewestPresses = brute.lastPressesCount;
                }
            }
            
            allButtonPresses += dashboard.countPresses();
            allButtonPresses += fewestPresses;
            
            int progress = (int) ((float) i / len * 100);
            System.out.printf("%d%%%n", progress);
        }
        
        return allButtonPresses;
    }
}
