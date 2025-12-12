package aoc.p009;

import java.util.Arrays;

import aoc.FileReader;
import aoc.Vectors;
import aoc.shared.LongVector2;


/**
 * Reads tiles from a file and stores them as LongVector2 objects.
 * <br> Can return vectors in either natural order or sorted by X-value.
 */
public class TileReader extends FileReader {
    private final LongVector2[] vectors;

    public TileReader() {
        vectors = super.read("009.txt").stream()
                .map(Vectors::parseLongVector)
                .toArray(LongVector2[]::new);
    }
    
    /**
     * Returns the array of tiles in their order of appearance.
     * @return Array of tiles as LongVector2's.
     */
    public LongVector2[] naturalOrder() {
        return vectors;
    }
    
    /**
     * Sorts vectors by X-value and returns a copy of the array. <br>
     * Is not used.
     * @return
     */
    @Deprecated
    public LongVector2[] sortedByX() {
        LongVector2[] copied = vectors.clone();
        Arrays.sort(copied, (a, b) -> Long.compare(a.x, b.x));
        return vectors;
    }
    
}
