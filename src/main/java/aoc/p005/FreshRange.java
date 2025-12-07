package aoc.p005;


public class FreshRange {
    long min;
    long max;

    public FreshRange(String range) {
        String[] parts = range.split("-");
        min = Long.parseLong(parts[0]);
        max = Long.parseLong(parts[1]);
    }

    public FreshRange(long min, long max) {
        this.min = min;
        this.max = max;
    }
    
    
    public boolean isFresh(long id) {
        if (id < min) {
            return false;
        }
        
        if (id > max) {
            return false;
        }
        
        return true;
    }
    
    public boolean overlaps(FreshRange anotherRange) {
        boolean a = anotherRange.max < min;
        boolean b = anotherRange.min > max;
        return !(a || b);
    }
    
    public FreshRange merge(FreshRange anotherRange) {
        long min = Math.min(this.min, anotherRange.min);
        long max = Math.max(this.max, anotherRange.max);
        return new FreshRange(min, max);
    }
    
    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof FreshRange r) {
            result = min == r.min;
            result = result && max == r.max;
        }
        return result;
    }
    
    @Override
    public int hashCode() {
        return Long.hashCode(min);
    }
    
    @Override 
    public String toString() {
        return String.format("%d-%d", min, max);
    }
    
}
