package aoc.p003;

import aoc.p003.batteries.*;
import aoc.p003.util.*;


/**
 * https://adventofcode.com/2025/day/3 <br> <br>
 * A battery is a number from 1 to 9. <br>
 * A battery bank is a String of numbers that represent batteries. <br>
 * A joltage of a bank is a number that contains only turned on batteries
 * in the bank (turned off batteries are dismissed from the String). <br>
 * The highest joltage of a bank is the combination of turned on
 * batteries that has the largest numeric value (as Long). <br>
 * The answer to the task is the sum of the highest joltages in the
 * list of battery banks.
 * @see JoltageOfTwo
 * @see JoltageOfTwelve
 */
public class Main {

    public static void main(String... args) {
        JoltageStrategy strategy = new JoltageOfTwelve();
        
        String[] banks = new BatteryReader("003.txt").banks;
        long sum = new JoltageSum().getSum(banks, strategy);
        System.out.printf(
                "The sum of joltages (using %s) is: %d", 
                strategy.getClass().getSimpleName(),
                sum
                );
    }
    
}
