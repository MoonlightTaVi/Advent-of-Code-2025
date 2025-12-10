package aoc.p008;

import java.util.List;

import aoc.p008.circuits.Circuits;
import aoc.p008.nodes.Connection;
import aoc.p008.nodes.JunctionSource;

public class MasterProcessor {

    public int getProduct() {
        JunctionSource source = new JunctionSource();
        List<Connection> connections = source.makeConnections();
        
        Circuits circuits = new Circuits(source.nodes);
        
        return circuits.getProduct(connections, 1000, 3);
    }
    
    public int getDistance() {
        JunctionSource source = new JunctionSource();
        List<Connection> connections = source.makeConnections();
        
        Circuits circuits = new Circuits(source.nodes);
        
        return circuits.getDistance(connections);
    }
    
}
