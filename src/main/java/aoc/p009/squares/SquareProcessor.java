package aoc.p009.squares;

import aoc.shared.IntVector2;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class SquareProcessor {
    public final IntVector2[] tiles;
    
    
    public long getLargest() {
        int len = tiles.length;
        Square largest = null;
        
        for (int i = 0; i < len; i++) {
            IntVector2 a = tiles[i];
            for (int j = i + 1; j < len; j++) {
                IntVector2 b = tiles[j];
                
                Square newSquare = new Square(a, b);
                if (largest == null || largest.square < newSquare.square) {
                    largest = newSquare;
                }
            }
        }
        
        return largest.square;
    }
    
}
