package aoc.p010.solvers;

import java.util.ArrayList;
import java.util.List;

import aoc.p010.factory.StartedMachine;


/**
 * Matrix utility for the linear algebra equalities.
 */
public class Matrix {
    
    /** Prints the matrix to the console when it changes. */
    public final boolean debug = false;
    
    /** Number of columns. */
    public final int m;
    /** Number of rows. */
    public final int n;

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
        print(debug);
    }
    
    public void print(boolean debug) {
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
        //matrixToREF();
        matrixToRREF();
        
        return table;
    }
    
    
    /**
     * Transforms this matrix into the row echelon form. <br>
     * For testing purposes only, use {@code build()} instead.
     */
    @Deprecated
    public void matrixToREF() {
        int row = 0;
        int column = 0;
        
        while (row < n && column < m) {
            
            // Find a row that has an appropriate leading element
            int pivotRow = findPivotRow(row, column);
            
            // Not possible to form a pivot
            if (pivotRow == -1) {
                // Save this column as containing a free variable
                freeVariables.add(column);
                
                // Skip to the next column
                column++;
                
                continue;
            }
            
            // Swap current row and pivot row
            swapRows(row, pivotRow);
            
            // Make current (pivot) row have 1 as its leading element
            //divideByLeading(table[row], column);
            
            // Make all rows below the current row (ONLY below)
            //  have zeroes at the pivot column
            //  (so the only non-zero element of this column
            //   BELOW the pivot row and including the pivot row
            //   is the leading element (1) of the pivot row)
            //    (techically, it's not a pivot row this way)
            for (int i = row + 1; i < n; i++) {
                subtract(table[i], table[row], column);
            }
            
            // Next step
            row++;
            column++;
        }
    }
    
    /**
     * Transforms this matrix into the reduced row echelon form. <br>
     * For testing purposes only, use build() instead.
     */
    public void matrixToRREF() {
        int row = 0;
        int column = 0;
        
        while (row < n && column < m - 1) {
            
            // Find a row that has an appropriate leading element
            int pivotRow = findPivotRow(row, column);
            
            // Not possible to form a pivot
            if (pivotRow == -1) {
                // Save this column as containing a free variable
                freeVariables.add(column);
                
                // Skip to the next column
                column++;
                
                continue;
            }
            
            // Swap current row and pivot row
            swapRows(row, pivotRow);
            
            // Make current (pivot) row have 1 as its leading element
            divideByLeading(table[row], column);
            
            // Make all rows above and below the current row
            //  have zeroes at the pivot column
            //  (so the only non-zero element of this column
            //   is the leading element (1) of the pivot row)
            for (int i = 0; i < n; i++) {
                if (i == row) {
                    continue;
                }
                
                subtract(table[i], table[row], column);
            }
            
            // Next step
            row++;
            column++;
        }
        
        for (int i = column; i < m - 1; i++) {
            freeVariables.add(i);
        }
    }
    
    
    /**
     * If there are rows that do not overlap, and the whole group
     * of this rows covers all of the columns, there's a single
     * possible answer to the matrix resolution. <br>
     * This method is not "smart", which means it will not always
     * find this exact solution for the matrix, but it solves
     * most of the cases.
     * @return The only possible solution for the matrix. Returns 0
     * if could not find such solution.
     */
    public int checkOverlapsOfRows() {
        // Find masks of each row
        List<Integer> rowMasks = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            int mask = 0;
            for (int c = 0; c < m - 1; c++) {
                if (table[r][c] != 0) {
                    mask = (mask | (1 << c));
                }
            }
            rowMasks.add(mask);
        }
        
        // The required mask is '1' at each bit
        //  (a full no-overlap)
        int requiredMask = 0;
        for (int c = 0; c < m - 1; c++) {
            requiredMask = (requiredMask | 1 << c);
        }
        
        // The group that must correspond to the required mask
        List<Integer> finalGroup = new ArrayList<>();
        int width = rowMasks.size();
        
        // Each row forms its own group
        for (int i = 0; i < width; i++) {
            List<Integer> group = new ArrayList<>();
            group.add(i);
            
            // The mask of the whole group (start from maskI)
            int groupMask = rowMasks.get(i);
            
            // Check against all other rows
            for (int j = 0; j < width; j++) {
                if (i == j) {
                    continue;
                }

                boolean success = true;
                int maskJ = rowMasks.get(j);
                
                // Check row J against all masks in the group
                for (int k = 0; k < group.size(); k++) {
                    int rowK = group.get(k);
                    int maskK = rowMasks.get(rowK);
                    
                    // Check if the two masks do not overlap
                    if ((maskK & maskJ) != 0) {
                        success = false;
                        break;
                    }
                    
                }
                
                // Update group mask
                if (success) {
                    groupMask = (groupMask | maskJ);
                    group.add(j);
                }
            }
            
            // The final group has been found
            if (groupMask == requiredMask) {
                finalGroup = group;
                break;
            }
        }
        
        int sum = 0;
        for (int rowId : finalGroup) {
            sum += table[rowId][m - 1];
        }
        
        return sum;
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
     * @param row Row to start searching from.
     * @param column Pivot column that must have the leading element.
     * @return The ID of the first row that can form a pivot.
     * Returns -1 if this column cannot form a pivot and must be skipped.
     */
    private int findPivotRow(int row, int column) {
        int result = -1;
        
        for (int i = row; i < n; i++) {
            if (checkDivision(table[i], column)) {
                result = i;
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
        
        for (int i = leadID + 1; i < m; i++) {
            if (row[leadID] % lead != 0) {
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
        
        for (int i = leadId; i < m; i++) {
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
        // thisRow[coefId] must be equal to 1
        //  But double check for bugs
        int coef = fromRow[coefId] / thisRow[coefId];
        
        for (int i = coefId; i < m; i++) {
            fromRow[i] -= thisRow[i] * coef;
        }
    }
}
