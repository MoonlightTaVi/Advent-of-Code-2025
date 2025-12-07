package aoc.p006.tables;

import java.util.stream.Stream;

import aoc.FileReader;


/**
 * A table consists of several columns (their lengths may differ).
 * Each column consists of numbers, and the last item in the column
 * is either '+' or '*' operator.
 */
public abstract class Table extends FileReader {
    final String[][] table;

    /**
     * Read table from file.
     * @param filename
     */
    public Table(String filename) {
        table = prepare(super.read(filename).stream());
    }
    
    /**
     * Get a column from the table by ID.
     * @param columnIndex ID of the column;
     * @return The column.
     */
    public abstract String[] getColumn(int columnIndex);
    
    /**
     * Returns the number of columns.
     * @return
     */
    public abstract int getColumns();
    
    /**
     * Transforms Stream of lines from a file into the specified
     * implementation of the table (the latter may differ based
     * on the class).
     * @param lines Stream of lines from a file.
     * @return The table from this lines.
     */
    public abstract String[][] prepare(Stream<String> lines);
    
}
