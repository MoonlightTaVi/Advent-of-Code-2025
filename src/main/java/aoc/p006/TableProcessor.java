package aoc.p006;

import java.util.function.BiFunction;

public class TableProcessor {
    BiFunction<Long, Long, Long> mult = (x, y) -> x * y;
    BiFunction<Long, Long, Long> sum = (x, y) -> x + y;

    public long process(TableReader table) {
        long total = 0;
        for (int i = 0; i < table.columns; i++) {
            String[] column = table.getColumn(i);
            total += processColumn(column);
        }
        return total;
    }
    
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
