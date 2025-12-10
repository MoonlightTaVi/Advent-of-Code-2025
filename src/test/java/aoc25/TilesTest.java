package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p009.TileReader;
import aoc.p009.squares.SquareProcessor;


/**
 * JUnit tests for Day 9.
 */
public class TilesTest {
    static TileReader tiles;
    
    @BeforeAll
    public static void setup() {
        tiles = new TileReader();
    }
    
    
    @Test
    public void squareTest() {
        SquareProcessor squares = new SquareProcessor(tiles.sortedByX());
        
        long expected = 50;
        long result = squares.getLargest();
        
        Assertions.assertEquals(expected, result);
    }
}
