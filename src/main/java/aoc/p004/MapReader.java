package aoc.p004;

import aoc.FileReader;


/**
 * Reads a map from a file.
 * @see #map
 */
public class MapReader extends FileReader {
    public char[][] map;
    
    /**
     * Reads  the map from a file.
     */
    public MapReader(String filename) {
        map = super.read(filename)
                .stream()
                .map(line -> line.toCharArray())
                .toArray(char[][]::new);
    }
    
}
