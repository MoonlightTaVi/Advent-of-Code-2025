package aoc.p010.factory;


public class Bruteforce {

    final int[] joltage;
    public final Button[] buttons;
    
    public int lastPressesCount = 0;
    
    public Bruteforce(Joltage joltage, Button[] sourceButtons) {
        this.joltage = joltage.value;
        buttons = new Button[sourceButtons.length];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(
                    sourceButtons[i], 
                    joltage.highestValue()
                    );
        }
    }
    
    
    public boolean canContinue() {
        for (int i = 0; i <= buttons.length; i++) {
            if (i == buttons.length) {
                return false;
            }
            if (!buttons[i].affectsAll(joltage)) {
                continue;
            }
            if (buttons[i].pressAndBreak()) {
                break;
            }
        }
        return true;
    }
    
    
    public boolean isResultSuccessful() {
        lastPressesCount = 0;
        int[] joltageCopy = joltage.clone();
        for (Button button : buttons) {
            for (int id : button.affectsIds) {
                joltageCopy[id] -= button.presses;
            }
            lastPressesCount += button.presses;
        }
        for (int n : joltageCopy) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }
    
}
