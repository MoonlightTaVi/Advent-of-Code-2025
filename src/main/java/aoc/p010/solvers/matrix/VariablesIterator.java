package aoc.p010.solvers.matrix;

import java.util.Iterator;


/**
 * Iterates over all possible combinations of the free variables
 * in the matrix.
 */
public class VariablesIterator implements Iterator<int[]> {
    final int[] step;
    final int[] constraints;
    
    boolean loopedItself = false;

    public VariablesIterator(MatrixBruteforce forSolver) {
        constraints = forSolver.maxConstraints;
        // Start for all set to zeroes
        step = new int[constraints.length];
    }

    @Override
    public boolean hasNext() {
        return !loopedItself;
    }

    @Override
    public int[] next() {
        // The first step is '1' at the last position
        //  (increment first)
        increment();
        return step;
    }
    
    private void increment() {
        int depth = step.length - 1;
        while (depth >= 0) {
            step[depth] += 1;
            if (step[depth] > constraints[depth]) {
                // The last step is all zeroes
                if (depth == 0) {
                    loopedItself = true;
                }
                
                step[depth] = 0;
                depth--;
                
                continue;
            }
            break;
        }
    }
    
}
