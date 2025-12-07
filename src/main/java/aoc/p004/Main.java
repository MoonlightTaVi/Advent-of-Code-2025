package aoc.p004;

import aoc.p004.forklifts.ContinuousForklift;
import aoc.p004.forklifts.ForkliftIF;


/**
 * https://adventofcode.com/2025/day/4 <br> <br>
 * There's a map with rolling papers ('@' symbol) scattered around. <br>
 * A roll of paper is accessible by a forklift if it is surrounded by
 * less than 4 other rolls. <br>
 * ContinuousForklift can pick up any accessible rolls of paper to free
 * up the space on the map. <br>
 * It is needed to count all rolls that may be picked up.
 */
public class Main {

    public static void main(String... args) {
        char[][] map = new MapReader("004.txt").map;
        ForkliftIF forklift = new ContinuousForklift();
        forklift.setMap(map);
        
        int count = forklift.countAccessible();
        System.out.printf(
                "Accessible rolls of paper count is: %d%n", 
                count
                );
    }
    
}
