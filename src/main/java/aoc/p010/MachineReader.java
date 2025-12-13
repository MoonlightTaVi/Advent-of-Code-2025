package aoc.p010;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc.FileReader;


public class MachineReader extends FileReader {

    public MachineReader() {
        super.read("010.txt");
    }
    
    
    public Machine getMachine(int id) {
        String line = super.lines[id];
        String indicators = getIndicators(line);
        String[] buttons = getButtons(line);
        
        return new Machine(indicators, buttons);
    }
    
    public int size() {
        return super.lines.length;
    }
    
    
    public String getIndicators(String line) {
        String regex = "\\[(.*?)\\]";
        
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        m.find();
        
        String indicators = m.group(1);
        return indicators;
    }
    
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
    
}
