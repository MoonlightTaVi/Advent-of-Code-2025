package aoc25;

import aoc.p003.batteries.*;
import aoc.p003.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class BatteryTest {

    @Test
    public void testDataSuccessful() {
        String[] banks = new BatteryReader("003.txt").banks;
        long sum = new JoltageSum()
                .getSum(
                        banks, 
                        bank -> new JoltageOfTwo(bank)
                            .getLargestJoltage()
                        );
        Assertions.assertEquals(357, sum);
    }
    
    @Test
    public void leftmostIndexPrioritized() {
        long joltage = new JoltageOfTwo("464449544549").getLargestJoltage();
        Assertions.assertEquals(99, joltage);
    }
    
    @Test
    public void joltageCalculated() {
        JoltageOfTwelve joltage = new JoltageOfTwelve("");
        long result = joltage.getJoltage('1', '2', '3', '4');
        int expected = 1234;
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    public void joltageOfTwelve() {
        String bank = "234234234234278";
        long joltage = new JoltageOfTwelve(bank).getLargestJoltage();
        Assertions.assertEquals(434234234278L, joltage);
    }
    
    @Test
    public void testDataOfTwelve() {
        String[] banks = new BatteryReader("003.txt").banks;
        long sum = new JoltageSum()
                .getSum(
                        banks, 
                        bank -> new JoltageOfTwelve(bank)
                            .getLargestJoltage()
                        );
        Assertions.assertEquals(3121910778619L, sum);
    }
    
}
