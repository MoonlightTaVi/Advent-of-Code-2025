package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p009.TileReader;
import aoc.p009.edges.Edges;
import aoc.p009.squares.SquareProcessor;


/**
 * JUnit tests for Day 9.
 */
public class TilesTest {
    static TileReader reader;
    
    @BeforeAll
    public static void setup() {
        reader = new TileReader();
    }
    
    
    @Test
    public void squareTest() {
        SquareProcessor squares = new SquareProcessor();
        
        long expected = 50;
        long result = squares.getLargest(reader);
        
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    public void redGreenTest() {
        SquareProcessor squares = new SquareProcessor();
        Edges polygon = new Edges(reader);
        
        long expected = 24;
        long result = squares.getRedGreen(polygon);
        
        Assertions.assertEquals(expected, result);
    }
}
