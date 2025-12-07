package aoc.p004;


public interface ForkliftIF {

    public void setMap(char[][] map);
    public int countAccessible();
    public boolean canAccess(int x, int y);
    
}
