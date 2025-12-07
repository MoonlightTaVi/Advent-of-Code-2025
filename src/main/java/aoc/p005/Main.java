package aoc.p005;


public class Main {
    static final long expected = 896;

    public static void main(String... args) {
        FreshCounter counter = new FreshCounter();
        Ingredients ingredients = new Ingredients("005.txt");
        ingredients.pack();
        long count = counter.countAll(ingredients);
        System.out.printf("Number of fresh ingredients: %d%n", count);
    }
    
}
