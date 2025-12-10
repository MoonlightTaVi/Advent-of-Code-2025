package aoc.p008.nodes;

import java.util.ArrayList;
import java.util.List;

import aoc.FileReader;

public class JunctionSource extends FileReader {
    public final Node[] nodes;

    
    public JunctionSource() {
        nodes = super.read("008.txt")
                .stream()
                .map(Node::new)
                .toArray(Node[]::new);
    }
    
    
    public List<Connection> makeConnections() {
        List<Connection> connections = new ArrayList<>();
        
        int len = nodes.length;
        
        for (int i = 0; i < len; i++) {
            Node x = nodes[i];
            for (int j = i + 1; j < len; j++) {
                Node y = nodes[j];
                
                Connection c = new Connection(
                        x, y, 
                        x.vector.dst(y.vector)
                        );
                
                connections.add(c);
            }
        }
        
        connections.sort(
                (x, y) -> Double.compare(
                        x.distance, 
                        y.distance
                        )
                );
        
        return connections;
    }
    
}
