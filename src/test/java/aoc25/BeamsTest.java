package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p007.*;

public class BeamsTest {
    static Map map;
    static Engine engine;
    
    @BeforeAll
    public static void setup() {
        map = new Map(new MapReader("007.txt").map);
        engine = new Engine(map);
    }
    
    @Test
    public void hashingSuccess() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(0, 0);
        Position p3 = new Position(0, 1);
        
        Assertions.assertEquals(p1, p2);
        Assertions.assertTrue(p1.hashCode() == p2.hashCode());
        Assertions.assertTrue(p1.hashCode() != p3.hashCode());
    }
    
    @Test
    public void equalsSuccess() {
        Beam b1 = new Beam(3, 3);
        
        Beam b2 = new Beam(3, 2);
        b2.position.y = 3;
        
        Beam b3 = new Beam(3, 3);
        
        Assertions.assertEquals(b1, b2);
        Assertions.assertTrue(b1.hashCode() == b2.hashCode());
        Assertions.assertTrue(b1.hashCode() == b3.hashCode());
    }
    
    @Test
    public void mapIsProper() {
        Position emitter = new Position(7, 0);
        boolean isEmitter = map.isEmitter(emitter);
        Assertions.assertTrue(isEmitter);
    }
    
    @Test
    public void beamSplits() {
        Position position = new Position(7, 2);
        Beam beam = new Beam(position);
        boolean canSplit = map.isSplitter(beam.position);
        Assertions.assertTrue(canSplit);
    }
    
    @Test
    public void testDataSuccess() {
        engine.start();
        while (engine.isRunning()) {
            engine.run();
        }
        long result = engine.getSplits();
        long expected = 21;
        Assertions.assertEquals(expected, result);
    }

}
