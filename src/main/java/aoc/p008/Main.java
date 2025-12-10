package aoc.p008;

import aoc.Validation;


public class Main {
    static int part1 = 63920;
    static int part2 = 1026594680;

    public static void main(String[] args) {
        MasterProcessor processor = new MasterProcessor();
        
        
        int product = processor.getProduct();
        
        Validation.print(
                "The sizes of the three largest circuits multiplied together", 
                product
                );
        Validation.validate(part1, product);
        
        
        int distance = processor.getDistance();
        
        Validation.print(
                "X coordinates of the last pair miltiplied", 
                distance
                );
        Validation.validate(part2, distance);
    }
    
}
