package aoc.p007;

import aoc.p007.map.*;


/**
 * https://adventofcode.com/2025/day/7
 */
public class Main {

    public static void main(String... args) {
        Map map = new Map(new MapReader("007.txt").map);
        Engine engine = new Engine(map);
        
        engine.start();
        while (engine.isRunning()) {
            engine.run();
        }
        
        System.out.printf(
                "Total number of splits is: %d%n", 
                engine.getSplits()
                );
    }
    
}
