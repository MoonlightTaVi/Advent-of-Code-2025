package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p006.TableProcessor;
import aoc.p006.tables.PlainTable;
import aoc.p006.tables.TrickyTable;


/**
 * Day 6 JUnit tests.
 */
public class TableTest {
    
    static PlainTable table;
    static TrickyTable trickyTable;
    static TableProcessor processor;
    
    @BeforeAll
    public static void setup() {
        table = new PlainTable("006.txt");
        trickyTable = new TrickyTable("006.txt");
        processor = new TableProcessor();
    }
    

    @Test
    public void columnsAreCorrect() {
        String[] expected = {"123", "45", "6", "*"};
        String[] result = table.getColumn(0);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void trickyColumnsAreCorrect() {
        String[] expected = {"356", "24", "1", "*"};
        String[] result = trickyTable.getColumn(3);
        Assertions.assertArrayEquals(expected, result);
    }
    
    @Test
    public void testDataProccessed() {
        long expected = 4277556;
        long result = processor.process(table);
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    public void trickyDataProccessed() {
        long expected = 3263827;
        long result = processor.process(trickyTable);
        Assertions.assertEquals(expected, result);
    }
    
}
