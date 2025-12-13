package aoc.p010.factory;

import java.util.Arrays;

public class Dashboard {

    final Button[] buttons;
    final int[] joltageRequired;
    
    
    public Dashboard(String[] buttonsStr, String joltageRequired) {
        this.joltageRequired = Arrays.stream(joltageRequired.split(","))
                .mapToInt(Integer::valueOf)
                .toArray();
        
        int len = buttonsStr.length;
        buttons = new Button[len];
        
        for (int i = 0; i < len; i++) {
            buttons[i] = new Button(buttonsStr[i]);
            buttons[i].setMaxJoltage(this.joltageRequired);
        }
    }
    
    
    public boolean canPress() {
        for (int i = 0; i <= buttons.length; i++) {
            if (i == buttons.length) {
                return false;
            }
            
            if (buttons[i].pressAndBreak()) {
                break;
            }
        }
        
        return true;
    }
    
    public int[] getResultingJoltage() {
        int[] joltage = new int[joltageRequired.length];
        
        for (Button button : buttons) {
            button.affectJoltage(joltage);
        }
        
        return joltage;
    }
    
    public boolean isJoltageProper() {
        int[] joltage = getResultingJoltage();
        
        for (int i = 0; i < joltage.length; i++) {
            if (joltageRequired[i] != joltage[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    
    public int countPresses() {
        int result = 0;
        
        for (Button button : buttons) {
            result += button.presses;
        }
        
        return result;
    }
}
