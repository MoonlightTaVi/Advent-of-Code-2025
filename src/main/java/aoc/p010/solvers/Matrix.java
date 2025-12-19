package aoc.p010.solvers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aoc.p010.factory.StartedMachine;


/**
 * Matrix utility for the linear algebra equalities.
 */
public class Matrix {
    
    /** Prints the matrix to the console when it changes. */
    public final boolean debug = false;
    
    /** Number of columns. */
    public final int cols;
    /** Number of rows. */
    public final int rows;

    /** Table of the matrix. */
    public final int[][] table;
    
    /* List of columns that contain free variables. **/
    public List<Integer> freeVariables = new ArrayList<>();
    
    
    /**
     * Constructs a matrix from a 2-dimensional array. <br>
     * For testing purposes.
     * @param matrix 2D array of integers. Each row must have
     * the same length.
     */
    public Matrix(int[][] matrix) {
        table = matrix;
        rows = table.length;
        cols = table[0].length;
    }
    
    /**
     * Constructs a matrix from a machine data object.
     * @param machine
     */
    public Matrix(StartedMachine machine) {
        // Columns (for each button)
        // Add 1 column for the required joltage of the row
        cols = machine.buttons.length + 1;
        // Rows (for each joltage)
        rows = machine.requiredJoltage.length;
        
        table = new int[rows][];
        for (int i = 0; i < rows; i++) {
            table[i] = new int[cols];
            table[i][cols - 1] = (int) machine.requiredJoltage[i];
        }
        
        for (int i = 0; i < cols - 1; i++) {
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
        print(debug);
    }
    
    public void print(boolean debug) {
        if (!debug) {
            return;
        }
        
        for (int[] row : table) {
            System.out.print("[");
            for (int element : row) {
                System.out.printf("%d, ", element);
            }
            System.out.println("],");
        }
        System.out.println();
    }
    
    
    
    /**
     * Transforms this matrix into the reduced row echelon form. <br>
     * For testing purposes only, use build() instead.
     */
    public void matrixToRREF() {
        Set<Integer> pivotColumns = new HashSet<>();
        int pivotRow = 0;
        
        for (int c = 0; c < cols - 1; c++) {
            if (pivotRow >= rows) {
                break;
            }
            
            // Find a row that has an appropriate leading element
            int bestRow = findPivotRow(pivotRow, c);
            
            // Not possible to form a pivot
            if (bestRow == -1) {
                continue;
            }
            
            // Swap current row and pivot row
            swapRows(pivotRow, bestRow);
            
            // Make current (pivot) row have 1 as its leading element
            divideByLeading(table[pivotRow], c);
            
            // Make all rows above and below the current row
            //  have zeroes at the pivot column
            //  (so the only non-zero element of this column
            //   is the leading element (1) of the pivot row)
            for (int r = 0; r < rows; r++) {
                if (r == pivotRow) {
                    continue;
                }
                
                subtract(table[r], table[pivotRow], c);
            }
            
            pivotColumns.add(c);
            
            // Next step
            pivotRow++;
        }
        
        
        for (int c = 0; c < cols - 1; c++) {
            if (!pivotColumns.contains(c)) {
                freeVariables.add(c);
            }
        }
    }
    
    
    public int checkOnlyPivots() {
        int[] combined = new int[cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                combined[c] += table[r][c];
            }
        }
        
        for (int i = 0; i < cols - 1; i++) {
            if (combined[i] != 1) {
                return 0;
            }
        }
        
        return combined[cols - 1];
    }
    
    
    /**
     * Makes two rows in the matrix swap their position.
     * @param firstId The ID of the first row in the matrix.
     * @param secondId The ID of the second row in the matrix.
     */
    private void swapRows(int firstId, int secondId) {
        // The same row
        if (firstId == secondId) {
            return;
        }
        
        int[] temp = table[firstId];
        table[firstId] = table[secondId];
        table[secondId] = temp;
    }
    
    /**
     * Finds the ID of the row in the matrix that corresponds
     * to the appropriate conditions of a pivot row.
     * @param fromRow Row to start searching from.
     * @param atColumn Pivot column that must have the leading element.
     * @return The ID of the first row that can form a pivot.
     * Returns -1 if this column cannot form a pivot and must be skipped.
     */
    private int findPivotRow(int fromRow, int atColumn) {
        int result = -1;
        
        for (int r = fromRow; r < rows; r++) {
            if (checkDivision(table[r], atColumn)) {
                result = r;
                break;
            }
        }
        
        return result;
    }
    
    /**
     * Checks if each element of this row can be divided by the leading
     * element of this row without a remainder (this is necessary to form
     * a pivot).
     * @param row Row to be tested against the appropriate conditions
     * of forming a pivot.
     * @param leadID Position of the leading element in the row.
     * @return True if this row can form a pivot column, false otherwise.
     */
    private boolean checkDivision(int[] row, int leadID) {
        int lead = row[leadID];
        
        if (lead == 0) {
            return false;
        }
        
        boolean success = true;
        
        for (int i = 0; i < cols; i++) {
            if (row[i] % lead != 0) {
                success = false;
                break;
            }
        }
        
        return success;
    }
    
    
    /**
     * Divides each element in this row (starting from {@code leadID}
     * column) by its leading element (positioned at {@code leadID}
     * column). As the result, the leading element will become 1.
     * @param row Row to divide the elements of.
     * @param leadId Position of the leading element in the row.
     */
    private void divideByLeading(int[] row, int leadId) {
        int lead = row[leadId];
        
        for (int i = 0; i < cols; i++) {
            if (row[i] != 0 && row[i] % lead != 0) {
                throw new RuntimeException("Floating point division");
            }
            
            row[i] /= lead;
        }
    }
    
    /**
     * Subtracts {@code thisRow} from {@code fromRow}, which means
     * subtracts each element of the subtrahend row (starting from
     * {@code coefId} ) from the element of the minuend row that is
     * positioned at the same column. The elements of the subtrahend
     * row are multiplied by a coefficient before subtraction;
     * the coefficient equals {@code fromRow[coefId]}. <br>
     * As the result, {@code fromRow[coefId]} will be equal to 0
     * (given that {@code thisRow[coefId]} equals 1).
     * @param fromRow Row to subtract from (minuend).
     * @param thisRow Row to subtract from the previous row (subtrahend).
     * @param coefId Position of the coefficient in the minuend row.
     * Each element of the subtrahend row will be multiplied by
     * this coefficient before subtraction.
     */
    private void subtract(int[] fromRow, int[] thisRow, int coefId) {
        int coef = fromRow[coefId];
        
        if (coef == 0) {
            return;
        }
        
        for (int i = 0; i < cols; i++) {
            fromRow[i] -= thisRow[i] * coef;
        }
    }
}
