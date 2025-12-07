package aoc.p004;


public class Main {

    public static void main(String... args) {
        char[][] map = new MapReader("004.txt").map;
        ForkliftIF forklift = new BasicForklift(map);
        int count = forklift.countAccessible();
        System.out.printf(
                "Accessible rolls of paper count is: %d%n", 
                count
                );
    }
    
}
