package aoc.p003.util;

import aoc.p003.batteries.*;


/**
 * Supportive class that calculates the sum from an array of
 * battery banks (as String[]) using one of the strategies.
 * @see JoltageOfTwo
 * @see JoltageOfTwelve
 */
public class JoltageSum {
    
    /**
     * Get the sum of the highest joltages of the battery banks.
     * @param batteryBanks String array of battery banks.
     * @param strategy Strategy to be used to get the highest joltage.
     * @return Sum of the highest joltages.
     */
    public long getSum(String[] batteryBanks, JoltageStrategy strategy) {
        long sum = 0;
        for (String bank : batteryBanks) {
            long joltage = strategy.getLargestJoltage(bank);
            sum += joltage;
        }
        return sum;
    }
}
