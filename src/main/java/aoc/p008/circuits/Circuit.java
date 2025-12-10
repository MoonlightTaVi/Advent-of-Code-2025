package aoc.p008.circuits;

import java.util.HashSet;
import java.util.Set;

import aoc.p008.nodes.Node;
import aoc.shared.Id;

public class Circuit extends Id {

    public final int id;
    public final Set<Node> nodes = new HashSet<>();
    
    public Circuit(Node start) {
        id = start.circuitId;
        nodes.add(start);
    }
    
    
    public void add(Node node) {
        nodes.add(node);
    }
    
    public void merge(Circuit circuit) {
        for (Node node : circuit.nodes) {
            nodes.add(node);
            node.circuitId = id;
        }
    }
    
    public int size() {
        return nodes.size();
    }


    @Override
    public int getId() {
        return id;
    }
}
