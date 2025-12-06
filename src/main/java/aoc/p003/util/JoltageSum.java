package aoc.p003.util;

import aoc.p003.batteries.JoltageStrategy;


public class JoltageSum {
    
    public long getSum(String[] batteryBanks, JoltageStrategy strategy) {
        long sum = 0;
        for (String bank : batteryBanks) {
            long joltage = strategy.getLargestJoltage(bank);
            sum += joltage;
        }
        return sum;
    }
}
