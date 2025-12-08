package aoc.p007;


public class Main {

    public static void main(String... args) {
        Beams beams = new Beams();
        char[][] map = new Map("007.txt").array;
        
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
