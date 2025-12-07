package aoc.p005;

import java.util.Comparator;
import java.util.TreeSet;

public class Ranges {
    public TreeSet<FreshRange> ranges = new TreeSet<>(new RangeComparator());
    
    public void add(FreshRange range) {
        ranges.add(range);
    }
    
    public void pack() {
        TreeSet<FreshRange> packedRanges = new TreeSet<>(new RangeComparator());
        FreshRange[] rangesArr = ranges.toArray(FreshRange[]::new);
        for (int i = 0; i < rangesArr.length; i++) {
            FreshRange rangeX = rangesArr[i];
            if (i == rangesArr.length - 1) {
                packedRanges.add(rangeX);
                break;
            }
            FreshRange rangeY = rangesArr[i + 1];
            
            if (rangeX.overlaps(rangeY)) {
                rangeX = rangeX.merge(rangeY);
                i++;
            }
            
            packedRanges.add(rangeX);
        }
        
        ranges = packedRanges;
    }
    
    public FreshRange getClosest(long toId) {
        FreshRange temp = new FreshRange(toId, toId);
        FreshRange result = ranges.floor(temp);
        return result;
    }
    
    class RangeComparator implements Comparator<FreshRange> {

        @Override
        public int compare(FreshRange o1, FreshRange o2) {
            int byMin = Long.compare(o1.min, o2.min);
            int byMax = Long.compare(o1.max, o2.max);
            return byMin != 0 ? byMin : byMax;
        }
        
    }
    
}
