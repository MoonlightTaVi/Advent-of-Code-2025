package aoc.p005;

import java.util.Map.Entry;
import java.util.TreeMap;


public class Ranges {
    public TreeMap<Long, FreshRange> ranges = new TreeMap<>();
    
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
    
    public void pack() {
        TreeMap<Long, FreshRange> packedRanges = new TreeMap<>();
        Long[] keys = ranges.keySet().toArray(Long[]::new);
        for (int i = 0; i < keys.length; i++) {
            FreshRange rangeX = ranges.get(keys[i]);
            
            for (int j = i + 1; j < keys.length; j++) {
                FreshRange rangeY = ranges.get(keys[j]);
                if (rangeX.overlaps(rangeY)) {
                    rangeX = rangeX.merge(rangeY);
                } else {
                    i = j - 1;
                    break;
                }
            }
            
            packedRanges.put(rangeX.min, rangeX);
        }
        
        ranges = packedRanges;
    }
    
    public FreshRange getClosest(long toId) {
        FreshRange result = null;
        Entry<Long, FreshRange> entry = ranges.floorEntry(toId);
        if (entry != null) {
            result = entry.getValue();
        }
        return result;
    }
    
}
