package aoc.p009.edges;

import java.util.ArrayList;
import java.util.List;

import aoc.p009.TileReader;
import aoc.shared.BinarySearch;
import aoc.shared.IntVector2;


public class Edges {

    public final VerticalEdge[] vertical;
    public final BinarySearch<IntVector2, VerticalEdge> raycastH;
    
    
    public Edges(TileReader reader) {
        IntVector2[] vectors = reader.tiles;
        int len = vectors.length;
        List<VerticalEdge> vertList = new ArrayList<>();
        
        for (int i = 0; i < len; i++) {
            IntVector2 vec1 = vectors[i];
            IntVector2 vec2 = vectors[(i + 1) % len];
            
            // Is not a vertical edge
            if (vec1.x != vec2.x) {
                continue;
            }
            
            VerticalEdge edge = new VerticalEdge(vec1.x, vec1.y, vec2.y);
            vertList.add(edge);
        }
        
        vertical = vertList.toArray(VerticalEdge[]::new);
        raycastH = new BinarySearch<>(vec -> vec.x, edge -> edge.x);
        raycastH.sort(vertical);
    }
    
    
    public int castLeft(IntVector2 vector) {
        return raycastH.castLeft(vector, vertical);
    }
    
    public int castRight(IntVector2 vector) {
        return raycastH.castRight(vector, vertical);
    }
}
