package aoc.p007;


/**
 * Stores all the beams that traverse through the map and performs
 * their movement logic.
 */
public class Beams {
    long splits = 0;
    long allCount = 0;
    
    
    /**
     * Finds the starting position of a beam in the map.
     * @param map
     * @return Center of the map.
     */
    public int getStart(Map map) {
        return map.width / 2;
    }
    
    /**
     * Total number of splits that happened.
     */
    public long getTotalSplits() {
        return splits;
    }
    
    /**
     * Total number of beams that reached the bottom limit of the map.
     */
    public long getTotalBeams() {
        return allCount;
    }
    
    /**
     * Emits a beam inside the map and performs its 
     * movement and splitting logic.
     * @param mapObject Map to be traversed.
     */
    public void traverse(Map mapObject) {
        char[][] map = mapObject.array;
        
        int width = mapObject.width;
        int center = getStart(mapObject);
        
        long[] beams = new long[width];
        beams[center] = 1;
        
        // Cone of possible beam accessibility
        int coneLeft = center;
        int coneRight = center;
        
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
    
    
    /**
     * Sums up the array of Long numbers.
     * @param beams long[] that represents a row of beams 
     * at horizontal positions of the map.
     * @return Total number of beams in the row.
     */
    private long sum(long[] beams) {
        long sum = 0;
        for (long n : beams) {
            sum += n;
        }
        return sum;
    }
}
