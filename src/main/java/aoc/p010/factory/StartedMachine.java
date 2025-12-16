package aoc.p010.factory;

import aoc.p010.solvers.MatrixSolver;


public class StartedMachine extends Machine {
    
    public final MatrixSolver solver;

    
    public StartedMachine(
            String startedStateStr, 
            String[] buttonsStr, 
            String requiredJoltageStr
            ) {
        super(startedStateStr, buttonsStr, requiredJoltageStr);
        
        solver = new MatrixSolver(this);
    }

    
    @Override
    public long solve() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
