package aoc.p010.matrix;

import aoc.p010.factory.StartedMachine;

public class Matrix {
    /** Number of columns. */
    public final int m;
    /** Number of rows. */
    public final int n;

    public final int[][] table;
    
    
    public Matrix(int[][] matrix) {
        table = matrix;
        n = table.length;
        m = table[0].length;
    }
    
    public Matrix(StartedMachine machine) {
        // Columns (for each button)
        // Add 1 column for the required joltage of the row
        m = machine.buttons.length + 1;
        // Rows (for each joltage)
        n = machine.requiredJoltage.length;
        
        table = new int[n][];
        for (int i = 0; i < n; i++) {
            table[i] = new int[m];
            table[i][m - 1] = (int) machine.requiredJoltage[i];
        }
        
        for (int i = 0; i < m - 1; i++) {
            // The machine button arrays refer to joltage IDs
            for (int id : machine.buttons[i]) {
                table[id][i] = 1;
            }
        }
    }
    
    
    public void matrixToREF() {
        int size = Math.min(n, m);
        
        print();
        
        for (int i = 0; i < size; i++) {
            // Set the first element of the first row to != 0 (swap)
            // Skip if not possible
            if (!swapRows(i)) {
                continue;
            }
            
            // Make all other rows have 0 at the i column
            applyReduction(i);
            
            print();
        }
    }
    
    public void matrixToRREF() {
        for (int row = n - 1; row > 0; row--) {
            int column = findLeadingColumn(row);
            // Check if the row is already at final form
            if (checkRREF(row, column)) {
                continue;
            }
            
            // Set the lead row to 1 by changing all of the row values
            int lead = table[row][column];
            // Division by 1 means nothing, skip
            if (lead != 1) {
                for (int j = lead; j < m; j++) {
                    if (table[row][j] % lead != 0) {
                        throw matrixIncompatible(
                                "Not dealing with integers (at final form)"
                                );
                    }
                    table[row][j] /= lead;
                }
            }
            
            /* Set the element above this lead element to 0
                by changing all of the values of the row above
                (subtract this row (multiplied by ratio) 
                 from the row above)
             */
            int ratio = table[row - 1][row];
            for (int j = row; j < m; j++) {
                table[row - 1][j] -= table[row][j] * ratio;
            }
            
            print();
        }
    }
    
    
    private int findLeadingColumn(int row) {
        // Find the leading (pivot) column
        int column = 0;
        while (column < m && table[row][column] == 0) {
            column++;
        }
        
        return column;
    }
    
    
    private boolean checkRREF(int row, int column) {
        // This row contains only '0's, skip it
        if (column == m) {
            return true;
        }
        // Return true if it already corresponds to RREF (skip it)
        return table[row][column] == 1 && table[row - 1][column] == 0;
    }
    
    
    private boolean swapRows(int start) {
        boolean isSwappable = false;
        for (int i = start; i < n; i++) {
            if (table[i][start] == 0) {
                continue;
            }
            
            isSwappable = true;
            if (i == start) {
                break;
            }
            
            int[] temp = table[start];
            table[start] = table[i];
            table[i] = temp;
            break;
        }
        
        return isSwappable;
    }
    
    private void applyReduction(int start) {
        int origin = table[start][start];
        for (int i = start + 1; i < n; i++) {
            int ratio = table[i][start] / origin;
            
            // Skip useless iteration...
            if (ratio == 0) {
                continue;
            }
            
            // We should not deal with floating point numbers...
            int doubleCheck = ratio * origin;
            if (doubleCheck != table[i][start]) {
                throw matrixIncompatible("Not dealing with Integers");
            }
            
            for (int j = start; j < m; j++) {
                table[i][j] -= table[start][j] * ratio;
            }
        }
    }
    
    
    private IllegalStateException matrixIncompatible(String message) {
        return new IllegalStateException(
                String.format("The matrix is incompatible: %s.", message)
                );
    }
    
    
    
    public void print() {
        for (int[] row : table) {
            for (int element : row) {
                System.out.printf("%d ", element);
            }
            System.out.println();
        }
        System.out.println();
    }
}
