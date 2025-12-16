package aoc.p010.solvers;

import aoc.p010.factory.StartedMachine;


/**
 * Matrix utility for the linear algebra equalities.
 */
public class Matrix {
    
    /** Prints the matrix to the console when it changes. */
    public final boolean debug = true;
    
    /** Number of columns. */
    public final int m;
    /** Number of rows. */
    public final int n;

    /** Table of the matrix. */
    public final int[][] table;
    
    
    /**
     * Constructs a matrix from a 2-dimensional array. <br>
     * For testing purposes.
     * @param matrix 2D array of integers. Each row must have
     * the same length.
     */
    public Matrix(int[][] matrix) {
        table = matrix;
        n = table.length;
        m = table[0].length;
    }
    
    /**
     * Constructs a matrix from a machine data object.
     * @param machine
     */
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
    
    
    /**
     * Prints the matrix to the console. <br>
     * Debug mode must be set to true.
     * @see #debug
     */
    public void print() {
        if (!debug) {
            return;
        }
        
        for (int[] row : table) {
            for (int element : row) {
                System.out.printf("%d ", element);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    /**
     * Transforms this matrix to its final RREF form
     * and returns the resulting 2D array.
     * @return 2D integer array of the RREF of the matrix.
     */
    public int[][] build() {
        matrixToREF();
        matrixToRREF();
        
        return table;
    }
    
    
    /**
     * Transforms this matrix into the row echelon form. <br>
     * For testing purposes only, use build() instead.
     */
    public void matrixToREF() {
        print();
        
        // In case there are more rows than columns
        int end = Math.min(n, m);
        
        for (int i = 0; i < end; i++) {
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
    
    /**
     * Transforms this matrix into the reduced raw echelon form. <br>
     * For testing purposes only, use build() instead.
     */
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
            
            // Set the element above this leading element to 0
            // Change the other elements in the row above respectively
            subtract(table[row - 1], table[row], column);
            
            print();
        }
    }
    
    
    /**
     * Finds the first non-zero element in a row and returns the index
     * of the corresponding column.
     * @param row
     * @return The ID of the leading column of the row.
     */
    private int findLeadingColumn(int row) {
        // Find the leading (pivot) column
        int column = 0;
        
        while (column < m && table[row][column] == 0) {
            column++;
        }
        
        // Returns an out-of-bounds index if there're only zeroes
        return column;
    }
    
    /**
     * Checks if this row can (and should) be applied a row reduction.
     * @param row Row to test against conditions of RREF.
     * @param column Leading column of this row.
     * @return True if this row already corresponds to the RREF
     * conditions and must be skipped.
     */
    private boolean checkRREF(int row, int column) {
        // Out of bounds: skip
        if (column == m) {
            return true;
        }
        
        // Return true if it already corresponds to RREF (skip it)
        return table[row][column] == 1 && table[row - 1][column] == 0;
    }
    
    /**
     * Checks all the rows in the table and makes it so the table
     * has a non-zero element at position [start][start] by swapping rows
     * (if it is necessary and possible).
     * @param start The position of the first leading element 
     * in the table during the current iteration of REF.
     * @return True if the rows can be applied REF transformation
     * during the current iteration. If it's false, must skip 
     * to the next iteration.
     */
    private boolean swapRows(int start) {
        // Reached the limit (by columns), cannot swap
        if (start >= m) {
            return false;
        }
        
        boolean isSwappable = false;
        
        for (int i = start; i < n; i++) {
            // Not zero, seek further
            if (table[i][start] == 0) {
                continue;
            }
            
            // Success
            isSwappable = true;
            
            // Need not to swap
            if (i == start) {
                break;
            }
            
            // Swap
            int[] temp = table[start];
            table[start] = table[i];
            table[i] = temp;
            break;
        }
        
        return isSwappable;
    }
    
    /**
     * Subtracts one row from another. As the result, the first row
     * will have 0 at its {@code leadingColumn} position (i.e. above
     * the leading element of the second row). <br>
     * Part of the RREF transformation algorithm.
     * @param minuend Row to subtract from.
     * @param subtrahend Row to subtract from the minuend.
     * @param leadingColumn The leading element position in the
     * subtrahend row.
     */
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
    
    /**
     * Divides every element of this row by the leading element
     * of this row. <br>
     * As the result, the leading element becomes 1.
     * @param row
     * @param leadingColumn
     */
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
    
    
    /**
     * Returns a template for matrix exception.
     * @param message Specific message about the exception.
     * @return
     */
    private IllegalStateException matrixIncompatible(String message) {
        return new IllegalStateException(
                String.format("The matrix is incompatible: %s.", message)
                );
    }
}
