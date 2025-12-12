package aoc.p009;

import aoc.Validation;


/**
 * Day 9.
 */
public class Main {
    static long squareP1 = 4764078684L;
    
    // 114_894_852 too low.. 150_497_383 ? too low
    // 2930655546 is not right
    public static void main(String[] args) {
        TileReader reader = new TileReader();
        TileProcessor processor = new TileProcessor(reader);

        processor.processCorners();
        processor.processAll();
        
        
        long resultP1 = processor.areaLargest();
        
        Validation.print("The largest square", resultP1);
        Validation.validate(squareP1, resultP1);
        
        long resultP2 = processor.areaLimited();
        
        Validation.print("The largest square", resultP2);
        Validation.validate(squareP1, resultP1);
    }
    
}
