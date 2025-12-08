package aoc.p007;


public class Position {
    public int x;
    public int y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Position p) {
            return x == p.x && y == p.y;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int result = 1;
        result = (result << 5) - result + x;
        result = (result << 5) - result + y;
        return result;
    }
    
    @Override
    public String toString() {
        return String.format("(%d;%d)", x, y);
    }
    
}
