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
        System.out.println("Solving matrix:");
        mat.print(true);
        
        // Check for an obvious solution
        instantSolution = mat.checkOverlapsOfRows();
        mat.matrixToRREF();
        
        count = mat.freeVariables.size();
        m = count + 1;
        n = mat.n;
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
            matrix[i][m - 1] = mat.table[i][mat.m - 1];
        }
        
        // Check again after the matrix has been transformed to RREF
        if (instantSolution == 0) {
            instantSolution = mat.checkOverlapsOfRows();
        }
        
        mat.print(true);
    }
    
    
    public long solve() {
        // If we have a simple solution already
        //  return it
        if (instantSolution != 0) {
            System.out.printf("The instant answer is: %d%n", instantSolution);
            return instantSolution;
        }
        
        long bestSolution = Integer.MAX_VALUE;
        Solution solutions = new Solution(this);
        
        for (int[] coefficients : solutions) {
            print("Check solution...");
            if (!solutions.checkBoundariesFor(coefficients)) {
                print("Solution invalid.");
                continue;
            }

            print("Found solution, calculate the result...");
            long solution = solutions.solveFor(coefficients);
            if (solution < bestSolution) {
                print("Update the result");
                bestSolution = solution;
            }
        }
        
        System.out.printf("The answer is: %d%n", bestSolution);
        
        return bestSolution;
    }
    
    
    private void print(String message) {
        if (!debug) {
            return;
        }
        
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < 25; i++) {
            spaces.append('\s');
        }
        System.out.print(message + spaces.toString() + '\r');
    }
}
