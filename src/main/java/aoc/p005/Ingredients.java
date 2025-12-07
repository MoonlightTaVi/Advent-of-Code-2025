package aoc.p005;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import aoc.FileReader;


/**
 * Master class for ingredient management.
 */
public class Ingredients extends FileReader {
    public Ranges ranges = new Ranges();
    public List<String> ids = new ArrayList<>();

    /**
     * Reads ingredient IDs and fresh ingredient ranges from a file.
     * @param filename
     */
    public Ingredients(String filename) {
        String[] lines = super.read(filename).lines;
        
        boolean readingRanges = true;
        
        for (String line : lines) {
            if (line.isBlank()) {
                readingRanges = false;
                continue;
            }
            
            if (readingRanges) {
                FreshRange range = new FreshRange(line);
                ranges.add(range);
                
            } else {
                ids.add(line);
                
            }
        }
    }
    
    /**
     * Merges all overlapping ranges.
     */
    public void pack() {
        ranges.pack();
    }
    
    /**
     * Returns the collection of IDs.
     * @return
     */
    public Collection<String> ids() {
        return ids;
    }
    
    /**
     * Finds the most close range to the specified ID.
     * @param forId ID to find the range for.
     * @return FreshRange of this ID (if any exists, null otherwise).
     */
    public FreshRange getClosestRange(long forId) {
        return ranges.getClosest(forId);
    }
    
}
