package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p005.FreshCounter;
import aoc.p005.FreshRange;
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
        FreshRange r1 = new FreshRange("243843849516096-246762825757879");
        FreshRange r2 = new FreshRange("245963236839800-249017821139790");
        boolean overlaps = r1.overlaps(r2);
        Assertions.assertTrue(overlaps);
    }
    
}
