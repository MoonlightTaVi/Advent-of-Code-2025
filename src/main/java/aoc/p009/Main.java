package aoc.p009;

import aoc.Validation;


/**
 * Day 9. <br> <br>
 * https://adventofcode.com/2025/day/9 <br> <br>
 * We have an orthogonal polygon made of points that hop either
 * horizontally or vertically from each other. <br>
 * We need to find: <br>
 * 1. The largest area of a rectangle that is made of two points; <br>
 * 2. The largest area, but that area must be completely <b>inside</b>
 * the polygon. <br>
 * How do we do the part 2? Let's find all the corners of the polygon
 * (the ones that are made of 3 points {@code A(i), B(i+1), C(i+2)} 
 * following each other) and decide whether each rectangle (formed by
 * this given corner) is inside the polygon. <br>
 * How do we know if the rectangle is inside? Its corner must be
 * a right turn (if moving clock-wise). <br>
 * How do we know if it is a right turn? We find the cross product
 * of the corner (must be positive). <br>
 * Then, for each another rectangle, we check if it intersects one of
 * the "outer" rectangles; if it does not, this rectangle is inside
 * the polygon.
 */
public class Main {
    static long squareP1 = 4_764_078_684L;
    static long squareP2 = 1_652_344_888;
    
    // 114_894_852 too low.. 150_497_383 ? too low
    // 2_930_655_546 is not right
    public static void main(String[] args) {
        TileReader reader = new TileReader();
        TileProcessor processor = new TileProcessor(reader);

        processor.makePolygon();
        processor.processAll();
        
        
        long resultP1 = processor.getLargest().area();
        
        Validation.print("The largest area", resultP1);
        Validation.validate(squareP1, resultP1);
        
        long resultP2 = processor.getInnerLargest().area();
        
        Validation.print("The largest area (inside)", resultP2);
        Validation.validate(squareP2, resultP2);
    }
    
}
