package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p009.TileProcessor;
import aoc.p009.TileReader;


/**
 * JUnit tests for Day 9.
 */
public class TilesTest {
    static TileReader reader;
    static TileProcessor processor;
    
    @BeforeAll
    public static void setup() {
        reader = new TileReader();
        processor = new TileProcessor(reader);
        processor.process();
    }
    
    
    @Test
    public void squareTest() {
        
        long expected = 50;
        long result = processor.areaLargest();
        
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    public void redGreenTest() {
        
        long expected = 24;
        long result = processor.areaLimited();
        
        Assertions.assertEquals(expected, result);
    }
}
