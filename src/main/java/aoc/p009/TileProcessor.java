package aoc.p009;

import java.util.HashSet;
import java.util.Set;

import aoc.shared.LongVector2;


public class TileProcessor {
    public final LongVector2[] vectors;
    
    public final Set<Rect> insideRects = new HashSet<>();
    public final Set<Rect> outsideRects = new HashSet<>();
    
    
    private long largestArea = 0;
    private long largestAreaInside = 0;
    
    
    public TileProcessor(TileReader reader) {
        vectors = reader.naturalOrder();
    }

    
    public long areaLargest() {
        return largestArea;
    }    
    
    public long areaInside() {
        return largestAreaInside;
    }
    
    
    public void processCorners() {
        int len = vectors.length;
        for (int i = 0; i < len; i++) {
            LongVector2 vecA = vectors[i];
            LongVector2 vecB = vectors[(i + 1) % len];
            LongVector2 vecC = vectors[(i + 2) % len];
            
            Rect rect = new Rect(vecA, vecB, vecC);
            
            if (rect.areaNegative()) {
                outsideRects.add(rect);
            } else {
                insideRects.add(rect);
            }
        }
    }
    
    public void processAll() {
        int len = vectors.length;
        for (int i = 0; i < len; i++) {
            LongVector2 start = vectors[i];
            for (int j = i + 1; j <= len; j++) {
                LongVector2 opposite = vectors[j % len];
                
                Rect rect = new Rect(start, opposite);
                
                long area = rect.orientedArea();
                
                if (area > largestArea) {
                    largestArea = area;
                }
                
                if (area > largestAreaInside && isInsidePolygon(rect)) {
                    largestAreaInside = area;
                }
            }
        }
    }
    
    
    public boolean isInsidePolygon(Rect rect) {
        if (outsideRects.isEmpty()) {
            throw new IllegalStateException("Polygon is not initialized");
        }
        
        for (Rect outsideRect : outsideRects) {
            
            if (rect.overlaps(outsideRect)) {
                return false;
            }
            
        }
        
        return true;
    }
    
}
