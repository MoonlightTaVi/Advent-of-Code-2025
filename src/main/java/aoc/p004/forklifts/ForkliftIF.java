package aoc.p004.forklifts;


/**
 * A forklift moves around the {@code map} and picks any rolls of paper
 * it {@code canAccess}. It also counts the number of the rolls it
 * picked up.
 * @see #setMap(char[][])
 * @see #countAccessible()
 * @see #canAccess(int, int)
 */
public interface ForkliftIF {

    /**
     * Sets the map to this forklift. <br>
     * '@' on the map represents a rolling paper to be picked.
     * @param map Two-dimensional array that represents a map.
     */
    public void setMap(char[][] map);
    
    /**
     * Counts all the rolls of paper it can pick on the map.
     * @return Number of accessible rolls of paper.
     */
    public int countAccessible();
    
    /**
     * Checks if a roll of paper can be accessed and picked up.
     * @param x X-positin of the map tile.
     * @param y Y-positin of the map tile.
     * @return true if the position can be accessed.
     */
    public boolean canAccess(int x, int y);
    
}
