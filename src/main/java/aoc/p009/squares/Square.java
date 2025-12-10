package aoc.p009.squares;

import aoc.shared.IntVector2;


public class Square {

    public final IntVector2 a;
    public final IntVector2 b;
    
    public final long square;
    
    
    public Square(IntVector2 a, IntVector2 b) {
        this.a = a;
        this.b = b;
        
        // A single tile is 1x1 square, so add 1
        long x = Math.abs(a.x - b.x) + 1;
        long y = Math.abs(a.y - b.y) + 1;
        
        square = x * y;
    }

}
