package aoc.p003.util;

import java.util.function.Function;


public class JoltageSum {
    
    public long getSum(String[] batteryBanks, Function<String, Long> joltageSupplier) {
        long sum = 0;
        for (String bank : batteryBanks) {
            long joltage = joltageSupplier.apply(bank);
            sum += joltage;
        }
        return sum;
    }
}
