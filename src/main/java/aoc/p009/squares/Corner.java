package aoc.p009.squares;

import aoc.shared.IntVector2;

public class Corner extends Square {
    
    public final IntVector2 corner;
    public final IntVector2 middle;

    public Corner(IntVector2 a, IntVector2 cornerAB, IntVector2 b) {
        super(a, b);
        corner = cornerAB;
        middle = findMiddle();
    }
    
    
    public boolean facesLeft() {
        return middle.x < corner.x;
    }
    
    public boolean facesRight() {
        return middle.x > corner.x;
    }
    
    
    private IntVector2 findMiddle() {
        int x1 = Math.min(super.a.x, super.b.x);
        int x2 = Math.max(super.a.x, super.b.x);
        int y1 = Math.min(super.a.y, super.b.y);
        int y2 = Math.max(super.a.y, super.b.y);
        
        return new IntVector2(x1 + (x2 - x1) / 2, y1 + (y2 - y1) / 2);
    }

}
