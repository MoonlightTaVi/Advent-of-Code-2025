package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aoc.p004.*;
import aoc.p004.forklifts.BasicForklift;
import aoc.p004.forklifts.ContinuousForklift;


/**
 * JUnit test for day 4.
 */
public class ForkliftTest {
    static BasicForklift forklift;
    static ContinuousForklift contForklift;
    
    @BeforeAll
    public static void setup() {
        forklift = new BasicForklift();
        contForklift = new ContinuousForklift();
    }
    
    
    char[][] map;
    
    @BeforeEach
    public void reset() {
        map = new MapReader("004.txt").map;
        forklift.setMap(map);
        contForklift.setMap(map);
    }
    
    
    @Test
    public void isAccessible() {
        // x and y are reversed
        boolean isAccessible = forklift.canAccess(0, 2);
        Assertions.assertTrue(isAccessible);
    }
    
    @Test
    public void isNotAccessible() {
        boolean isNotAccessible = !forklift.canAccess(1, 1);
        Assertions.assertTrue(isNotAccessible);
    }
    
    @Test
    public void testDataSuccess() {
        int expected = 13;
        int result = forklift.countAccessible();
        
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    public void testDataContinuousSuccess() {
        int expected = 43;
        int result = contForklift.countAccessible();
        
        Assertions.assertEquals(expected, result);
    }
}
