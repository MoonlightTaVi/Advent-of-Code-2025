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
    
    private int[] solvedVariables;
    private int solvedCount = 0;
    private final int solvedTotal;
    
    private long result;
    
    
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
        solvedVariables = new int[m - 1];
        combined = initCombined();
        
        solvedTotal = m - 1;
    }
    
    
    public long solve() {
        while (solvedCount < solvedTotal) {
            updateConstraints();
            
            if (applyForMaxNegative()) {
                continue;
            }
            
            if (applyForMinPositive()) {
                continue;
            }
            
            if (solvedCount < solvedTotal) {
                throw new RuntimeException("Infinite loop");
            }
        }
        
        return result;
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
            
            // Pivot columns are solved by default
            if (combined[j] == 0) {
                solved[j] = true;
                solvedCount++;
            }
        }
        
        result = combined[m - 1];
        
        return combined;
    }
    
    
    private void updateConstraints() {
        for (int r = 0; r < n; r++) {
            int negativesCount = 0;
            int lastNegativeId = -1;
            
            int positivesCount = 0;
            int lastPositiveId = -1;
            
            int solvedSum = 0;
            
            for (int c = 0; c < m - 1; c++) {
                if (solved[c]) {
                    solvedSum += solvedVariables[c] * matrix[r][c];
                    continue;
                }
                
                if (matrix[r][c] > 0) {
                    positivesCount++;
                    lastPositiveId = c;
                } else if (matrix[r][c] < 0) {
                    negativesCount++;
                    lastNegativeId = c;
                }
            }
            
            // Decrease maximum constraint for a positive variable
            if (positivesCount == 1 && negativesCount == 0) {
                // Since we move the sum to the right,
                //  we decrement the right-side value by sum
                solvedSum = matrix[r][m - 1] - solvedSum;
                
                if (maxConstraints[lastPositiveId] > solvedSum) {
                    maxConstraints[lastPositiveId] = solvedSum;
                }
            }
            
            // Increase minimum constraint for a negative variable
            if (negativesCount == 1 && positivesCount == 0) {
                // Since we move the sum to the right,
                //  we decrement the right-side value by sum
                solvedSum = matrix[r][m - 1] - solvedSum;
                
                // Change inequality direction (only for negatives)
                // The original number must be <= 0
                solvedSum *= -1;
                
                /*if (solvedSum < 0) {
                    // This should not happen
                    throw new RuntimeException(
                            "Free variable cannot be < 0, but they are."
                            );
                }*/
                
                if (minConstraints[lastNegativeId] < solvedSum) {
                    minConstraints[lastNegativeId] = solvedSum;
                }
            }
        }
    }
    
    
    private boolean applyForMaxNegative() {
        int maxId = -1;
        int max = 0;
        
        for (int i = 0; i < combined.length - 1; i++) {
            if (solved[i]) {
                continue;
            }
            
            if (combined[i] < max) {
                max = combined[i];
                maxId = i;
            }
        }
        
        boolean success = false;
        
        if (maxId >= 0) {
            solved[maxId] = true;
            solvedVariables[maxId] = maxConstraints[maxId];
            success = true;
            solvedCount++;
            
            result += max * solvedVariables[maxId];
        }
        
        return success;
    }
    
    private boolean applyForMinPositive() {
        int maxId = -1;
        int max = 0;
        
        for (int i = 0; i < combined.length - 1; i++) {
            if (solved[i]) {
                continue;
            }
            
            if (combined[i] > max) {
                max = combined[i];
                maxId = i;
            }
        }
        
        boolean success = false;
        
        if (maxId >= 0) {
            solvedVariables[maxId] = minConstraints[maxId];
            solved[maxId] = true;
            success = true;
            solvedCount++;
            
            result += max * solvedVariables[maxId];
        }
        
        return success;
    }
}
