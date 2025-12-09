package aoc.p008;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import aoc.FileReader;

public class Boxes extends FileReader {
    public final Box[] boxes;

    public Boxes() {
        super.read("008.txt");
        
        boxes = super.stream()
                .map(Box::new)
                .toArray(Box[]::new);
        
    }
    
    public void findClosest(int limit) {
        for (Box box : boxes) {
            box.connect(Arrays.stream(boxes)
                    .filter(b -> !box.equals(b))
                    .filter(b -> b.closest == null || !b.closest.equals(box))
                    .sorted(
                            (b1, b2) -> Double.compare(
                                    box.dst(b1), box.dst(b2)
                                )
                            )
                    .findFirst()
                    .orElse(null));
        }
        Arrays.sort(boxes, (x, y) -> Double.compare(x.getDist(), y.getDist()));
        for (int i = 0; i < boxes.length; i++) {
            Box box = boxes[i];
            box.id = i;
            if (i >= limit) {
                //box.id = null;
                box.closest = null;
            }
        }
    }
    
    public void connect(int limit) {
        int circuitId = 1;
        for (int i = 0; i < limit; i++) {
            Box box = boxes[i];
            
            // Resulted ID may be different
            int resultedId = box.propagateId(circuitId);
            
            // If the current ID propagated successfully
            //  - generate new ID
            if (circuitId == resultedId) {
                circuitId++;
            }
            
        }
        
    }
    
    public void print() {
        for (Box box : boxes) {
            System.out.printf(
                    "%s -> %s (%.2f)%n", 
                    box, box.closest, 
                    box.getDist()
                    );
        }
    }
    
    public long summarize(int limit) {
        Map<Integer, Long> count = Arrays.stream(boxes)
                .filter(b -> b.circuitId != null)
                .collect(
                        Collectors.groupingBy(
                                b -> b.circuitId, 
                                Collectors.counting()
                                )
                        );
        long[] l = count.entrySet()
                .stream()
                .sorted((x,y) -> Long.compare(y.getValue(), x.getValue()))
                .limit(limit)
                .mapToLong(Map.Entry::getValue)
                .toArray();
        long mul = l[0];
        for (int i = 1; i < l.length; i++) {
            mul *= l[i];
        }
        return mul;
    }
    
}
