package aoc.p010.factory;


public class Matrix {
    /** Number of columns. */
    final int m;
    /** Number of rows. */
    final int n;

    final int[][] table;
    
    
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
            table[i][m - 1] = (int) machine.requiredJoltage[n];
        }
        
        for (int i = 0; i < m - 1; i++) {
            // The machine button arrays refer to joltage IDs
            for (int id : machine.buttons[i]) {
                table[id][i] = 1;
            }
        }
    }
    
    
    public int[][] eliminatedByGauss() {
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
        
        return table;
    }
    
    public int[][] finalizeEchelone() {
        int size = Math.min(n - 1, m);
        for (int i = size; i > 0; i--) {
            // Check if the row is already at final form
            if (checkRREF(i, i)) {
                continue;
            }
            
            // Set the lead row to 1 by changing all of the row values
            int lead = table[i][i];
            for (int j = i; j < m; j++) {
                if (table[i][j] % lead != 0) {
                    throw matrixIncompatible(
                            "Not dealing with integers (at final form)"
                            );
                }
                table[i][j] /= lead;
            }
            
            // Set the element above this lead element to 0
            //  by changing all of the values of the row above
            int ratio = table[i - 1][i];
            for (int j = i; j < m; j++) {
                table[i - 1][j] -= table[i][j] * ratio;
            }
            
            print();
        }
        
        return table;
    }
    
    
    private boolean checkRREF(int row, int column) {
        if (table[row][column - 1] != 0) {
            throw matrixIncompatible(
                    "This element is not leading at all!"
                    );
        }
        
        // Just skip to next
        if (table[row][column] == 0) {
            return true;
        }
        
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
