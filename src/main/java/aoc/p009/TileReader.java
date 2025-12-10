package aoc.p009;

import java.util.Arrays;

import aoc.FileReader;
import aoc.Vectors;
import aoc.shared.IntVector2;


public class TileReader extends FileReader {
    public final IntVector2[] tiles;

    public TileReader() {
        tiles = super.read("009.txt").stream()
                .map(Vectors::parseIntVector)
                .toArray(IntVector2[]::new);
    }
    
    
    public IntVector2[] sortedByX() {
        Arrays.sort(tiles, (a, b) -> a.x - b.x);
        return tiles;
    }
    
}
