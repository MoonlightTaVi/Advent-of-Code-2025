package aoc.p007;


public class Beams {
    long splits = 0;
    long allCount = 0;
    
    
    public int getCenter(int width) {
        return width / 2;
    }
    
    public long getTotalSplits() {
        return splits;
    }
    
    public long getTotalBeams() {
        return allCount;
    }
    
    
    public void traverse(char[][] map) {
        int width = map[0].length;
        int HALF = getCenter(width);
        
        long[] beams = new long[width];
        beams[HALF] = 1;
        
        // Cone of possible beam accessibility
        int coneLeft = HALF;
        int coneRight = HALF;
        
        for (int i = 0; i < map.length; i++) {
            
            // Check inside the accessible cone
            for (int j = coneLeft; j <= coneRight; j++) {
                
                // Perform a split if at least one beam is at the position
                if (map[i][j] == '^' && beams[j] > 0) {
                    splits++;
                    
                    // Move all the beams to the sides
                    beams[j - 1] += beams[j];
                    beams[j + 1] += beams[j];
                    
                    // Clear current X grid cell
                    beams[j] = 0;
                    
                    // Increase the cone
                    coneLeft = Math.max(--coneLeft, 0);
                    coneRight = Math.min(++coneRight, width - 1);
                }
                
            }
        }
        
        allCount = sum(beams);
    }
    
    
    private long sum(long[] beams) {
        long sum = 0;
        for (long n : beams) {
            sum += n;
        }
        return sum;
    }
}
