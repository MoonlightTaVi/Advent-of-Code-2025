package aoc.p009;

import aoc.Validation;


/**
 * Day 9.
 */
public class Main {
    static long squareP1 = 4764078684L;
    
    // 114894852 too low.. 150497383 ? too low
    public static void main(String[] args) {
        
        long resultP1 = 0;
        
        Validation.print("The largest square", resultP1);
        Validation.validate(squareP1, resultP1);
        
        long resultP2 = 0;
        
        Validation.print("The largest square", resultP2);
        Validation.validate(squareP1, resultP1);
    }
    
}
