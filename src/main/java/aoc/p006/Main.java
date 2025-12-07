package aoc.p006;


public class Main {

    public static void main(String... args) {
        TableReader table = new TableReader("006.txt");
        TableProcessor processor = new TableProcessor();
        long result = processor.process(table);
        System.out.printf(
                "The result of the table processing is: %d%n", 
                result
                );
    }
    
}
