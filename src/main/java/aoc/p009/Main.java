package aoc.p009;

import aoc.Validation;


/**
 * Day 9.
 */
public class Main {
    static long squareP1 = 4764078684L;
    
    // 114894852 too low.. 150497383 ? too low
    public static void main(String[] args) {
        TileReader reader = new TileReader();
        TileProcessor processor = new TileProcessor(reader);
        processor.process();
        
        long resultP1 = processor.areaLargest();
        
        Validation.print("The largest square", resultP1);
        Validation.validate(squareP1, resultP1);
        
        long resultP2 = processor.areaLimited();
        
        Validation.print("The largest square", resultP2);
        Validation.validate(squareP1, resultP1);
    }
    
}
