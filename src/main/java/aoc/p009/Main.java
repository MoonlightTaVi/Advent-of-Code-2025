package aoc.p009;

import aoc.Validation;
import aoc.p009.squares.SquareProcessor;


/**
 * Day 9.
 */
public class Main {
    static long squareP1 = 4764078684L;
    
    
    public static void main(String[] args) {
        TileReader reader = new TileReader();
        SquareProcessor squares = new SquareProcessor();
        
        long resultP1 = squares.getLargest(reader);
        
        Validation.print("The largest square", resultP1);
        Validation.validate(squareP1, resultP1);
    }
    
}
