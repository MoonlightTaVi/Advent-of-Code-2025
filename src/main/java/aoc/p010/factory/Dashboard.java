package aoc.p010.factory;


public class Dashboard {

    public final Button[] buttons;
    public final Joltage joltage;
    
    
    public Dashboard(String[] buttonsStr, String joltageRequired) {
        joltage = new Joltage(joltageRequired);
        
        int len = buttonsStr.length;
        buttons = new Button[len];
        
        for (int i = 0; i < len; i++) {
            buttons[i] = new Button(buttonsStr[i]);
        }
    }
    
    
    public void press() {
        Button mostOptimalButton = null;
        int mostOptimalSum = 0;
        
        for (Button button : buttons) {
            int sum = joltage.getEffectivenessOf(button);
            if (sum > mostOptimalSum) {
                mostOptimalSum = sum;
                mostOptimalButton = button;
            }
        }
        
        joltage.press(mostOptimalButton);
    }
    
    
    public boolean shouldBruteforce() {
        return joltage.lowestJoltage < 5;
    }
    
    
    public void mergeButtons(Button[] fromArray) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].presses += fromArray[i].presses;
        }
    }
    
    public int countPresses() {
        int result = 0;
        
        for (Button button : buttons) {
            result += button.presses;
        }
        
        return result;
    }
}
