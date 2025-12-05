package aoc.p001;


/**
 * https://adventofcode.com/2025/day/1 <br> <br>
 * The safe contains the password to the entrance. 
 * There is a combination of rotations to the safe. <br>
 * The real password is not inside the safe, 
 * but it is the number of times when the dial of the
 * safe hits / hits or passes by 0 position.
 * @see Safe
 */
public class SecretEntrance {

    public static void main(String... args) {
        String[] input = new PuzzleInput("001.txt").rotations;
        Safe safe = new Safe();
        safe.process(input);
        System.out.println(safe.pointedZeroes);
        System.out.println(safe.allZeroes);
    }
    
}
