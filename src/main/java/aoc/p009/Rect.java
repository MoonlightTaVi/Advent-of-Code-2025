package aoc.p009;

import java.util.Objects;

import aoc.shared.LongVector2;


public class Rect {

    public final LongVector2 a;
    public final LongVector2 b;
    public final LongVector2 c;
    public final LongVector2 d;
    
    private long area = 0;
    
    
    public Rect(LongVector2 start, LongVector2 opposite) {
        a = start;
        c = opposite;
        
        b = new LongVector2(c.x, a.y);
        d = new LongVector2(a.x, c.y);
    }
    
    public Rect(LongVector2 a, LongVector2 b, LongVector2 c) {
        this.a = a;
        this.b = b;
        this.c = c;
        
        d = new LongVector2(a.x, c.y);
    }
    
    
    public boolean overlaps(Rect another) {
        boolean left = x2() <= another.x1();
        boolean right = x1() >= another.x2();
        boolean top = y2() <= another.y1();
        boolean bottom = y1() >= another.y2();
        
        return !(left || right || top || bottom);
    }
    
    
    public long x1() {
        return Math.min(a.x, c.x);
    }
    
    public long x2() {
        return Math.max(a.x, c.x);
    }
    
    public long y1() {
        return Math.min(a.y, c.y);
    }
    
    public long y2() {
        return Math.max(a.y, c.y);
    }
    
    
    public long area() {
        return area != 0 ? area : generateArea();
    }
    
    
    public int crossSign() {
        return isClockwise() ? -1 : 1;
    }
    
    public boolean isClockwise() {
        return cross() < 0;
    }
    
    public long cross() {
        LongVector2 vec1 = new LongVector2(b.x - a.x, b.y - a.y);
        LongVector2 vec2 = new LongVector2(c.x - b.x, c.y - b.y);
        
        return vec1.x * vec2.y - vec1.y * vec2.x;
    }
    
    
    private long generateArea() {
        area = (x2() - x1() + 1) * (y2() - y1() + 1);
        return area;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Rect r) {
            return hashCode() == r.hashCode();
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d);
    }
}
