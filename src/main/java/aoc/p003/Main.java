package aoc.p003;

import aoc.p003.batteries.*;
import aoc.p003.util.*;


public class Main {

    public static void main(String... args) {
        String[] banks = new BatteryReader("003.txt").banks;
        long sum = new JoltageSum().getSum(banks, bank -> new JoltageOfTwelve(bank).getLargestJoltage());
        System.out.println(sum);
    }
    
}
