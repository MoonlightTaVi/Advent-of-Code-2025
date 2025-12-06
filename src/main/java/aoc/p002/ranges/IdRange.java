package aoc.p002.ranges;

import java.util.Iterator;


/**
 * This is an iterable that is used to iterate a range of IDs.
 */
public class IdRange implements Iterable<String> {
    public String rangeStr;

    /**
     * Constructs IdRange from a string range. <br>
     * The range follows the pattern: min_number-max_number;
     * for example 11-22.
     * @param rangeStr Range as a String.
     */
    public IdRange(String rangeStr) {
        this.rangeStr = rangeStr;
    }

    @Override
    public Iterator<String> iterator() {
        return new RangeIterator(this);
    }
    
    
    class RangeIterator implements Iterator<String> {
        long start;
        long step;
        long end;
        
        public RangeIterator(IdRange idRange) {
            String[] rangeStr = idRange.rangeStr.split("-");
            start = Long.parseLong(rangeStr[0]);
            step = start;
            end = Long.parseLong(rangeStr[1]);
        }

        @Override
        public boolean hasNext() {
            return step <= end;
        }

        @Override
        public String next() {
            return String.valueOf(step++);
        }
        
    }

}
