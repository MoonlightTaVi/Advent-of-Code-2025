package aoc.p005;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;


/**
 * Stores a collection of freshness ranges.
 */
public class Ranges implements Iterable<FreshRange> {
    public TreeMap<Long, FreshRange> ranges = new TreeMap<>();
    
    /**
     * Adds a new range to the collection. Merges with some existing
     * if possible.
     * @param range FreshRange to add.
     */
    public void add(FreshRange range) {
        long key = range.min;
        if (ranges.containsKey(key)) {
            FreshRange prev = ranges.remove(key);
            prev = prev.merge(range);
            ranges.put(key, prev);
        } else {
            ranges.put(key, range);
        }
    }
    
    /**
     * Merges all overlapping ranges.
     */
    public void pack() {
        TreeMap<Long, FreshRange> packedRanges = new TreeMap<>();
        Long[] keys = ranges.keySet().toArray(Long[]::new);
        for (int i = 0; i < keys.length; i++) {
            FreshRange rangeX = ranges.get(keys[i]);
            
            for (int j = i + 1; j < keys.length; j++) {
                FreshRange rangeY = ranges.get(keys[j]);
                if (rangeX.overlaps(rangeY)) {
                    rangeX = rangeX.merge(rangeY);
                    i = j;
                } else {
                    break;
                }
            }
            
            packedRanges.put(rangeX.min, rangeX);
        }
        
        ranges = packedRanges;
    }
    
    /**
     * Returns the closest range corresponding to the ID.
     * @param toId ID to find a range for.
     * @return FreshRange for this ID if exists, null otherwise.
     */
    public FreshRange getClosest(long toId) {
        FreshRange result = null;
        Entry<Long, FreshRange> entry = ranges.floorEntry(toId);
        if (entry != null) {
            result = entry.getValue();
        }
        return result;
    }

    @Override
    public Iterator<FreshRange> iterator() {
        return ranges.entrySet().
                stream()
                .map(e -> e.getValue())
                .toList()
                .iterator();
    }
    
}
