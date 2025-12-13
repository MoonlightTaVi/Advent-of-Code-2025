package aoc.p010.factory;

import java.util.Arrays;

public class StartedMachine {
    final int[][] buttons;
    public final long[][] combinations;
    
    final long[] joltage;
    
    long currentArea;
    
    
    public StartedMachine(String[] buttonsStr, String joltageStr) {
        buttons = new int[buttonsStr.length][];
        for (int i = 0; i < buttonsStr.length; i++) {
            buttons[i] = Arrays.stream(buttonsStr[i].split(","))
                    .mapToInt(Integer::valueOf)
                    .toArray();
        }
        
        joltage = Arrays.stream(joltageStr.split(","))
                    .mapToLong(Long::valueOf)
                    .toArray();
        
        int len = getCombinations();
        combinations = new long[len][];
        for (int i = 0; i < len; i++) {
            combinations[i] = makeCombination(i);
        }
        
        Arrays.sort(
                combinations, 
                (a, b) -> Long.compare(getArea(b), getArea(a))
                );
        
        currentArea = getArea(joltage);
    }
    
    
    public long[] makeCombination(int combinationId) {
        long[] result = new long[joltage.length];
        boolean[] combo = getCombination(combinationId);
        
        for (int i = 0; i < buttons.length; i++) {
            if (!combo[i]) {
                continue;
            }
            int[] button = buttons[i];
            for (int id : button) {
                result[id] += 1;
            }
        }
        
        return result;
    }
    
    public int getCombinations() {
        return (int) Math.pow(2, buttons.length);
    }
    
    public boolean[] getCombination(int id) {
        int len = buttons.length;
        int start = len - 1;
        
        boolean[] combination = new boolean[len];
        for (int i = start; i >= 0; i--) {
            combination[i] = (id & (1 << i)) != 0;
        }
        
        return combination;
    }
    
    public int countButtons(boolean[] inCombination) {
        int sum = 0;
        for (boolean toggled : inCombination) {
            if (toggled) {
                sum++;
            }
        }
        return sum;
    }
    
    public int getPriorityId() {
        long max = 0;
        int maxId = -1;
        
        for (int i = 0; i < joltage.length; i++) {
            if (joltage[i] > max) {
                max = joltage[i];
                maxId = i;
            }
        }
        
        return maxId;
    }
    
    public int getLowestId() {
        long min = Integer.MAX_VALUE;
        int maxId = -1;
        
        for (int i = 0; i < joltage.length; i++) {
            if (joltage[i] < min) {
                min = joltage[i];
                maxId = i;
            }
        }
        
        return maxId;
    }
    
    
    public int getPrioritizedCombinationId() {
        int maxId = getPriorityId();
        long prioritizedValue = Integer.MIN_VALUE;
        long prioritizedArea = Integer.MIN_VALUE;
        int prioritizedId = -1;
        
        out:
        for (int j = 0; j < combinations.length; j++) {
            long[] combo = combinations[j];
            long area = getArea(combo);
            for (int i = 0; i < joltage.length; i++) {
                if (combo[i] > joltage[i]) {
                    continue out;
                }
            }
            if (combo[maxId] < prioritizedValue) {
                continue;
            }
            if (area < prioritizedArea) {
                continue;
            }
            prioritizedId = j;
            prioritizedValue = combo[maxId];
            prioritizedArea = area;
        }
        
        return prioritizedId;
    }
    
    
    public long getArea(long[] longArray) {
        long sum = 0;
        for (long n : longArray) {
            sum += n;
        }
        return sum;
    }
    
    
    public boolean hasNotFinished() {
        return currentArea > 0;
    }
    
    
    public long apply(long[] combination) {
        long diff = 1;//Integer.MAX_VALUE;
        
        for (int i = 0; i < joltage.length; i++) {
            if (combination[i] > 0 && joltage[i] < diff) {
                //diff = joltage[i];
            }
        }

        long sum = 0;
        
        for (int i = 0; i < joltage.length; i++) {
            if (combination[i] > 0) {
                sum += diff;
                joltage[i] -= diff;
            }
        }
        
        currentArea -= sum;
        
        return diff;
    }
    
}
