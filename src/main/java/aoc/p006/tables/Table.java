package aoc.p006.tables;

import java.util.stream.Stream;

import aoc.FileReader;

public abstract class Table extends FileReader {
    final String[][] table;

    public Table(String filename) {
        table = prepare(super.read(filename).stream());
    }
    
    public abstract String[] getColumn(int columnIndex);
    
    public abstract int getColumns();
    
    public abstract String[][] prepare(Stream<String> lines);
    
}
