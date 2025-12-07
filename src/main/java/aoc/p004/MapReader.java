package aoc.p004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Reads a map from a file.
 * @see #map
 */
public class MapReader {
    public char[][] map;
    
    public MapReader(String filename) {
        ClassLoader loader = MapReader.class.getClassLoader();
        InputStreamReader is = new InputStreamReader(
                loader.getResourceAsStream(filename)
                );
        try (BufferedReader reader = new BufferedReader(is)) {
            map = reader.lines()
                    .map(line -> line.toCharArray())
                    .toArray(char[][]::new);
            
        } catch (IOException e) {
            System.err.printf(
                    "IOException while reading %s: %s%n", 
                    filename, 
                    e.getLocalizedMessage()
                    );
            e.printStackTrace();
        }
    }
}
