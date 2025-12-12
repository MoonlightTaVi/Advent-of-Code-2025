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
        processor.processCorners();
        processor.processAll();
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
        long result = processor.areaInside();
        
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    public void overlapTest() {
        Rect r1 = new Rect(new LongVector2(0,0), new LongVector2(5,5));
        Rect r2 = new Rect(new LongVector2(5,0), new LongVector2(10,5));
        
        boolean result = !r1.overlaps(r2);
        Assertions.assertTrue(result);
    }
    
    @Test
    public void signedAreaTest() {
        LongVector2 a = new LongVector2(11, 1);
        LongVector2 b = new LongVector2(11, 7);
        LongVector2 c = new LongVector2(9, 7);
        LongVector2 d = new LongVector2(9, 5);
        
        Rect r1 = new Rect(a, b, c);
        Rect r2 = new Rect(b, c, d);
        
        Rect r3 = new Rect(d, c, b);
        
        boolean firstPositive = !r1.isClockwise();
        boolean secondPositive = !r2.isClockwise();
        
        boolean thirdNegative = r3.isClockwise();
        
        Assertions.assertEquals(firstPositive, secondPositive);
        
        Assertions.assertTrue(firstPositive);
        Assertions.assertTrue(secondPositive);
        Assertions.assertTrue(thirdNegative);
        
        Assertions.assertEquals(12, r1.cross());
        Assertions.assertEquals(4, r2.cross());
    }
}
