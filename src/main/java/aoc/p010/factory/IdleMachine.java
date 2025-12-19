package aoc.p010.factory;

import aoc.p010.solvers.BitmaskSolver;


/**
 * A machine that must be started (day 10 part 1).
 */
public class IdleMachine extends Machine {
    
    public final BitmaskSolver solver;
    
    
    public IdleMachine(
            String startedStateStr, 
            String[] buttonsStr, 
            String requiredJoltageStr
            ) {
        super(startedStateStr, buttonsStr, requiredJoltageStr);
        
        solver = new BitmaskSolver(this);
    }

    
    /**
     * The fewest button presses to start the machine.
     */
    @Override
    public long solve() {
        int combos = solver.getCombinations();
        long fewestPresses = Integer.MAX_VALUE;
        
        for (int j = 0; j < combos; j++) {
            boolean[] combo = solver.getCombination(j);
            boolean success = solver.press(combo);
            long presses = solver.countPresses(combo);
            
            if (success && presses < fewestPresses) {
                fewestPresses = presses;
            }
        }
        
        return fewestPresses;
    }

    
}
