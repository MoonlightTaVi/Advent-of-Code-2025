package aoc.p008;


import java.util.HashMap;
import java.util.Map;

import aoc.FileReader;
import aoc.p008.nodes.*;
import aoc.p008.sorting.*;


public class NodeProcessor extends FileReader {
    HashMap<Integer, Circuit> circuits = new HashMap<>();
    
    public final Nodes boxes;

    public NodeProcessor() {
        super.read("008.txt");
        
        boxes = new Nodes(
                    super.stream()
                        .map(Node::new)
                        .toArray(Node[]::new)
                );
    }
    
    
    public void findClosest(int limit) {
        // Connect EACH to its closest first
        boxes.findClosest();
        // Then limit the connections by removing excessive ones
        boxes.limitConnections(limit);
        // And finally connect all remaining in circuits
        boxes.makeConnections();
        
    }
    
    public void generateCircuits() {
        for (Node box : boxes) {
            Circuit circuit = circuits.computeIfAbsent(
                    box.circuitId, 
                    id -> new Circuit(id)
                    );
            circuit.add(box);
        }
    }
    
    
    public long calculateProduct(int limit) {
        int[] counted = circuits
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                // Sort by node count for each circuit, highest first
                .sorted((x,y) -> Long.compare(y.size(), x.size()))
                // Map to node count
                .mapToInt(Circuit::size)
                // Limit by only highest
                .limit(limit)
                .toArray();
        
        long mul = counted[0];
        for (int i = 1; i < counted.length; i++) {
            mul *= counted[i];
        }
        return mul;
    }
    
    
    public void print() {
        boxes.sort(new IdComparator());
        
        for (Node box : boxes) {
            System.out.printf(
                    "%s -> %s (%.2f)%n", 
                    box, box.closest, 
                    box.getDist()
                    );
        }
    }
    
}
