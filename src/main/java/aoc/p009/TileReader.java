package aoc.p009;

import java.util.Arrays;

import aoc.FileReader;
import aoc.Vectors;
import aoc.shared.IntVector2;


public class TileReader extends FileReader {
    private final IntVector2[] tiles;

    public TileReader() {
        tiles = super.read("009.txt").stream()
                .map(Vectors::parseIntVector)
                .toArray(IntVector2[]::new);
    }
    
    
    public IntVector2[] naturalOrder() {
        return tiles;
    }
    
    public IntVector2[] sortedByX() {
        IntVector2[] copied = tiles.clone();
        Arrays.sort(copied, (a, b) -> a.x - b.x);
        return tiles;
    }
    
}
