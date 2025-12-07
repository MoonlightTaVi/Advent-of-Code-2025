package aoc.p004.forklifts;


/**
 * Keeps counting accessible rolls of paper and picking them up,
 * therefore freeing up the space on the map and making more rolls
 * accessible with each iteration.
 */
public class ContinuousForklift extends BasicForklift {
    
    /**
     * Keeps counting until no more rolls accessible.
     */
    @Override
    public int countAccessible() {
        int allCount = 0;
        int count = Integer.MAX_VALUE;
        while (count > 0) {
            count = super.countAccessible();
            allCount += count;
        }
        return allCount;
    }
    
    /**
     * Picks up a roll if it is accessible.
     */
    @Override
    public boolean canAccess(int x, int y) {
        boolean result = super.canAccess(x, y);
        if (result) {
            super.map[x][y] = '.';
        }
        return result;
    }

}
