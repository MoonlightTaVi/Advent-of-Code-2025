package aoc.p010.solvers;

import aoc.p010.factory.StartedMachine;
import aoc.p010.solvers.matrix.Matrix;
import aoc.p010.solvers.matrix.MatrixBruteforce;


/**
 * Solves part 2 of day 10.
 */
public class JoltageSolver {

    /**
     * Solves part 2 task for a single machine.
     * @param machine A machine to solve.
     * @return The fewest button presses required to configure
     * all the joltages of the machine.
     */
    public long solve(StartedMachine machine) {
        Matrix matrix = new Matrix(machine);
        long instantSolution = 0;
        
        // Check if the solution is obvious
        instantSolution = matrix.checkObviousSolution();
        if (instantSolution != 0) {
            return instantSolution;
        }
        
        // Check again for RREF...
        matrix.matrixToRREF();
        instantSolution = matrix.checkObviousSolution();
        if (instantSolution != 0) {
            return instantSolution;
        }
        
        // Brute force
        MatrixBruteforce bruteforce = new MatrixBruteforce(matrix, machine);
        return bruteforce.solve();
    }
    
}
