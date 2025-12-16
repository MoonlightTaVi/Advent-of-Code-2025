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
    
    
    public void print() {
        for (int[] row : table) {
            for (int element : row) {
                System.out.printf("%d ", element);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    public void matrixToREF() {
        print();
        
        for (int i = 0; i < n; i++) {
            // Set the first element of the first row to != 0 (swap)
            // Skip if not possible
            if (!swapRows(i)) {
                continue;
            }
            
            // Make all other rows have 0 at the i column
            for (int j = i + 1; j < n; j++) {
                subtract(table[j], table[i], i);
            }
            
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

            int lead = table[row][column];
            // Set the lead row to 1 by changing all of the row values
            // Division by 1 means nothing, so just skip it
            if (lead != 1) {
                divide(table[row], column);
            }
            
            // Set the element above this lead element to 0
            // Change the other elements in the row above respectively
            subtract(table[row - 1], table[row], column);
            
            print();
        }
    }
    
    
    private int findLeadingColumn(int row) {
        // Find the leading (pivot) column
        int column = 0;
        while (column < m && table[row][column] == 0) {
            column++;
        }
        // Returns an out-of-bounds index if there're only zeroes
        return column;
    }
    
    private boolean checkRREF(int row, int column) {
        // Out of bounds:
        //  This row contains only '0's, skip it
        if (column == m) {
            return true;
        }
        // Return true if it already corresponds to RREF (skip it)
        return table[row][column] == 1 && table[row - 1][column] == 0;
    }
    
    private boolean swapRows(int leadingColumn) {
        boolean isSwappable = false;
        for (int i = leadingColumn; i < n; i++) {
            if (table[i][leadingColumn] == 0) {
                continue;
            }
            
            isSwappable = true;
            if (i == leadingColumn) {
                break;
            }
            
            int[] temp = table[leadingColumn];
            table[leadingColumn] = table[i];
            table[i] = temp;
            break;
        }
        
        return isSwappable;
    }
    
    private void subtract(
            int[] minuend, 
            int[] subtrahend, 
            int leadingColumn
            ) {
        
        // The division should not have a remainder
        if (minuend[leadingColumn] % subtrahend[leadingColumn] != 0) {
            throw matrixIncompatible(
                    "Not dealing with Integers (subtraction error)"
                    );
        }
        
        int len = minuend.length;
        int ratio = minuend[leadingColumn] / subtrahend[leadingColumn];
        
        for (int i = leadingColumn; i < len; i++) {
            minuend[i] -= ratio * subtrahend[i];
        }
    }
    
    private void divide(int[] row, int leadingColumn) {
        int lead = row[leadingColumn];
        for (int j = leadingColumn; j < m; j++) {
            
            if (row[j] % lead != 0) {
                throw matrixIncompatible(
                        "Not dealing with integers (division error)"
                        );
            }
            
            row[j] /= lead;
        }
    }
    
    
    private IllegalStateException matrixIncompatible(String message) {
        return new IllegalStateException(
                String.format("The matrix is incompatible: %s.", message)
                );
    }
}
