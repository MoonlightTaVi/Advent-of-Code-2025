package aoc.p001;

import aoc.FileReader;


/**
 * Reads the dial rotations for the safe from a resource file.
 * A combination starts with either L or R 
 * (rotate left/right respectively) that is then followed by a number
 * of steps. Saves the rotations to the array.
 * @see #rotations
 */
public class PuzzleInput extends FileReader {
    public String[] rotations;

    /**
     * Reads rotations from the file.
     * @param file
     * @see PuzzleInput
     */
    public PuzzleInput(String file) {
        rotations = super.read(file).lines;
    }
    
}
