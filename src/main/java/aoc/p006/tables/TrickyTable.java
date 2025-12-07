package aoc.p006.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


/**
 * Just don't ask me.
 */
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
        // Length of a column may vary (compared to PlainTable)
        return table[columnIndex];
    }
    
    @Override
    public int getColumns() {
        // The table is flipped (compared to PlainTable)
        return table.length;
    }
    
    /**
     * Make a String[][] table from char[][] table.
     * @param chars char[][] contents of a file.
     * @return The resulting table.
     */
    private String[][] process(char[][] chars) {
        String[] operators = super.lines[super.lines.length - 1]
                .strip()
                .split("\\s+");
        int columns = operators.length;
        // As many operators, that many columns
        String[][] table = new String[columns][];
        List<String> column = new ArrayList<>();
        int j = columns - 1;
        // Start reading from the rightmost part
        for (int i = chars[0].length - 1; i >= 0; i--) {
            String line = readString(chars, i);
            // Only digits may be included in a line
            // Blank line = next column of numbers
            if (!line.isBlank()) {
                column.add(line);
            }
            if (i == 0) { // End of reading
                line = "";
                j = 0;
            }
            if (line.isBlank()) { // Add a column
                column.add(operators[j]);
                // The left of the table is the right of the file
                table[columns - j - 1] = column.toArray(String[]::new);
                column.clear();
                j--;
            }
            
        }
        return table;
    }
    
    /**
     * Attempts to read a column of characters from a char[][] table. <br>
     * Only numbers will be included, so the result may be empty.
     * @param chars char[][] table representation of a file.
     * @param columnIndex Horizontal index of a char[] column.
     * @return String value from this char[] column that either 
     * contains a number or is blank.
     */
    private String readString(char[][] chars, int columnIndex) {
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
