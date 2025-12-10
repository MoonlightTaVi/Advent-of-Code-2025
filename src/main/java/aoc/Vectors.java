package aoc;

import java.util.Arrays;

import aoc.shared.IntVector2;

public class Vectors {

    public static IntVector2 parseIntVector(String fromLine) {
        int[] vec = Arrays.stream(fromLine.split(","))
                .mapToInt(Integer::valueOf)
                .toArray();
        
        int i = 0;
        return new IntVector2(vec[i++], vec[i++]);
    }
    
}
