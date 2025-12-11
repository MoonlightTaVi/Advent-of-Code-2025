package aoc.p009;

import java.util.HashSet;
import java.util.Set;

import aoc.shared.LongVector2;


public class TileProcessor {
    public final LongVector2[] vectors;
    
    public final Raycasting raycast = new Raycasting();
    public final Set<LongVector2> horizontal = new HashSet<>();
    public final Set<Rect> rects = new HashSet<>();
    
    
    public TileProcessor(TileReader reader) {
        vectors = reader.naturalOrder();
    }
    
    
    public void process() {
        int len = vectors.length;
        for (int i = 0; i < len; i++) {
            LongVector2 vec1 = vectors[i];
            LongVector2 vec2 = vectors[(i + 1) % len];
            LongVector2 vec3 = vectors[(i + 2) % len];
            
            rects.add(new Rect(vec1, vec2, vec3));
            
            for (int j = i + 1; j <= vectors.length; j++) {
                vec2 = vectors[j % len];
                rects.add(new Rect(vec1, vec2));
                
                if (vec1.y.equals(vec2.y)) {
                    horizontal.add(vec1);
                    horizontal.add(vec2);
                }
                
                if (vec1.x.equals(vec2.x)) {
                    raycast.makeYLine(vec1, vec2);
                }
            }
        }
        
        raycast.updateIgnored(horizontal);
    }
    
    
    public long areaLargest() {
        return rects.stream()
                .sorted(
                        (r1, r2) -> 
                        Long.compare(r2.square(), r1.square())
                        )
                .limit(1)
                .mapToLong(r -> r.square())
                .findFirst()
                .orElseThrow();
    }    
    
    public long areaLimited() {
        return rects.stream()
                .filter(r -> raycast.isRectValid(r))
                .sorted(
                        (r1, r2) -> 
                        Long.compare(r2.square(), r1.square())
                        )
                .limit(1)
                .mapToLong(r -> r.square())
                .findFirst()
                .orElseThrow();
    }
}
