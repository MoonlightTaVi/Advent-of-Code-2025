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
            box.closest = Arrays.stream(boxes)
                    .filter(b -> !box.equals(b))
                    .filter(b -> b.closest == null || !b.closest.equals(box))
                    .sorted(
                            (b1, b2) -> Double.compare(
                                    box.dst2(b1), box.dst2(b2)
                                )
                            )
                    .findFirst()
                    .orElse(null);
            if (box.closest != null) {
                box.dist = box.dst(box.closest);
            }
        }
        Arrays.sort(boxes, (x, y) -> Double.compare(x.dist, y.dist));
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
        for (int i = 0; i < limit; i++) {
            Box box = boxes[i];
            if (box.closest == null || box.id == null || box.closest.id == null) {
                continue;
            }
            int id = Math.min(box.id, box.closest.id);
            if (box.closest.closest != null && box.closest.closest.id != null) {
                id = Math.min(id, box.closest.closest.id);
                box.closest.closest.id = id;
            }
            box.id = id;
            box.closest.id = id;
        }
    }
    
    public void print(int limit) {
        for (int i = 0; i < limit; i++) {
            Box box = boxes[i];
            System.out.printf("%s -> %s (%.2f)%n", box, box.closest, box.dist);
        }
    }
    
    public long summarize(int limit) {
        Map<Integer, Long> count = Arrays.stream(boxes)
                .filter(b -> b.id != null)
                .collect(
                        Collectors.groupingBy(
                                b -> b.id, 
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
