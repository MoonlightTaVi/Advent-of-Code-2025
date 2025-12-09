package aoc25;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p008.*;
import aoc.p008.sorting.CircuitComparator;


public class CircuitTest {
    static NodeProcessor boxes;
    
    static final double threshold = 0.0001;

    
    @BeforeAll
    public static void setup() {
        boxes = new NodeProcessor();
        boxes.findClosest(10);
    }
    
    public static boolean almostEqual(double x, double y) {
        return Math.abs(x - y) <= threshold;
    }

    
    @Test
    public void someTest() {
        boxes.connect();
        Arrays.sort(boxes.boxes, new CircuitComparator());
        boxes.print();
        long sum = boxes.summarize(3);
        Assertions.assertEquals(40, sum);
    }
    
    @Test
    public void distanceTest() {
        Node b1 = new Node("162,817,812");
        Node b2 = new Node("425,690,689");
        Node b3 = new Node("431,825,988");
        
        Assertions.assertTrue(almostEqual(316.9021931133, b1.dst(b2)));
        Assertions.assertTrue(almostEqual(321.5602587385, b1.dst(b3)));
        Assertions.assertTrue(almostEqual(328.1188808953, b2.dst(b3)));
        
    }
    
}
