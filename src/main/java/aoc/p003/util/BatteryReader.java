package aoc.p003.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BatteryReader {
    public String[] banks;

    public BatteryReader(String filename) {
        ClassLoader loader = BatteryReader.class.getClassLoader();
        InputStreamReader is = new InputStreamReader(loader.getResourceAsStream(filename));
        try (BufferedReader reader = new BufferedReader(is)) {
            banks = reader.lines().toArray(String[]::new);
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
