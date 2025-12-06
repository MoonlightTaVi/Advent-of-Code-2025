package aoc25;

import aoc.p003.batteries.*;
import aoc.p003.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class BatteryTest {
    static String[] banks;
    
    
    @BeforeAll
    public static void setup() {
        banks = new BatteryReader("003.txt").banks;
    }
    
    
    JoltageOfTwo twoStrategy = new JoltageOfTwo();
    JoltageOfTwelve twelveStrategy = new JoltageOfTwelve();
    

    @Test
    public void testDataSuccessful() {
        long sum = new JoltageSum()
                .getSum(
                        banks, 
                        twoStrategy
                        );
        Assertions.assertEquals(357, sum);
    }
    
    @Test
    public void leftmostIndexPrioritized() {
        // Should prioritize left 9 first
        long joltage = twoStrategy.getLargestJoltage("464449544549");
        Assertions.assertEquals(99, joltage);
    }
    
    @Test
    public void joltageCalculated() {
        // Get joltage from digits of a number
        long result = twelveStrategy.getJoltage('1', '2', '3', '4');
        int expected = 1234;
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    public void joltageOfTwelve() {
        String bank = "234234234234278";
        long expected = 434234234278L;
        long joltage = twelveStrategy.getLargestJoltage(bank);
        Assertions.assertEquals(expected, joltage);
    }
    
    @Test
    public void testDataOfTwelve() {
        long expected = 3121910778619L;
        long sum = new JoltageSum()
                .getSum(
                        banks, 
                        twelveStrategy
                        );
        Assertions.assertEquals(expected, sum);
    }
    
}
