package aoc.p007;


/**
 * https://adventofcode.com/2025/day/7 <br> <br>
 * A beam is emitted from the upper center of the map (marked 'S')
 * and moves downwards. <br>
 * When a beam hits a splitter (marked '^') it is split into two
 * beams to the right and to the left. <br>
 * The task is to count all the performed splits and the overall
 * number of beams that reached the bottom of the map.
 */
public class Main {

    public static void main(String... args) {
        Beams beams = new Beams();
        Map map = new Map("007.txt");
        
        beams.traverse(map);
        
        System.out.printf(
                "Number of beam splits (pt1) is: %d%n", 
                beams.getTotalSplits()
                );
        System.out.printf(
                "Number of all beams (pt2) is: %d%n", 
                beams.getTotalBeams()
                );
    }
    
}
