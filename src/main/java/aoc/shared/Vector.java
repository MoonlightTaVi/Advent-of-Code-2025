package aoc.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    
    public double dst(Vector<?> another) {
        return Math.sqrt(dst2(another));
    }
}
