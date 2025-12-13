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
}
