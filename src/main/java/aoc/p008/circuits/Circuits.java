package aoc.p008.circuits;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aoc.p008.nodes.Connection;
import aoc.p008.nodes.Node;


public class Circuits {
    
    final Map<Integer, Circuit> map = new HashMap<>();
    final int maxSize;

    
    public Circuits(Node[] fromNodes) {
        maxSize = fromNodes.length;
        for (Node node : fromNodes) {
            map.put(node.circuitId, new Circuit(node));
        }
    }
    
    
    public int getDistance(List<Connection> fromConnections) {
        Connection last = build(fromConnections, fromConnections.size());
        return last.left.vector.x * last.right.vector.x;
    }
    
    public int getProduct(
                List<Connection> fromConnections, 
                int limitConnections, 
                int limitMax
            ) {
        
        build(fromConnections, limitConnections);
        
        int[] sizes = map.entrySet().stream()
                .map(e -> e.getValue())
                .sorted((x, y) -> y.size() - x.size())
                .limit(limitMax)
                .mapToInt(c -> c.size())
                .toArray();
        
        int result = 1;
        for (int n : sizes) {
            result *= n;
        }
        
        return result;
    }
    
    
    private Connection build(List<Connection> fromConnections, int limit) {
        for (int i = 0; i < limit; i++) {
            Connection connection = fromConnections.get(i);
            Node x = connection.left;
            Node y = connection.right;
            
            if (x.circuitId == y.circuitId) {
                continue;
            }
            
            Circuit c1 = map.get(x.circuitId);
            Circuit c2 = map.get(y.circuitId);

            map.remove(y.circuitId);
            c1.merge(c2);
            
            if (c1.size() == maxSize) {
                return connection;
            }
        }
        
        return null;
    }
}
