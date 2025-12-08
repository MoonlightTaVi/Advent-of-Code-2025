package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p007.*;


/**
 * JUnit test for day 7.
 */
public class BeamsTest {
    static Map map;
    static Beams beams;
    
    @BeforeAll
    public static void setup() {
        map = new Map("007.txt");
        beams = new Beams();
    }
    
    @Test
    public void halfIsValid() {
        int center = beams.getStart(map);
        char start = map.array[0][center];
        Assertions.assertEquals('S', start);
    }
    
    @Test
    public void testDataIsValid() {
        beams.traverse(map);
        long splits = beams.getTotalSplits();
        long beamCount = beams.getTotalBeams();
        
        Assertions.assertEquals(21, splits);
        Assertions.assertEquals(40, beamCount);
    }
    
}
