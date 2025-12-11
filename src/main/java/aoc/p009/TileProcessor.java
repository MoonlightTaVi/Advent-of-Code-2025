package aoc.p009;

import java.util.HashSet;
import java.util.Set;

import aoc.shared.LongVector2;


public class TileProcessor {
    public final LongVector2[] vectors;
    
    public final Raycasting raycast = new Raycasting();
    
    public final Set<Rect> rects = new HashSet<>();
    public final Set<Rect> invalidRects = new HashSet<>();
    
    
    private long largestArea = 0;
    
    
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
            
            if (vec1.x.equals(vec2.x)) {
                raycast.makeYLine(vec1, vec2);
            }
            
            for (int j = i + 1; j <= vectors.length; j++) {
                vec2 = vectors[j % len];
                
                Rect dummy = new Rect(vec1, vec2);
                if (dummy.area() > largestArea) {
                    largestArea = dummy.area();
                }
                
                
            }
        }
        
        findInvalidRects();
        findOverlappingRects();
    }
    
    
    public long areaLargest() {
        return largestArea;
    }    
    
    public long areaLimited() {
        return rects.stream()
                .sorted(
                        (r1, r2) -> 
                        Long.compare(r2.area(), r1.area())
                        )
                .limit(1)
                .mapToLong(r -> r.area())
                .findFirst()
                .orElseThrow();
    }
    
    
    private void findInvalidRects() {
        for (Rect rect : rects) {
            if (!raycast.isRectValid(rect)) {
                invalidRects.add(rect);
            }
        }
        rects.removeAll(invalidRects);
    }
    
    private void findOverlappingRects() {
        for (Rect r1 : rects) {
            for (Rect r2 : invalidRects.toArray(Rect[]::new)) {
                if (r1.overlaps(r2)) {
                    invalidRects.add(r1);
                }
            }
        }
        rects.removeAll(invalidRects);
    }
}
