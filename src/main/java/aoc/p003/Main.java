package aoc.p003;

import aoc.p003.batteries.*;
import aoc.p003.util.*;


public class Main {

    public static void main(String... args) {
        JoltageStrategy strategy = new JoltageOfTwelve();
        
        String[] banks = new BatteryReader("003.txt").banks;
        long sum = new JoltageSum().getSum(banks, strategy);
        System.out.println(sum);
    }
    
}
