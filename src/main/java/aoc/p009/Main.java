package aoc.p009;

import aoc.Validation;


/**
 * Day 9.
 */
public class Main {
    static long squareP1 = 4_764_078_684L;
    static long squareP2 = 1_652_344_888;
    
    // 114_894_852 too low.. 150_497_383 ? too low
    // 2_930_655_546 is not right
    public static void main(String[] args) {
        TileReader reader = new TileReader();
        TileProcessor processor = new TileProcessor(reader);

        processor.processCorners();
        processor.processAll();
        
        
        long resultP1 = processor.areaLargest();
        
        Validation.print("The largest area", resultP1);
        Validation.validate(squareP1, resultP1);
        
        long resultP2 = processor.areaInside();
        
        Validation.print("The largest area (inside)", resultP2);
        Validation.validate(squareP2, resultP2);
    }
    
}
