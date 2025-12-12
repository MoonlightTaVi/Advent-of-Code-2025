package aoc.p009;

import java.util.Objects;

import aoc.shared.LongVector2;

public class Rect {

    public final LongVector2 a;
    public final LongVector2 b;
    public final LongVector2 c;
    public final LongVector2 d;
    
    private LongVector2 center = null;
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
    
    
    public LongVector2 center() {
        return center != null ? center : generateCenter();
    }
    
    
    public long area() {
        return area != 0 ? area : generateArea();
    }
    
    public long orientedArea() {
        return area() * areaSign();
    }
    
    
    public int areaSign() {
        return areaNegaive() ? -1 : 1;
    }
    
    public boolean areaNegaive() {
        long basicOrientedArea = (a.x * b.y - b.x * a.y)
                + (b.x * c.y - c.x * b.y);
        return basicOrientedArea < 0;
    }
    
    
    private long generateArea() {
        area = (x2() - x1() + 1) * (y2() - y1() + 1);
        return area;
    }
    
    private LongVector2 generateCenter() {
        center = new LongVector2(
                x1() + (x2() - x1()) / 2, 
                y1() + (y2() - y1()) / 2
                );
        return center;
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
