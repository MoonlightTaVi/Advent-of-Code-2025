package aoc.p001;


/**
 * A safe with a dial. The dial can be rotated in order 
 * to unlock the safe. While rotating the dial, the class counts the 
 * numbers of times when the dial points at zero 
 * (as the result of rotation) or passes by zero (during the rotation).
 * @see #process(String[])
 * @see #rotate(String)
 */
public class Safe {
    /** Number of divisions on the round dial. */
    final int MAX = 100;
    /** Current position of the dial. */
    public int position = 50;
    
    /** Times the dial pointed 0 after rotation. */
    public int pointedZeroes = 0;
    /** Times the dial passed by 0 during rotation. */
    public int passedZeroes = 0;
    /** 
     * The sum of {@code pointedZeroes} and {@code passedZeroes}.
     * Only accessible after using {@code process()} method.
     * @see #pointedZeroes
     * @see #passedZeroes
     * @see #process(String[])
     */
    public int allZeroes = 0;
    
    /**
     * Applies the rotation from the given array to the dial.
     * Counts {@code allZeroes} in the end.
     * @param rotations An array of rotations.
     * @return Itself, to get one of the desired result numbers.
     * @see #pointedZeroes
     * @see #passedZeroes
     * @see #allZeroes
     * @see PuzzleInput
     */
    public Safe process(String[] rotations) {
        for (String rotation : rotations) {
            rotate(rotation);
        }
        allZeroes = pointedZeroes + passedZeroes;
        return this;
    }
    
    /**
     * Applies one rotation to the dial. A rotation must start with
     * either L (left) or R (right) and then be followed by a number of
     * steps.
     * @param input Rotation to apply (e.g. L10, R154).
     */
    public void rotate(String input) {
        char direction = input.charAt(0);
        int steps = Integer.parseInt(input.substring(1));
        
        if (direction == 'L') {
            rotateLeft(steps);
        } else {
            rotateRight(steps);
        }
        
        if (position == 0) {
            pointedZeroes++;
        }
    }
    
    /**
     * Applies left rotation by the number of steps.
     * @param steps
     */
    private void rotateLeft(int steps) {
        boolean startedAtZero = position == 0;
        position -= steps;
        while (position < 0) {
            position += MAX;
            // The first 0 doesn't count
            if (startedAtZero) { 
                startedAtZero = false;
                continue;
            } else {
                passedZeroes++;
            }
        }
    }
    
    /**
     * Applies right rotation by the number of steps.
     * @param steps
     */
    private void rotateRight(int steps) {
        position += steps;
        while (position >= MAX) {
            position -= MAX;
            passedZeroes++;
        }
        // The last 0 doesn't count
        if (position == 0) { 
            passedZeroes--;
        }
    }
    
}
