package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p006.TableProcessor;
import aoc.p006.TableReader;


public class TableTest {
    
    static TableReader table;
    static TableProcessor processor;
    
    @BeforeAll
    public static void setup() {
        table = new TableReader("006.txt");
        processor = new TableProcessor();
    }
    

    @Test
    public void columnsAreCorrect() {
        String[] expected = {"123", "45", "6", "*"};
        String[] result = table.getColumn(0);
        Assertions.assertArrayEquals(expected, result);
    }
    
    @Test
    public void testDataProccessed() {
        long expected = 4277556;
        long result = processor.process(table);
        Assertions.assertEquals(expected, result);
    }
    
}
