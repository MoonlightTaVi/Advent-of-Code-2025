package aoc.p007;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import aoc.p007.map.*;


/**
 * https://adventofcode.com/2025/day/7
 */
public class Main {

    public static void main(String... args) {
        Map map = new Map(new MapReader("007.txt").map);
        Engine engine = new Engine(map);
        
        Set<Beam> beams = new HashSet<>();
        Collection<Beam> spawnStorage = new HashSet<>();
        
        engine.start(beams);
        
        while (!beams.isEmpty()) {
            beams = engine.run(beams, spawnStorage, Collectors.toSet());
            spawnStorage.clear();
        }
        
        System.out.printf(
                "Total number of splits is: %d%n", 
                engine.getSplits()
                );
    }
    
}
