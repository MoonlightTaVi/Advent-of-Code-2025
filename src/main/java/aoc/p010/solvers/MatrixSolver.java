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
    
    
    public MatrixSolver(StartedMachine machine) {
        Matrix mat = new Matrix(machine);
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
        
        System.out.println("Solving matrix:");
        mat.print(true);
    }
    
    
    public long solve() {
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
