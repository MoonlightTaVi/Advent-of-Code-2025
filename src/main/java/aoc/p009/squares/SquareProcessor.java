package aoc.p009.squares;

import aoc.p009.TileReader;
import aoc.p009.edges.Edges;
import aoc.shared.IntVector2;


public class SquareProcessor {
    
    public long getLargest(TileReader reader) {
        IntVector2[] vectors = reader.sortedByX();
        int len = vectors.length;
        
        Square largest = null;
        
        for (int i = 0; i < len; i++) {
            IntVector2 a = vectors[i];
            for (int j = i + 1; j < len; j++) {
                IntVector2 b = vectors[j];
                
                Square newSquare = new Square(a, b);
                if (largest == null || largest.square < newSquare.square) {
                    largest = newSquare;
                }
            }
        }
        
        return largest.square;
    }
    
    
    public long getRedGreen(Edges polygon) {
        // Vectors are in their natural order
        IntVector2[] vectors = polygon.vectors;
        int len = vectors.length;
        
        Corner largest = null;
        
        for (int i = 0; i < len; i++) {
            IntVector2 a = vectors[i];
            IntVector2 b = vectors[(i + 1) % len];
            IntVector2 c = vectors[(i + 2) % len];
            
            Corner corner = new Corner(a, b, c);
            
            // The corner is not red-green
            if (!polygon.isInsidePolygon(corner)) {
                continue;
            }
            
            if (largest == null || corner.square > largest.square) {
                largest = corner;
            }
        }
        
        return largest.square;
    }
    
}
