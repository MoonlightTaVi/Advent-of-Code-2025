package aoc25.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.shared.BinarySearch;


/**
 * Binary search and line ray-casting.
 */
public class BinaryTest {
    static BinarySearch<Integer, Integer> search;
    static Integer[] numbers = { 1, 2, 3, 5, 8, 13 };
    
    
    @BeforeAll
    public static void setup() {
        search = new BinarySearch<>(x -> x, y -> y);
    }
    
    
    @Test
    public void raycastLeft7() {
        int id = search.castLeft(7, numbers);
        
        int result = numbers[id];
        int expected = 5;
        
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    public void raycastLeft4() {
        int id = search.castLeft(4, numbers);
        
        int result = numbers[id];
        int expected = 3;
        
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    public void raycastRight8() {
        int id = search.castRight(8, numbers);
        
        int result = numbers[id];
        int expected = 13;
        
        Assertions.assertEquals(expected, result);
    }
}
