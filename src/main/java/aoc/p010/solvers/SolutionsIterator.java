package aoc.p010.solvers;

import java.util.Iterator;


public class SolutionsIterator implements Iterator<int[]> {
    final int[] step;
    final int[] constraints;
    
    boolean loopedItself = false;

    public SolutionsIterator(MatrixSolver forSolver) {
        constraints = forSolver.initialMaxConstraints;
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
