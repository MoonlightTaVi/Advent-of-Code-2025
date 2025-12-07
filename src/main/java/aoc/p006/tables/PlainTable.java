package aoc.p006.tables;

import java.util.stream.Stream;


/**
 * A simple table.
 * @see Table
 */
public class PlainTable extends Table {
    public int rows;
    public int columns;
    
    public PlainTable(String filename) {
        super(filename);
        rows = super.table.length;
        columns = super.table[0].length;
    }

    @Override
    public String[][] prepare(Stream<String> lines) {
        return lines.map(line -> line.strip().split("\\s+"))
                .toArray(String[][]::new);
    }
    
    @Override
    public int getColumns() {
        return columns;
    }
    
    @Override
    public String[] getColumn(int columnIndex) {
        String[] column = new String[rows];
        for (int i = 0; i < rows; i++) {
            column[i] = super.table[i][columnIndex];
        }
        return column;
    }
    
}
