package aoc25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p008.*;

public class CircuitTest {
    static final double threshold = 0.0001;
    
    public static boolean almostEqual(double x, double y) {
        return Math.abs(x - y) <= threshold;
    }
    
    static Boxes boxes;
    
    @BeforeAll
    public static void setup() {
        boxes = new Boxes();
        boxes.findClosest(10);
    }

    @Test
    public void someTest() {
        boxes.print();
        boxes.connect(20);
        Arrays.sort(boxes.boxes, (x, y) -> Integer.compare(x.circuitId, y.circuitId));
        boxes.print();
        long sum = boxes.summarize(3);
        Assertions.assertEquals(40, sum);
    }
    
    @Test
    public void distanceTest() {
        Box b1 = new Box("162,817,812");
        Box b2 = new Box("425,690,689");
        Box b3 = new Box("431,825,988");
        
        Assertions.assertTrue(almostEqual(316.9021931133, b1.dst(b2)));
        Assertions.assertTrue(almostEqual(321.5602587385, b1.dst(b3)));
        Assertions.assertTrue(almostEqual(328.1188808953, b2.dst(b3)));
        
    }
    
}
