package aoc.p010.solvers;

import java.util.Iterator;


public class Solution implements Iterable<int[]> {
    
    final MatrixSolver solver;
    
    final int varsCount;
    final int[] unknownRhs;
    
    int knownRhs;
    

    public Solution(MatrixSolver forSolver) {
        solver = forSolver;
        varsCount = solver.count;
        unknownRhs = new int[forSolver.m];
        computeEquality();
    }
    
    
    public boolean checkBoundariesFor(int[] coefficients) {
        boolean success = true;
        
        for (int r = 0; r < solver.n; r++) {
            int lhs = 0;
            for (int c = 0; c < solver.count; c++) {
                lhs += coefficients[c] * solver.matrix[r][c];
            }
            
            int rhs = solver.matrix[r][solver.count];
            
            // Check if the LHS for these coefficients
            //  is less than or equal to RHS (must be)
            if (lhs > rhs) {
                success = false;
                break;
            }
        }
        
        return success;
    }
    
    public int solveFor(int[] coefficients) {
        int variablesSum = 0;
        
        for (int i = 0; i < varsCount; i++) {
            variablesSum += unknownRhs[i] * coefficients[i];
        }
        
        int solution = knownRhs + variablesSum;
        
        return solution;
    }
    
    
    private void computeEquality() {
        // Sum all of the inequalities (including the known RHS)
        for (int r = 0; r < solver.n; r++) {
            for (int c = 0; c < solver.m; c++) {
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
        return new SolutionsIterator(solver);
    }
}
