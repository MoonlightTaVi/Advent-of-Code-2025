package aoc25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aoc.p010.Machine;
import aoc.p010.MachineReader;

public class IndicatorsTest {
    
    static MachineReader reader;
    
    
    @BeforeAll
    public static void setup() {
        reader = new MachineReader();
    }
    
    
    @Test
    public void parserTest() {
        Machine machine = reader.getMachine(0);
        
        String indicators = machine.startedState;
        String btn0 = machine.buttons[0];
        int buttonCount = machine.buttons.length;
        
        Assertions.assertEquals(".##.", indicators);
        Assertions.assertEquals("3", btn0);
        Assertions.assertEquals(6, buttonCount);
    }
    
    @Test
    public void bitIndicatorsTest() {
        Machine machine = reader.getMachine(0);
        int state = machine.getStartedState();
        
        Assertions.assertEquals(6, state);
    }
    
    @Test
    public void bitButtonTest() {
        Machine machine = reader.getMachine(0);
        
        int btn0 = machine.getButton(0);
        int btn1 = machine.getButton(1);
        
        Assertions.assertEquals(8, btn0);
        Assertions.assertEquals(10, btn1);
    }
    
    @Test
    public void bitComboTest() {
        Machine machine = reader.getMachine(0);
        
        int count = machine.getCombinations();
        int len = machine.buttons.length;
        boolean[] combo = machine.getCombination(5);
        
        boolean[] expected = new boolean[len];
        expected[0] = true;
        expected[2] = true;

        Assertions.assertEquals(64, count);
        Assertions.assertArrayEquals(expected, combo);
    }
    
    @Test
    public void bitLastComboTest() {
        Machine machine = reader.getMachine(0);
        
        // Combinations start from 0, and the 'count' is exclusive
        int count = machine.getCombinations();
        int len = machine.buttons.length;
        boolean[] combo = machine.getCombination(count - 1);
        
        boolean[] expected = new boolean[len];
        for (int i = 0; i < len; i++) {
            expected[i] = true;
        }

        Assertions.assertEquals(64, count);
        Assertions.assertArrayEquals(expected, combo);
    }
}
