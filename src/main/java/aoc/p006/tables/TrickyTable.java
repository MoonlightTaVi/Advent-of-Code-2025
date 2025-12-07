package aoc.p006.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TrickyTable extends Table {
    
    public TrickyTable(String filename) {
        super(filename);
    }
    
    @Override
    public String[][] prepare(Stream<String> lines) {
        char[][] chars = lines
                .map(String::toCharArray)
                .toArray(char[][]::new);
        return process(chars);
    }
    
    @Override
    public String[] getColumn(int columnIndex) {
        return table[columnIndex];
    }
    
    @Override
    public int getColumns() {
        return table.length;
    }
    
    public String[][] process(char[][] chars) {
        String[] operators = super.lines[super.lines.length - 1]
                .strip()
                .split("\\s+");
        int columns = operators.length;
        String[][] table = new String[columns][];
        List<String> column = new ArrayList<>();
        int j = columns - 1;
        for (int i = chars[0].length - 1; i >= 0; i--) {
            String line = readString(chars, i);
            if (!line.isBlank()) {
                column.add(line);
            }
            if (i == 0) {
                line = "";
                j = 0;
            }
            if (line.isBlank()) {
                column.add(operators[j]);
                table[columns - j - 1] = column.toArray(String[]::new);
                column.clear();
                j--;
            }
            
        }
        return table;
    }
    
    public String readString(char[][] chars, int columnIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length - 1; i++) {
            char ch = chars[i][columnIndex];
            if (Character.isDigit(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

}
