package aoc.p005;

import aoc.Validation;

/**
 * https://adventofcode.com/2025/day/5 <br> <br>
 * There are various ingredients with Long IDs. <br>
 * There are several ranges that indicate fresh (non-spoiled) 
 * ingredients. <br>
 * The task is to count all fresh ingredients.
 */
public class Main {
    static final long expected = 896;
    static final long expectedAll = 346240317247002L;

    public static void main(String... args) {
        FreshCounter counter = new FreshCounter();
        Ingredients ingredients = new Ingredients("005.txt");
        ingredients.pack();
        long count = counter.count(ingredients);
        long countAll = counter.countAll(ingredients);
        System.out.printf("Number of fresh ingredients: %d%n", count);
        
        Validation.validate(expected, count);
        Validation.validate(expectedAll, countAll);
    }
    
}
