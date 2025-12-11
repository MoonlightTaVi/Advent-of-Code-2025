package aoc.p009;

import aoc.Validation;
import aoc.p009.edges.Edges;
import aoc.p009.squares.SquareProcessor;


/**
 * Day 9.
 */
public class Main {
    static long squareP1 = 4764078684L;
    
    // 114894852 too low.. 150497383 ? too low
    public static void main(String[] args) {
        TileReader reader = new TileReader();
        SquareProcessor squares = new SquareProcessor();
        Edges polygon = new Edges(reader);
        
        long resultP1 = squares.getLargest(reader);
        
        Validation.print("The largest square", resultP1);
        Validation.validate(squareP1, resultP1);
        
        long resultP2 = squares.getRedGreen(polygon);
        
        Validation.print("The largest square", resultP2);
        Validation.validate(squareP1, resultP1);
    }
    
}
