package aoc.p004;


public class ContinuousForklift extends BasicForklift {
    
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
    
    @Override
    public boolean canAccess(int x, int y) {
        boolean result = super.canAccess(x, y);
        if (result) {
            super.map[x][y] = '.';
        }
        return result;
    }

}
