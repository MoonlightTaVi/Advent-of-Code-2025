package aoc.p007;

import aoc.p007.beams.AtomicBeams;
import aoc.p007.map.*;


/**
 * https://adventofcode.com/2025/day/7
 */
public class Main {

    public static void main(String... args) {
        Map map = new Map(new MapReader("007.txt").map);
        Engine engine = new Engine(map);
        
        AtomicBeams beams = new AtomicBeams();
        
        engine.start(beams);
        
        while (!beams.isEmpty()) {
            engine.process(beams);
            beams.spawn();
        }
        
        System.out.printf(
                "Total number of splits is: %d%n", 
                engine.getSplits()
                );
    }
    
}
