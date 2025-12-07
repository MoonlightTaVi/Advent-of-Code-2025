package aoc.p005;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import aoc.FileReader;


public class Ingredients extends FileReader {
    public Ranges ranges = new Ranges();
    public List<String> ids = new ArrayList<>();

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
    
    public void pack() {
        ranges.pack();
    }
    
    public Collection<String> ids() {
        return ids;
    }
    
    
    public FreshRange getClosestRange(long forId) {
        return ranges.getClosest(forId);
    }
    
}
