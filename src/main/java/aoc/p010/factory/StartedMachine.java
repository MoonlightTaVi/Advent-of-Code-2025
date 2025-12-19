package aoc.p010.factory;

import aoc.p010.solvers.JoltageSolver;


/**
 * A working machine that must be configured (day 10 part 2).
 */
public class StartedMachine extends Machine {
    
    public final JoltageSolver solver;

    
    public StartedMachine(
            String startedStateStr, 
            String[] buttonsStr, 
            String requiredJoltageStr
            ) {
        super(startedStateStr, buttonsStr, requiredJoltageStr);
        
        solver = new JoltageSolver();
    }

    
    @Override
    public long solve() {
        return solver.solve(this);
    }
    
}
