package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aoc.p001.*;

public class SafeTest {
    
    Safe safe;
    
    
    /**
     * Reset the state of the safe.
     */
    @BeforeEach
    public void reset() {
        safe = new Safe();
    }
    
    /**
     * Parser test.
     */
    @Test
    public void basicRotation() {
        safe.rotate("R8");
        int right = safe.position;
        safe.rotate("L7");
        int left = safe.position;
        Assertions.assertEquals(58, right);
        Assertions.assertEquals(51, left);
    }
    
    /**
     * The circular handle of the safe loops itself correctly.
     */
    @Test
    public void loopedRotation() {
        safe.rotate("L45");
        int left1 = safe.position;
        safe.rotate("L10");
        int left2 = safe.position;
        safe.rotate("R5");
        int right = safe.position;
        Assertions.assertEquals(5, left1);
        Assertions.assertEquals(95, left2);
        Assertions.assertEquals(0, right);
    }
    
    /**
     * "Passed" zeroes and "pointed at" zeroes do not overlap.
     */
    @Test
    public void countAllValid() {
        String[] rotations = {"L51", "R1", "R1", "L3"};
        safe.process(rotations);
        Assertions.assertEquals(1, safe.pointedZeroes);
        Assertions.assertEquals(2, safe.passedZeroes);
        Assertions.assertEquals(3, safe.allZeroes);
        Assertions.assertEquals(98, safe.position);
    }
    
    /**
     * Rotating by 1000 takes 10 full handle loops.
     */
    @Test
    public void longRotationValid() {
        safe.rotate("R1000");;
        Assertions.assertEquals(0, safe.pointedZeroes);
        Assertions.assertEquals(10, safe.passedZeroes);
        Assertions.assertEquals(50, safe.position);
    }
    
    /**
     * When starting from 0, no zeroes are passed in a single loop.
     */
    @Test
    public void startedAtZeroValid() {
        safe.rotate("L50");
        safe.rotate("L100");
        Assertions.assertEquals(2, safe.pointedZeroes);
        Assertions.assertEquals(0, safe.passedZeroes);
        Assertions.assertEquals(0, safe.position);
    }
    
    /**
     * Test case from file with known data.
     */
    @Test
    public void testFromFile() {
        String[] rotations = new PuzzleInput("001.txt").rotations;
        safe.process(rotations);
        Assertions.assertEquals(3, safe.pointedZeroes);
        Assertions.assertEquals(3, safe.passedZeroes);
        Assertions.assertEquals(6, safe.allZeroes);
        Assertions.assertEquals(32, safe.position);
        
    }
    
    /**
     * When starting from 0, no zeroes are passed in the first loop,
     * but they are taken into account on all the others loops (left).
     */
    @Test
    public void passingZeroLoopLeft() {
        safe.position = 78;
        safe.process(new String[] {"L478"});
        Assertions.assertEquals(1, safe.pointedZeroes);
        Assertions.assertEquals(4, safe.passedZeroes);
        Assertions.assertEquals(5, safe.allZeroes);
    }
    
    /**
     * When starting from 0, no zeroes are passed in the first loop,
     * but they are taken into account on all the others loops (right).
     */
    @Test
    public void passingZeroLoopRight() {
        safe.position = 22;
        safe.process(new String[] {"R478"});
        Assertions.assertEquals(1, safe.pointedZeroes);
        Assertions.assertEquals(4, safe.passedZeroes);
        Assertions.assertEquals(5, safe.allZeroes);
    }
    
}
