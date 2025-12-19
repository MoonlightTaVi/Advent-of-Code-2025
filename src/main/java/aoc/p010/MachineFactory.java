package aoc.p010;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc.FileReader;
import aoc.p010.factory.*;


/**
 * Factory for the machines to start and configure. <br>
 * Parses the resource file, prepares the Machine objects.
 * @see #getIdleMachine(int)
 * @see #getStartedMachine(int)
 */
public class MachineFactory extends FileReader {

    /**
     * Reads data from the file.
     */
    public MachineFactory() {
        super.read("010.txt");
    }
    
    
    /**
     * Part 1 of the task: the machine that must be started.
     * @param id
     * @return
     */
    public Machine getIdleMachine(int id) {
        String line = super.lines[id];
        String indicators = getIndicators(line);
        String[] buttons = getButtons(line);
        String joltage = getJoltage(line);
        
        return new IdleMachine(indicators, buttons, joltage);
    }
    
    /**
     * Part 2 of the task: the machine that must be configured.
     * @param id
     * @return
     */
    public Machine getStartedMachine(int id) {
        String line = super.lines[id];
        String indicators = getIndicators(line);
        String[] buttons = getButtons(line);
        String joltage = getJoltage(line);
        
        return new StartedMachine(indicators, buttons, joltage);
    }
    
    /**
     * Number of all the machines.
     * @return
     */
    public int size() {
        return super.lines.length;
    }
    
    
    /**
     * Parses indicators for the machine by ID.
     * @param line
     * @return
     */
    public String getIndicators(String line) {
        String regex = "\\[(.*?)\\]";
        
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        m.find();
        
        String indicators = m.group(1);
        return indicators;
    }
    
    /**
     * Parses buttons for the machine by ID.
     * @param line
     * @return
     */
    public String[] getButtons(String line) {
        String regex = "\\((.*?)\\)";
        
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        
        List<String> buttons = new ArrayList<>();
        while (m.find()) {
            buttons.add(m.group(1));
        }
        
        return buttons.toArray(String[]::new);
    }
    
    /**
     * Parses joltages for the machine by ID.
     * @param line
     * @return
     */
    public String getJoltage(String line) {
        String regex = "\\{(.*?)\\}";
        
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        m.find();
        
        String joltage = m.group(1);
        return joltage;
    }
    
}
