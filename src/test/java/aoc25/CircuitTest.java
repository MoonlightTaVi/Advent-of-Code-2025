package aoc25;

import java.util.List;

import org.junit.jupiter.api.*;

import aoc.p008.circuits.Circuits;
import aoc.p008.nodes.Connection;
import aoc.p008.nodes.JunctionSource;


public class CircuitTest {
    static JunctionSource source;
    static List<Connection> connections;

    
    @BeforeEach
    public void setup() {
        source = new JunctionSource();
        connections = source.makeConnections();
    }

    
    @Test
    public void productTest() {
        Circuits circuits = new Circuits(source.nodes);
        
        int product = circuits.getProduct(connections, 10, 3);
        int expected = 40;
        
        Assertions.assertEquals(expected, product);
    }

    @Test
    public void distanceTest() {
        Circuits circuits = new Circuits(source.nodes);
        
        int distance = circuits.getDistance(connections);
        int expected = 25272;
        
        Assertions.assertEquals(expected, distance);
    }
    
}
