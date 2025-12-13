package aoc.p010.factory;


public class Bruteforce {

    final int[] joltage;
    public final Button[] buttons;
    
    public Bruteforce(Joltage joltage, Button[] sourceButtons) {
        this.joltage = joltage.value;
        buttons = new Button[sourceButtons.length];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(
                    sourceButtons[i], 
                    joltage.lowestJoltage
                    );
        }
    }
    
    
    public void press() {
        for (Button button : buttons) {
            if (button.pressAndBreak()) {
                break;
            }
        }
    }
    
    
    public boolean hasFinished() {
        int[] joltageCopy = joltage.clone();
        for (Button button : buttons) {
            for (int id : button.affectsIds) {
                joltageCopy[id] -= button.presses;
            }
        }
        for (int n : joltageCopy) {
            if (n > 0) {
                return false;
            }
        }
        return true;
    }
    
}
