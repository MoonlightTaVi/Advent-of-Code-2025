package aoc.p008.nodes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import aoc.p008.sorting.DistanceComparator;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Nodes implements Iterable<Node> {
    public final Node[] nodes;
    
    
    public void findClosest() {
     // Connect EACH to its closest first
        for (Node box : nodes) {
            box.connect(
                    Arrays
                    .stream(nodes).parallel()
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
    }
    
    public void limitConnections(int limit) {
     // Sort by closest distance priority
        Arrays.sort(nodes, new DistanceComparator());
        
        // Prepare
        for (int i = 0; i < nodes.length; i++) {
            Node box = nodes[i];
            
            // Set IDs based on closest distance ordering
            box.id = i;
            
            // Disconnect to prevent further processing
            if (i >= limit) {
                box.closest = null;
            }
            
        }
    }
    
    public void makeConnections() {
        int circuitId = 0;
        for (int i = 0; i < nodes.length; i++) {
            Node box = nodes[i];
            
            // Resulted ID may be different
            int resultedId = box.propagateId(circuitId);
            
            // If the current ID propagated successfully
            //  - generate new ID
            if (circuitId == resultedId) {
                circuitId++;
            }
            
        }
    }
    
    
    public void sort(Comparator<Node> comparator) {
        Arrays.sort(nodes, comparator);
    }

    @Override
    public Iterator<Node> iterator() {
        return Arrays.asList(nodes).iterator();
    }
}
