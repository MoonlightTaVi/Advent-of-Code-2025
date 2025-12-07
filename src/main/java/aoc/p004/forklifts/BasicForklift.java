package aoc.p004.forklifts;


/**
 * Counts the accessible rolls of paper, but doesn't pick them.
 */
public class BasicForklift implements ForkliftIF {
    char[][] map;
    int width;
    int height;
    
    
    @Override
    public void setMap(char[][] map) {
        this.map = map;
        width = map.length;
        height = map[0].length;
    }


    @Override
    public int countAccessible() {
        int result = 0;
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (map[x][y] != '@') {
                    continue;
                }
                if (canAccess(x, y)) {
                    result++;
                }
            }
        }
        
        return result;
    }

    @Override
    public boolean canAccess(int x, int y) {
        int left = Math.max(x - 1, 0);
        int right = Math.min(x + 1, width - 1);
        int top = Math.max(y - 1, 0);
        int bottom = Math.min(y + 1, height - 1);
        
        int surrounding = 0;
        
        for (int i = left; i <= right; i++) {
            for (int j = top; j <= bottom; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (map[i][j] == '@') {
                    surrounding++;
                }
            }
        }
        
        return surrounding < 4;
    }

}
