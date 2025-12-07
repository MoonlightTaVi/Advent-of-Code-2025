package aoc.p005;


/**
 * FreshRange specifies the range of fresh ingredient IDs. <br>
 * Whenever an ID falls into the range, it is considered fresh;
 * otherwise it is spoiled.
 */
public class FreshRange {
    long min;
    long max;

    /**
     * Constructs the range from a String.
     * @param range String that follows the pattern: 'min-max'
     */
    public FreshRange(String range) {
        String[] parts = range.split("-");
        min = Long.parseLong(parts[0]);
        max = Long.parseLong(parts[1]);
    }

    /**
     * Constructs the range directly from minimum and maximum
     * boundaries.
     * @param min
     * @param max
     */
    public FreshRange(long min, long max) {
        this.min = min;
        this.max = max;
    }
    
    
    /**
     * The id is considered fresh if it is >= min and <= max.
     * @param id ID of the ingredient.
     * @return true if the ingredient is fresh.
     */
    public boolean isFresh(long id) {
        if (id < min) {
            return false;
        }
        
        if (id > max) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Check if this range overlaps with some other.
     * @param anotherRange Another range to check the overlapping with.
     * @return true if the two ranges overlap.
     */
    public boolean overlaps(FreshRange anotherRange) {
        boolean a = anotherRange.max < min;
        boolean b = anotherRange.min > max;
        return !(a || b);
    }
    
    /**
     * Makes a merge between this range and the other without changing
     * its own state.
     * @param anotherRange The range to merge with.
     * @return The result of the merge.
     */
    public FreshRange merge(FreshRange anotherRange) {
        long min = Math.min(this.min, anotherRange.min);
        long max = Math.max(this.max, anotherRange.max);
        return new FreshRange(min, max);
    }
    
    /**
     * Get the number of all possible fresh ingredient IDs within
     * this range.
     * @return Number of fresh ingredients.
     */
    public long length() {
        return max - min + 1;
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
