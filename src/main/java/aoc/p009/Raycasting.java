package aoc.p009;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aoc.shared.LongVector2;
import lombok.Getter;

public class Raycasting {
    public final List<YLine> lines = new ArrayList<>();
    
    @Getter
    private Set<LongVector2> ignored = new HashSet<>();
    
    
    public void updateIgnored(Set<LongVector2> fromSet) {
        ignored.addAll(fromSet);
        fromSet.clear();
    }
    
    
    public boolean makeYLine(LongVector2 vec1, LongVector2 vec2) {
        if (vec1.y.equals(vec2.y)) {
            return false;
        }
        
        YLine line = new YLine(vec1, vec2);
        lines.add(line);
        return true;
    }
    
    
    public boolean isRectValid(Rect rect) {
        LongVector2 center = rect.center();
        
        return isVectorValid(center);
    }
    
    public boolean isVectorValid(LongVector2 vec) {
        boolean valid = false;
        
        for (YLine line : lines) {
            if (vec.x.equals(line.x)) {
                continue;
            }
            
            if (line.intersects(vec)) {
                valid = !valid;
            }
        }
        
        return valid;
    }
}
