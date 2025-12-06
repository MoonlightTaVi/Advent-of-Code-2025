package aoc.p002.ranges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * Collects ranges of IDs (as Strings) from a file. <br>
 * An ID is a Long number that is stored as a String. <br>
 * IDs must be separated by a comma and may be on separate lines. <br>
 * Each ID must contain digits only, and must not start with 0. <br>
 * A range of IDs is two IDs separated by '-'.
 */
public class IdParser {
    public String[] idRanges;

    public IdParser(String filename) {
        ClassLoader loader = IdParser.class.getClassLoader();
        InputStreamReader is = new InputStreamReader(
                loader.getResourceAsStream(filename)
                );
        try (BufferedReader reader = new BufferedReader(is)) {
            idRanges = reader.lines()
                    .flatMap(line -> Arrays.stream( line.split(",") ))
                    .filter(range -> !range.isBlank())
                    .filter(range -> range.charAt(0) != '0')
                    .toArray(String[]::new);
            
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
