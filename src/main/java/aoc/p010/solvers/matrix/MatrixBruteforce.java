package aoc.p010.solvers.matrix;

import aoc.p010.factory.StartedMachine;


/**
 * 
 */
public class MatrixBruteforce {
    /** Number of free variables. */
    final int count;
    /** Number of matrix columns. */
    final int cols;
    /** Number of matrix rows. */
    final int rows;

    /** Inequalities + their RHS. */
    final int[][] matrix;
    
    /** Initial maximal constraint for each free variable. */
    final int[] maxConstraints;
    
    
    /**
     * Prepares the brute-force algorithm for a specific case.
     * @param mat Matrix of the machine (the main requirement).
     * @param machine The machine itself (only the constraints of
     * the variables are takes from it).
     */
    public MatrixBruteforce(Matrix mat, StartedMachine machine) {
        count = mat.freeVariables.size();
        cols = count + 1;
        rows = mat.rows;
        matrix = new int[rows][cols];
        maxConstraints = new int[count];
        
        for (int i = 0; i < count; i++) {
            // Put only free variable columns into new matrix
            int columnId = mat.freeVariables.get(i);
            for (int j = 0; j < rows; j++) {
                matrix[j][i] = mat.table[j][columnId];
            }
            
            // Retrieve maximal constraints for these variables
            maxConstraints[i] = (int) machine.constraints[columnId];
        }
        
        // Set the rightmost matrix column to RHS of inequalities.
        for (int i = 0; i < rows; i++) {
            matrix[i][cols - 1] = mat.table[i][mat.cols - 1];
        }
    }
    
    
    /**
     * Brute-forces the solution for a system of linear equalities.
     * @return The best solution of the matrix function.
     */
    public long solve() {
        long bestSolution = Integer.MAX_VALUE;
        Solution solutions = new Solution(this);
        
        for (int[] variables : solutions) {
            // Check if still within the constraints of inequalities
            if (!solutions.checkBoundariesFor(variables)) {
                continue;
            }

            // Update best solution if needed
            long solution = solutions.solveFor(variables);
            if (solution < bestSolution) {
                bestSolution = solution;
            }
        }
        
        // Throw exception on bug
        if (bestSolution == Integer.MAX_VALUE) {
            throw new RuntimeException("Could not solve the matrix.");
        }
        
        return bestSolution;
    }
}
