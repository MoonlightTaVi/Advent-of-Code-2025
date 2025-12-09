package aoc.p008.nodes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import aoc.shared.Vector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Node {
    
    public static Vector<Integer> getVector(String line) {
        int[] position = Arrays.stream(line.split(","))
                .mapToInt(Integer::valueOf)
                .toArray();
        Vector<Integer> vector = new Vector<>();
        int i = 0;
        vector.x = position[i++];
        vector.y = position[i++];
        vector.z = position[i++];
        return vector;
    }
    
    
    public int id;
    public Integer circuitId;
    public final Vector<Integer> vector;
    
    public Node closest = null;
    Integer lastClosest = null;
    @Getter
    private double dist = 0;
    
    
    public Node(int x, int y, int z) {
        this(new Vector<>(x, y, z));
    }
    
    public Node(String line) {
        this(getVector(line));
    }
    
    
    public double dst(Node other) {
        if (other == null) {
            return 0;
        }
        return vector.dst(other.vector);
    }
    
    public void connect(Node toAnother) {
        dist = dst(toAnother);
        closest = toAnother;
        if (dist < toAnother.dist) {
            toAnother.connect(this);
        }
    }
    
    public void disconnect() {
        //dist = 0;
        lastClosest = closest.id;
        closest = null;
    }
    
    public boolean isConnected(Node toAnother) {
        return closest != null && closest.equals(toAnother);
    }
    
    
    public int propagateId(int circuitId) {
        Set<Node> passedNodes = new HashSet<>();
        return propagateId(circuitId, passedNodes);
    }
    
    
    int propagateId(int circuitId, Set<Node> passedNodes) {
        passedNodes.add(this);
        
        if (this.circuitId == null) {
            this.circuitId = circuitId;
        } else {
            circuitId = Math.min(this.circuitId, circuitId);
        }
        
        if (isCircular(passedNodes)) {
            if (dist < closest.dist) {
                closest.disconnect();
            } else {
                this.disconnect();
            }
        }
        
        if (closest != null) {
            circuitId = Math.min(
                    closest.propagateId(circuitId, passedNodes), 
                    circuitId
                    );
        }
        
        this.circuitId = circuitId;
        return circuitId;
    }
    
    
    private boolean isCircular(Set<Node> passedNodes) {
        if (passedNodes.contains(closest)) {
            return true;
        }
        return false;
    }
    
    
    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Node b) {
            result = vector.equals(b.vector);
        }
        
        return result;
    }
    
    @Override
    public int hashCode() {
        return vector.hashCode();
    }
    
    String str() {
        return String.format(
                "[%s] %d#[%s]", 
                String.valueOf(circuitId), 
                id, 
                vector
                );
    }
    
    @Override
    public String toString() {
        return String.format(
                "[%s] %d#[%s] -(%.2f)-> %s", 
                String.valueOf(circuitId), 
                id, 
                vector,
                dist,
                closest == null ? String.format("[%d]", lastClosest) : closest.str()
                );
    }
}
