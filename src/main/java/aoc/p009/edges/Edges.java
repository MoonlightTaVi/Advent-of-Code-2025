package aoc.p009.edges;

import java.util.ArrayList;
import java.util.List;

import aoc.p009.TileReader;
import aoc.p009.squares.Corner;
import aoc.shared.BinarySearch;
import aoc.shared.IntVector2;


public class Edges {

    public final IntVector2[] vectors;
    public final VerticalEdge[] vertical;
    public final BinarySearch<IntVector2, VerticalEdge> raycastH;
    
    
    public Edges(TileReader reader) {
        vectors = reader.naturalOrder();
        int len = vectors.length;
        List<VerticalEdge> vertList = new ArrayList<>();
        
        for (int i = 0; i < len; i++) {
            IntVector2 vec1 = vectors[i];
            IntVector2 vec2 = vectors[(i + 1) % len];
            
            // Is not a vertical edge
            if (!vec1.x.equals(vec2.x)) {
                continue;
            }
            
            VerticalEdge edge = new VerticalEdge(vec1.x, vec1.y, vec2.y);
            vertList.add(edge);
        }
        
        vertical = vertList.toArray(VerticalEdge[]::new);
        raycastH = new BinarySearch<>(vec -> vec.x, edge -> edge.x);
        raycastH.sort(vertical);
    }
    
    
    public boolean isInsidePolygon(Corner corner) {
        if (corner.facesLeft()) {
            return hitLeft(corner.middle);
        } else {
            return hitRight(corner.middle);
        }
    }
    
    
    public boolean hitLeft(IntVector2 vector) {
        for (int i = vertical.length - 1; i >= 0; i--) {
            if (vertical[i].intersects(vector.y)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hitRight(IntVector2 vector) {
        for (int i = 0; i < vertical.length; i++) {
            if (vertical[i].intersects(vector.y)) {
                return true;
            }
        }
        return false;
    }
}
