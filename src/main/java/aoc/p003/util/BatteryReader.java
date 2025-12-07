package aoc.p003.util;

import aoc.FileReader;


/**
 * Supportive class that reads battery banks from a file.
 */
public class BatteryReader extends FileReader {
    public String[] banks;

    public BatteryReader(String filename) {
        banks = super.read(filename).lines;
    }
    
}
