package aoc.p009;

import aoc.shared.LongVector2;

public class Rect {

    public final LongVector2 a;
    public final LongVector2 b;
    public final LongVector2 c;
    public final LongVector2 d;
    
    
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
    
    
    public long square() {
        return (x2() - x1() + 1) * (y2() - y1() + 1);
    }
}
