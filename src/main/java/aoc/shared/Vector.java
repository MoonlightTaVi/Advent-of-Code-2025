package aoc.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Generic spatial 3D vector.
 * @param <T> Numeric type of a vector coordinate value.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vector<T extends Number> {
    public T x;
    public T y;
    public T z;

    
    long[] toLongArray() {
        long[] vector = new long[3];
        int i = 0;
        vector[i++] = x.longValue();
        vector[i++] = y.longValue();
        vector[i++] = z.longValue();
        return vector;
    }
    
    
    /**
     * Returns squared distance of this vector to another one. <br>
     * Faster than {@code dst()}, therefore is useful for basic 
     * comparison.
     * @param another Vector to find the distance to. The generic type
     * of the another vector may be different from this one's.
     * @return Distance between two vectors to the second power (squared
     * value of the distance) as a long number.
     */
    public long dst2(Vector<?> another) {
        long dst2 = 0;
        long[] vec1 = toLongArray();
        long[] vec2 = another.toLongArray();
        for (int i = 0; i < 3; i++) {
            long n = vec1[i] - vec2[i];
            dst2 += n * n;
        }
        return dst2;
    }
    
    /**
     * Returns a distance between two vectors 
     * (this one and the other one).
     * @param another Vector to find the distance to. The generic type
     * of the another vector may be different from this one's.
     * @return Distance between two vectors as a double precision
     * floating point number.
     */
    public double dst(Vector<?> another) {
        return Math.sqrt(dst2(another));
    }
}
