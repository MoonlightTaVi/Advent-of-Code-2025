package aoc.p007;

import aoc.FileReader;


public class Map extends FileReader {
    public final char[][] array;
    public final int height;
    public final int width;
    
    
    public Map(String filename) {
        array = super.read(filename)
                .stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
        height = array.length;
        width = array[0].length;
    }
    
}
