package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p004.*;


public class ForkliftTest {
    static char[][] map;
    static BasicForklift forklift;
    
    
    @BeforeAll
    public static void setup() {
        map = new MapReader("004.txt").map;
        forklift = new BasicForklift(map);
    }
    
    @Test
    public void isAccessible() {
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
}
