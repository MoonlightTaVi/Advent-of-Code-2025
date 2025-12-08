package aoc.p007.map;

import aoc.FileReader;


/**
 * Reads map from a file.
 */
public class MapReader extends FileReader {
    public final char[][] map;

    public MapReader(String filename) {
        map = super.read(filename)
                .stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }
    
}
