package aoc.p002.ranges;

import java.util.Arrays;

import aoc.FileReader;


/**
 * Collects ranges of IDs (as Strings) from a file. <br>
 * An ID is a Long number that is stored as a String. <br>
 * IDs must be separated by a comma and may be on separate lines. <br>
 * Each ID must contain digits only, and must not start with 0. <br>
 * A range of IDs is two IDs separated by '-'.
 */
public class IdParser extends FileReader {
    public String[] idRanges;

    public IdParser(String filename) {
        idRanges = super.read(filename)
                .stream()
                .flatMap(line -> Arrays.stream( line.split(",") ))
                .filter(range -> !range.isBlank())
                .filter(range -> range.charAt(0) != '0')
                .toArray(String[]::new);
    }
    
}
