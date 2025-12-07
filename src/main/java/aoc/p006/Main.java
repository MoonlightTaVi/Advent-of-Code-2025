package aoc.p006;

import aoc.p006.tables.*;


/**
 * https://adventofcode.com/2025/day/6 <br> <br>
 */
public class Main {
    
    //11052310600986
    public static void main(String... args) {
        Table table = new TrickyTable("006.txt");
        TableProcessor processor = new TableProcessor();
        long result = processor.process(table);
        System.out.printf(
                "The result of the table processing is: %d%n", 
                result
                );
    }
    
}
