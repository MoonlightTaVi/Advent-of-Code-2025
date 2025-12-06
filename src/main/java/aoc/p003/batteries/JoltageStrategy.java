package aoc.p003.batteries;


/**
 * A strategy to get the highest joltage in the battery bank.
 */
public interface JoltageStrategy {

    /**
     * Returns the largest joltage of the battery bank.
     */
    public long getLargestJoltage(String batteryBank);
    
}
