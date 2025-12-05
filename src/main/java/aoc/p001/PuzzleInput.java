package aoc.p001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Reads the dial rotations for the safe from a resource file.
 * A combination starts with either L or R 
 * (rotate left/right respectively) that is then followed by a number
 * of steps. Saves the rotations to the array.
 * @see #rotations
 */
public class PuzzleInput {
    public String[] rotations;

    /**
     * Reads rotations from the file.
     * @param file
     * @see PuzzleInput
     */
    public PuzzleInput(String file) {
        ClassLoader loader = PuzzleInput.class.getClassLoader();
        InputStreamReader is = new InputStreamReader(
                loader.getResourceAsStream(file)
                );
        
        try (BufferedReader reader = new BufferedReader(is)) {
            rotations = reader.lines().toArray(String[]::new);
            
        } catch (IOException e) {
            System.err.printf(
                    "IOException while reading puzzle input: %s%n", 
                    e.getLocalizedMessage()
                    );
            e.printStackTrace();
        }
    }
    
}
