package aoc.p010.solvers;

import aoc.p010.factory.StartedMachine;


/**
 * 
 */
public class MatrixSolver {
    
    /** Number of columns. */
    final int m;
    /** Number of rows. */
    final int n;

    public final int[] minConstraints;
    public final int[] maxConstraints;
    
    public final int[][] matrix;
    
    public final int[] combined;
    public final boolean[] solved;
    
    
    public MatrixSolver(StartedMachine machine) {
        minConstraints = new int[machine.buttons.length];
        maxConstraints = new int[machine.buttons.length];
        
        for (int i = 0; i < machine.buttons.length; i++) {
            maxConstraints[i] = (int) machine.constraints[i];
        }
        
        Matrix matrix = new Matrix(machine);
        
        this.matrix = matrix.build();
        n = matrix.n;
        m = matrix.m;
        
        solved = new boolean[m - 1];
        combined = initCombined();
    }
    
    
    private int[] initCombined() {
        int[] combined = new int[m];
        
        // Compute the equalities of the free variables
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                combined[j] += matrix[i][j];
            }
        }
        
        for (int j = 0; j < m - 1; j++) {
            // Convert
            combined[j] *= -1;
            combined[j] += 1;
            
            // Pivot rows are solved by default
            if (combined[j] == 0) {
                solved[j] = true;
            }
        }
        
        return combined;
    }
}
