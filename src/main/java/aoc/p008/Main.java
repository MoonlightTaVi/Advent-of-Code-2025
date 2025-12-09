package aoc.p008;

import aoc.Validation;

public class Main {

    // 12600 is too low
    public static void main(String[] args) {
        NodeProcessor processor = new NodeProcessor();
        processor.findClosest(1000);
        processor.generateCircuits();
        
        long product = processor.calculateProduct(3);
        
        Validation.print(
                "Sizes of the three largest circuits multiplied", 
                product
                );
    }
}
