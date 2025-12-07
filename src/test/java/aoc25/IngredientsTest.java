package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p005.FreshCounter;
import aoc.p005.Ingredients;


public class IngredientsTest {
    static FreshCounter counter;
    static Ingredients ingredients;

    @BeforeAll
    public static void setup() {
        counter = new FreshCounter();
        ingredients = new Ingredients("005.txt");
        ingredients.pack();
    }
    
    @Test
    public void testDataSuccessful() {
        long expected = 3;
        long result = counter.count(ingredients);
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    public void mergeSuccsess() {
        long expected = 2;
        long result = ingredients.ranges.ranges.size();
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    public void totalSumValid() {
        long expected = 14;
        long result = counter.countAll(ingredients);
        Assertions.assertEquals(expected, result);
    }
    
}
