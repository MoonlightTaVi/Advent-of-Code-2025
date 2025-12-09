package aoc.p008;

import java.util.Arrays;

public class Box {
    static int count = 0;
    
    int x;
    int y;
    int z;
    
    public Integer id;
    
    public Box closest = null;
    public double dist = 0;
    
    public Box(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        id = count++;
    }
    
    public Box(String line) {
        int[] position = Arrays.stream(line.split(","))
                .mapToInt(Integer::valueOf)
                .toArray();
        int i = 0;
        this.x = position[i++];
        this.y = position[i++];
        this.z = position[i++];
        id = count++;
    }
    
    public int[] vector() {
        return new int[] {x, y, z};
    }
    
    public double dst2(Box other) {
        int[] vec1 = vector();
        int[] vec2 = other.vector();
        int dst2 = 0;
        for (int i = 0; i < 3; i++) {
            int diff = vec1[i] - vec2[i];
            dst2 += diff * diff;
        }
        return dst2;
    }
    
    public double dst(Box other) {
        return Math.sqrt(dst2(other));
    }

    @Override
    public String toString() {
        return String.format("%d#(%d;%d;%d)", id, x, y, z);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Box b) {
            return x == b.x && y == b.y && z == b.z;
        }
        return false;
    }
    
    
}
