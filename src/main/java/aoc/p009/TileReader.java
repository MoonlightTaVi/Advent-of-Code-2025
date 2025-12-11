package aoc.p009;

import java.util.Arrays;

import aoc.FileReader;
import aoc.Vectors;
import aoc.shared.LongVector2;


public class TileReader extends FileReader {
    private final LongVector2[] vectors;

    public TileReader() {
        vectors = super.read("009.txt").stream()
                .map(Vectors::parseLongVector)
                .toArray(LongVector2[]::new);
    }
    
    
    public LongVector2[] naturalOrder() {
        return vectors;
    }
    
    public LongVector2[] sortedByX() {
        LongVector2[] copied = vectors.clone();
        Arrays.sort(copied, (a, b) -> Long.compare(a.x, b.x));
        return vectors;
    }
    
}
