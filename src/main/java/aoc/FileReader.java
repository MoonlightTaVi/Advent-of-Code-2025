package aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

import aoc.p004.MapReader;


/**
 * Base class that reads lines from a file and returns a stream of
 * them.
 */
public class FileReader {
    public String[] lines = null;

    /**
     * Reads lines from a file.
     * @param filename Name of the file inside the resources directory.
     * @return Itself.
     * @see #lines
     * @see #stream()
     */
    protected final FileReader read(String filename) {
        ClassLoader loader = MapReader.class.getClassLoader();
        InputStreamReader is = new InputStreamReader(
                loader.getResourceAsStream(filename)
                );
        
        try (BufferedReader reader = new BufferedReader(is)) {
            lines = reader.lines().toArray(String[]::new);
            
        } catch (IOException e) {
            System.err.printf(
                    "[%s] IOException while reading %s: %s%n", 
                    this.getClass().getName(),
                    filename, 
                    e.getLocalizedMessage()
                    );
            e.printStackTrace();
        }
        
        return this;
    }
    
    public final Stream<String> stream() {
        return Arrays.stream(lines);
    }
    
}
