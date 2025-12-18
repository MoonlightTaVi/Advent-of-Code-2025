package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p010.Elf;
import aoc.p010.MachineFactory;
import aoc.p010.factory.*;
import aoc.p010.solvers.*;


/**
 * JUnit tests for day 10.
 */
public class MachineTest {
    
    static MachineFactory reader;
    
    
    @BeforeAll
    public static void setup() {
        reader = new MachineFactory();
    }
    
    
    @Test
    public void parserTest() {
        String line = reader.lines[0];
        
        String indicators = reader.getIndicators(line);
        String[] buttons = reader.getButtons(line);
        String btn0 = buttons[0];
        
        int buttonCount = buttons.length;
        
        Assertions.assertEquals(".##.", indicators);
        Assertions.assertEquals("3", btn0);
        Assertions.assertEquals(6, buttonCount);
    }
    
    @Test
    public void bitIndicatorsTest() {
        IdleMachine machine = (IdleMachine) reader.getMachine(0);
        BitmaskSolver solver = machine.solver;
        
        int state = solver.finalStateMask;
        
        Assertions.assertEquals(6, state);
    }
    
    @Test
    public void bitButtonTest() {
        IdleMachine machine = (IdleMachine) reader.getMachine(0);
        BitmaskSolver solver = machine.solver;
        
        int btn0 = solver.getButton(0);
        int btn1 = solver.getButton(1);
        
        Assertions.assertEquals(8, btn0);
        Assertions.assertEquals(10, btn1);
    }
    
    @Test
    public void bitComboTest() {
        IdleMachine machine = (IdleMachine) reader.getMachine(0);
        BitmaskSolver solver = machine.solver;
        
        int count = solver.getCombinations();
        int len = machine.buttons.length;
        boolean[] combo = solver.getCombination(5);
        
        boolean[] expected = new boolean[len];
        expected[0] = true;
        expected[2] = true;

        Assertions.assertEquals(64, count);
        Assertions.assertArrayEquals(expected, combo);
    }
    
    @Test
    public void bitLastComboTest() {
        IdleMachine machine = (IdleMachine) reader.getMachine(0);
        BitmaskSolver solver = machine.solver;
        
        // Combinations start from 0, and the 'count' is exclusive
        int count = solver.getCombinations();
        int len = machine.buttons.length;
        boolean[] combo = solver.getCombination(count - 1);
        
        boolean[] expected = new boolean[len];
        for (int i = 0; i < len; i++) {
            expected[i] = true;
        }

        Assertions.assertEquals(64, count);
        Assertions.assertArrayEquals(expected, combo);
    }
    
    
    @Test
    public void buttonsPressedTest() {
        IdleMachine machine = (IdleMachine) reader.getMachine(0);
        BitmaskSolver solver = machine.solver;
        
        // Press 4th and 5th buttons to start the machine
        int btn4 = solver.getButton(4);
        int btn5 = solver.getButton(5);
        
        boolean isStarted = solver.press(btn4, btn5);
        
        Assertions.assertTrue(isStarted);
    }
    
    @Test
    public void elfWorkerTest() {
        Elf elf = new Elf(reader);
        
        long totalPresses = elf.tryToStartEmMachines();
        
        Assertions.assertEquals(7, totalPresses);
    }
    
    @Test
    public void elfJoltageTest() {
        Elf elf = new Elf(reader);
        
        long totalPresses = elf.tryToFixEmJoltages();
        
        Assertions.assertEquals(33, totalPresses);
    }
    
    static int[][] testMatrix = {
            {1, 3, 1, 9},
            {1, 1, -1, 1},
            {3, 11, 5, 35}
    };
    
    
    static int[][] testMatrixEchelone = {
            {1, 3, 1, 9},
            {0, -2, -2, -8},
            {0, 0, 0, 0}
    };
    
    static int[][] testMatrixFinal = {
            {1, 0, -2, -3},
            {0, 1, 1, 4},
            {0, 0, 0, 0}
    };
    
    
    @SuppressWarnings("deprecation")
    @Test
    public void matrixTest() {
        Matrix matrix = new Matrix(testMatrix);
        
        matrix.matrixToREF();
        Assertions.assertArrayEquals(testMatrixEchelone, matrix.table);
        
        matrix.matrixToRREF();
        Assertions.assertArrayEquals(testMatrixFinal, matrix.table);
    }
    
    
    @Test
    public void solverTest() {
        StartedMachine machine = (StartedMachine) reader.getStartedMachine(0);
        MatrixSolver solver = machine.solver;
        
        // Just an initial test
        //Assertions.assertEquals(11, solver.combined[6]);
        
        // The actual test
        long result = solver.solve();
        long expected = 10;
        
        Assertions.assertEquals(expected, result);
    }
    
    
    @Test
    public void cheezySolutionTest() {
        int[][] table = {
                { 1, 0, 0 , 1},
                { 0, 1, 0, 1},
                { 0, 1, 1, 2},
        };
        
        Matrix matrix = new Matrix(table);
        int result = matrix.checkOverlapsOfRows();
        
        Assertions.assertEquals(3, result);
    }
}
