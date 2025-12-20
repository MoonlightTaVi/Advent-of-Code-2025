package aoc;

import java.util.Arrays;

import aoc.shared.IntVector2;
import aoc.shared.LongVector2;


/**
 * The class contains some static utility methods 
 * for working with spatial vectors.
 */
public class Vectors {

    /**
     * Parser a string line and converts it to an integer 2D vector.
     * @param fromLine Source line of the vector 
     * (integer values separated with a comma).
     * @return IntVector2 corresponding to this line.
     */
    public static IntVector2 parseIntVector(String fromLine) {
        int[] vec = Arrays.stream(fromLine.split(","))
                .mapToInt(Integer::valueOf)
                .toArray();
        
        int i = 0;
        return new IntVector2(vec[i++], vec[i++]);
    }

    /**
     * Parser a string line and converts it to a big-integer 2D vector.
     * A LongVector2 is more appropriate for big numbers than an
     * IntVector2.
     * @param fromLine Source line of the vector 
     * (integer values separated with a comma).
     * @return LongVector2 corresponding to this line.
     */
    public static LongVector2 parseLongVector(String fromLine) {
        long[] vec = Arrays.stream(fromLine.split(","))
                .mapToLong(Long::valueOf)
                .toArray();
        
        int i = 0;
        return new LongVector2(vec[i++], vec[i++]);
    }
    
}
