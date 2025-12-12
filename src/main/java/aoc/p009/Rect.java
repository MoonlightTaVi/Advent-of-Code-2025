package aoc.p009;

import java.util.Objects;

import aoc.shared.LongVector2;


/**
 * A rectangle that is made of 4 points. <br>
 * Can be created from either 3 points ABC (that form a corner) or
 * from two points (A and C, i.e. the opposite ones).
 * @see #overlaps(Rect)
 * @see #area()
 * @see #cross()
 * @see #isClockwise()
 */
public class Rect {

    public final LongVector2 a;
    public final LongVector2 b;
    public final LongVector2 c;
    public final LongVector2 d;
    
    private long area = 0;
    
    
    /**
     * Creates a rectangle from two opposite points (A and C in ABCD). 
     * <br> The order does not matter.
     * @param start
     * @param opposite
     */
    public Rect(LongVector2 start, LongVector2 opposite) {
        a = start;
        c = opposite;
        
        b = new LongVector2(c.x, a.y);
        d = new LongVector2(a.x, c.y);
    }
    
    /**
     * Creates a rectangle from 3 points that form a corner
     * (A, B and C in ABCD). The point D is calculated automatically.
     * <br> The order <b>does matter</b>.
     * @param a
     * @param b
     * @param c
     * @see #cross()
     */
    public Rect(LongVector2 a, LongVector2 b, LongVector2 c) {
        this.a = a;
        this.b = b;
        this.c = c;
        
        d = new LongVector2(a.x, c.y);
    }
    
    
    /**
     * Checks if this rectangle is overlapped by another rectangle.
     * @param another
     * @return True if these rectangles overlap each other.
     */
    public boolean overlaps(Rect another) {
        boolean left = x2() <= another.x1();
        boolean right = x1() >= another.x2();
        boolean top = y2() <= another.y1();
        boolean bottom = y1() >= another.y2();
        
        return !(left || right || top || bottom);
    }
    
    
    /**
     * Left-side X-value as long number.
     */
    public long x1() {
        return Math.min(a.x, c.x);
    }
    
    /**
     * Right-side X-value as long number.
     */
    public long x2() {
        return Math.max(a.x, c.x);
    }
    
    /**
     * Minimal Y-value as long number.
     */
    public long y1() {
        return Math.min(a.y, c.y);
    }
    
    /**
     * Maximal Y-value as long number.
     */
    public long y2() {
        return Math.max(a.y, c.y);
    }
    
    
    /**
     * Returns sign-less (absolute) area of the rectangle. <br>
     * Since a single tile (e.g. {@code (0;0)}) has an area of 1,
     * the area of a tiled rectangle is always greated than of 
     * a usual retangle (and the area is not equal to the crossproduct).
     * @return
     */
    public long area() {
        return area != 0 ? area : generateArea();
    }
    
    
    /**
     * Returns the sign of the cross product of this rectangle.
     * @return -1 or 1
     */
    public int crossSign() {
        return isClockwise() ? -1 : 1;
    }
    
    /**
     * Returns if the rectangle is made of a clockwise corner.
     * @return True if the ABC corner is a clockwise turn.
     */
    public boolean isClockwise() {
        return cross() > 0;
    }
    
    /**
     * Calculates the cross product of ABC. <br>
     * The absolute value of the cross product 
     * is <b>not equal to the area</b>.
     * @return Cross product of ABC.
     * @see #isClockwise()
     * @see #area()
     */
    public long cross() {
        LongVector2 vec1 = new LongVector2(b.x - a.x, b.y - a.y);
        LongVector2 vec2 = new LongVector2(c.x - b.x, c.y - b.y);
        
        return vec1.x * vec2.y - vec1.y * vec2.x;
    }
    
    
    /**
     * Generates the area upon first call.
     * @see #area()
     */
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
