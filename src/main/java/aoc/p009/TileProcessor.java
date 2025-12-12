package aoc.p009;

import java.util.HashSet;
import java.util.Set;

import aoc.shared.LongVector2;


/**
 * Processes rectangles made of tiles. <br>
 * Returns the rectangle with the largest area (overall or only
 * inside the polygon).
 */
public class TileProcessor {
    /** Points of a polygon. */
    public final LongVector2[] vectors;
    
    public final Set<Rect> outerRects = new HashSet<>();
    
    
    private Rect largest = null;
    private long largestArea = 0;
    private Rect largestInner = null;
    private long largestInnerArea = 0;
    
    
    public TileProcessor(TileReader reader) {
        vectors = reader.naturalOrder();
    }

    
    /**
     * The rectangle with the largest area. <br>
     * {@code processAll()} must be called first to find this rectangle.
     * @see #processAll()
     */
    public Rect getLargest() {
        return largest;
    }    
    
    /**
     * The rectangle with the largest area that is inside the polygon.
     * {@code processAll()} must be called first to find this rectangle.
     * @see #processAll()
     */
    public Rect getInnerLargest() {
        return largestInner;
    }
    
    
    /**
     * Finds all outer corners of a polygon.
     */
    public void makePolygon() {
        int len = vectors.length;
        for (int i = 0; i < len; i++) {
            LongVector2 vecA = vectors[i];
            LongVector2 vecB = vectors[(i + 1) % len];
            LongVector2 vecC = vectors[(i + 2) % len];
            
            Rect rect = new Rect(vecA, vecB, vecC);
            
            if (!rect.isClockwise()) {
                outerRects.add(rect);
            }
        }
    }
    
    /**
     * Processes all rectangles; finds and stores the ones with
     * the largest areas. Call {@code makePolygon()} first.
     * @see #getLargest()
     * @see #getInnerLargest()
     */
    public void processAll() {
        int len = vectors.length;
        for (int i = 0; i < len; i++) {
            LongVector2 start = vectors[i];
            for (int j = i + 1; j <= len; j++) {
                LongVector2 opposite = vectors[j % len];
                
                Rect rect = new Rect(start, opposite);
                
                long area = rect.area();
                
                if (area > largestArea) {
                    largest = rect;
                    largestArea = area;
                }
                
                if (area > largestInnerArea && isInsidePolygon(rect)) {
                    largestInner = rect;
                    largestInnerArea = area;
                }
            }
        }
    }
    
    
    /**
     * Checks if a rectangle is inside the polygon.
     * @param rect
     * @return
     */
    public boolean isInsidePolygon(Rect rect) {
        if (outerRects.isEmpty()) {
            throw new IllegalStateException("Polygon is not initialized");
        }
        
        for (Rect outsideRect : outerRects) {
            
            if (rect.overlaps(outsideRect)) {
                return false;
            }
            
        }
        
        return true;
    }
    
}
