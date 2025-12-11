package aoc.p009;

import aoc.shared.LongVector2;


public class YLine {

    public final long x;
    public final long y1;
    public final long y2;
    
    
    public YLine(LongVector2 vec1, LongVector2 vec2) {
        if (vec1.y.equals(vec2.y)) {
            throw new IllegalArgumentException(
                    String.format(
                            "Y values are the same: [%s]:[%s]", 
                            vec1, 
                            vec2
                            )
                    );
        }
        
        x = vec1.x;
        y1 = Math.min(vec1.y, vec2.y);
        y2 = Math.max(vec1.y, vec2.y);
    }
    
    
    public boolean intersects(LongVector2 vector) {
        return vector.x < x && !(vector.y < y1 || vector.y > y2);
    }
}
