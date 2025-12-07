package aoc.p006;

import java.util.function.BiFunction;

import aoc.p006.tables.Table;


/**
 * Applies mathematical operations to a table.
 */
public class TableProcessor {
    /** Multiplication. */
    BiFunction<Long, Long, Long> mult = (x, y) -> x * y;
    /** Sum. */
    BiFunction<Long, Long, Long> sum = (x, y) -> x + y;

    
    /**
     * Processes the table and returns the grand total of 
     * the operations applied to each column as the sum of these results.
     * @param table Table to be processed.
     * @return Grand total of all of the operations.
     */
    public long process(Table table) {
        long total = 0;
        for (int i = 0; i < table.getColumns(); i++) {
            String[] column = table.getColumn(i);
            total += processColumn(column);
        }
        return total;
    }
    
    /**
     * Processes a single column.
     * @param column
     * @return Result of the operation.
     */
    public long processColumn(String[] column) {
        char operator = column[column.length - 1].charAt(0);
        BiFunction<Long, Long, Long> func = getFunction(operator);
        
        long total = Long.parseLong(column[0]);
        for (int i = 1; i < column.length - 1; i++) {
            long num = Long.parseLong(column[i]);
            total = func.apply(total, num);
        }
        return total;
    }
    
    /**
     * Decides which BiFunction to use for the operator.
     * @param operator '+' or '*'
     * @return BiFunction of this operator.
     */
    public BiFunction<Long, Long, Long> getFunction(char operator) {
        switch (operator) {
        case '*':
            return mult;
        case '+':
            return sum;
        default:
            return null;
        }
    }
    
}
