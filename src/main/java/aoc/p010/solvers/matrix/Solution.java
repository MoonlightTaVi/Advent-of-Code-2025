package aoc.p010.solvers.matrix;

import java.util.Iterator;


/**
 * Iterates over all the possible combinations of the matrix
 * free variables, checks each against the conditions of the matrix
 * inequalities, returns the solution for each combination.
 * @see #checkBoundariesFor(int[])
 * @see #solveFor(int[])
 */
public class Solution implements Iterable<int[]> {
    /**
     * The master brute-force object for the solutions.
     */
    final MatrixBruteforce solver;
    
    /**
     * Number of the free variables in the matrix RREF.
     */
    final int varsCount;
    
    /**
     * Coefficients of the free variables in the final equality
     * of the matrix.
     */
    final int[] unknownRhs;
    
    /**
     * The unknown part of the right-hand side of the equality
     * must be subtracted from the known part to get the solution
     * of the matrix.
     */
    int knownRhs;
    

    /**
     * Takes the required information from the main brute force object.
     * @param forSolver
     */
    public Solution(MatrixBruteforce forSolver) {
        solver = forSolver;
        varsCount = solver.count;
        unknownRhs = new int[forSolver.cols];
        
        computeEquality();
    }
    
    
    /**
     * Checks if all the variables are within their boundaries,
     * according to the inequalities of the matrix.
     * @param values A specific combination of the values for the
     * free variables.
     * @return True if this values may give a valid solution 
     * for the matrix.
     */
    public boolean checkBoundariesFor(int[] values) {
        boolean success = true;
        
        for (int r = 0; r < solver.rows; r++) {
            int lhs = 0;
            int rhs = solver.matrix[r][solver.cols - 1];
            
            for (int c = 0; c < solver.cols - 1; c++) {
                int coef = solver.matrix[r][c];
                if (coef == 0) {
                    continue;
                }
                
                lhs += values[c] * coef;
            }
            
            // Left-hand side is always less than the right-hand side
            if (lhs > rhs) {
                success = false;
                break;
            }
        }
        
        return success;
    }
    
    /**
     * Finds the solution for this combination of values of 
     * the free variables.
     * @param values A specific combination of the values for the
     * free variables.
     * @return The solution of the matrix for this variables.
     */
    public int solveFor(int[] values) {
        int variablesSum = 0;
        
        for (int i = 0; i < varsCount; i++) {
            variablesSum += unknownRhs[i] * values[i];
        }
        
        int solution = knownRhs + variablesSum;
        
        return solution;
    }
    
    
    /**
     * Computes the final equality of the matrix that is used
     * to find the solution.
     */
    private void computeEquality() {
        // Sum all of the inequalities (including the known RHS)
        for (int r = 0; r < solver.rows; r++) {
            for (int c = 0; c < solver.cols; c++) {
                unknownRhs[c] += solver.matrix[r][c];
            }
        }
        
        // Convert variables (move from the LHS to the RHS);
        //  this is the unknown part of the RHS
        for (int c = 0; c < varsCount; c++) {
            unknownRhs[c] *= -1;
            unknownRhs[c] += 1;
        }
        
        // Known part of the RHS is the last element
        // Note: varsCount = unknownRhs.length - 1
        knownRhs = unknownRhs[solver.count];
    }


    @Override
    public Iterator<int[]> iterator() {
        return new VariablesIterator(solver);
    }
}
