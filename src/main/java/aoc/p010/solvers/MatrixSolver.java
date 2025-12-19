package aoc.p010.solvers;

import aoc.p010.factory.StartedMachine;


/**
 * 
 */
public class MatrixSolver {
    static final boolean debug = false;

    /** Number of free variables. */
    final int count;
    /** Number of matrix columns. */
    final int m;
    /** Number of matrix rows. */
    final int n;

    /** Inequalities + their RHS. */
    final int[][] matrix;
    
    /** Initial maximal constraint for each free variable. */
    final int[] initialMaxConstraints;
    
    long instantSolution;
    
    
    public MatrixSolver(StartedMachine machine) {
        Matrix mat = new Matrix(machine);
        //System.out.println("Solving matrix:");
        //mat.print(true);
        
        // Check for an obvious solution
        instantSolution = mat.checkOnlyPivots();
        mat.matrixToRREF();
        
        count = mat.freeVariables.size();
        m = count + 1;
        n = mat.rows;
        matrix = new int[n][m];
        initialMaxConstraints = new int[count];
        
        for (int i = 0; i < count; i++) {
            // Put only free variable columns into new matrix
            int columnId = mat.freeVariables.get(i);
            for (int j = 0; j < n; j++) {
                matrix[j][i] = mat.table[j][columnId];
            }
            
            // Retrieve maximal constraints for these variables
            initialMaxConstraints[i] = (int) machine.constraints[columnId];
        }
        
        // Set the rightmost matrix column to RHS of inequalities.
        for (int i = 0; i < n; i++) {
            matrix[i][m - 1] = mat.table[i][mat.cols - 1];
        }
        
        // Check again after the matrix has been transformed to RREF
        if (instantSolution == 0) {
            instantSolution = mat.checkOnlyPivots();
        }
        
        //mat.print(true);
    }
    
    
    public long solve() {
        long bestSolution = Integer.MAX_VALUE;
        Solution solutions = new Solution(this);
        
        if (instantSolution == 0) {
            instantSolution = solutions.initialCheck();
        }
        
        // If we have a simple solution already
        //  return it
        if (instantSolution != 0) {
            //System.out.printf("The instant answer is: %d%n", instantSolution);
            return instantSolution;
        }
        
        for (int[] coefficients : solutions) {
            if (!solutions.checkBoundariesFor(coefficients)) {
                continue;
            }

            long solution = solutions.solveFor(coefficients);
            if (solution < bestSolution) {
                bestSolution = solution;
            }
        }
        
        //System.out.printf("The answer is: %d%n", bestSolution);
        
        if (bestSolution == Integer.MAX_VALUE) {
            throw new RuntimeException("Could not solve the matrix.");
        }
        
        return bestSolution;
    }
}
