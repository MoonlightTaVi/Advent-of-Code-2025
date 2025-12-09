package aoc.p008;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import aoc.FileReader;


public class NodeProcessor extends FileReader {
    HashMap<Integer, Circuit> circuits = new HashMap<>();
    
    public final Node[] boxes;

    public NodeProcessor() {
        super.read("008.txt");
        
        boxes = super.stream()
                .map(Node::new)
                .toArray(Node[]::new);
    }
    
    
    public void findClosest(int limit) {
        // Connect EACH to its closest first
        for (Node box : boxes) {
            box.connect(
                    Arrays
                    .stream(boxes).parallel()
                    // Prevent connecting to itself
                    .filter(b -> !box.equals(b))
                    // Prevent circular connections between two
                    .filter(b -> !b.isConnected(box))
                    // Sort by closest distance
                    .sorted(
                            (b1, b2) -> Double.compare(
                                    box.dst(b1), box.dst(b2)
                                )
                            )
                    // Get the closest, if possible
                    .findFirst()
                    .orElse(null)
                    );
        }
        
        // Sort by closest distance priority
        Arrays.sort(boxes, (x, y) -> Double.compare(x.getDist(), y.getDist()));
        
        // Prepare
        for (int i = 0; i < boxes.length; i++) {
            Node box = boxes[i];
            
            // Set IDs based on closest distance ordering
            box.id = i;
            
            // Disconnect to prevent further processing
            if (i >= limit) {
                box.closest = null;
            }
            
        }
    }
    
    public void connect() {
        int circuitId = 0;
        for (int i = 0; i < boxes.length; i++) {
            Node box = boxes[i];
            
            // Resulted ID may be different
            int resultedId = box.propagateId(circuitId);
            
            // If the current ID propagated successfully
            //  - generate new ID
            if (circuitId == resultedId) {
                circuitId++;
            }
            
        }
    }
    
    
    public long summarize(int limit) {
        for (Node box : boxes) {
            Circuit circuit = circuits.computeIfAbsent(
                    box.circuitId, 
                    id -> new Circuit(id)
                    );
            circuit.boxes.add(box);
        }
        
        int[] l = circuits
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .sorted((x,y) -> Long.compare(y.size(), x.size()))
                .mapToInt(Circuit::size)
                .limit(limit)
                .toArray();
        
        long mul = l[0];
        for (int i = 1; i < l.length; i++) {
            mul *= l[i];
        }
        return mul;
    }
    
    
    public void print() {
        for (Node box : boxes) {
            System.out.printf(
                    "%s -> %s (%.2f)%n", 
                    box, box.closest, 
                    box.getDist()
                    );
        }
    }
    
}
