package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p009.Rect;
import aoc.p009.TileProcessor;
import aoc.p009.TileReader;
import aoc.shared.LongVector2;


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
    
    @Test
    public void overlapTest() {
        Rect r1 = new Rect(new LongVector2(0,0), new LongVector2(5,5));
        Rect r2 = new Rect(new LongVector2(5,0), new LongVector2(10,5));
        
        boolean result = !r1.overlaps(r2);
        Assertions.assertTrue(result);
    }
}
