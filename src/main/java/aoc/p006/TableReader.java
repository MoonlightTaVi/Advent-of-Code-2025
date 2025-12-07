package aoc.p006;

import aoc.FileReader;

public class TableReader extends FileReader {
    public final int rows;
    public final int columns;
    final String[][] table;
    
    public TableReader(String filename) {
        table = super.read(filename)
                .stream()
                .map(line -> line.strip().split("\\s+"))
                .toArray(String[][]::new);
        rows = table.length;
        columns = table[0].length;
    }
    
    public String[] getColumn(int columnIndex) {
        String[] column = new String[rows];
        for (int i = 0; i < rows; i++) {
            column[i] = table[i][columnIndex];
        }
        return column;
    }
}
