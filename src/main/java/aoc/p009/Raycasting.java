package aoc.p009;

import java.util.ArrayList;
import java.util.List;

import aoc.shared.LongVector2;

public class Raycasting {
    public final List<YLine> lines = new ArrayList<>();
    
    
    public boolean makeYLine(LongVector2 vec1, LongVector2 vec2) {
        if (vec1.y.equals(vec2.y)) {
            return false;
        }
        
        YLine line = new YLine(vec1, vec2);
        lines.add(line);
        return true;
    }
    
    
    public boolean isRectValid(Rect rect) {
        boolean testA = isVectorValid(rect.a);
        boolean testB = isVectorValid(rect.b);
        boolean testC = isVectorValid(rect.c);
        boolean testD = isVectorValid(rect.d);
        
        return testA && testB && testC && testD;
    }
    
    public boolean isVectorValid(LongVector2 vec) {
        boolean invalid = false;
        
        for (YLine line : lines) {
            if (vec.x.equals(line.x)) {
                continue;
            }
            
            if (line.intersects(vec)) {
                invalid = !invalid;
            }
        }
        
        return invalid;
    }
}
