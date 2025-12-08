package aoc.p007;


public class Beam {
    final int hashCode;
    public final Position position;
    
    
    public Beam(int x, int y) {
        this(new Position(x, y));
    }
    
    public Beam(Position position) {
        this.position = position;
        this.hashCode = position.hashCode();
    }
    
    
    public void moveDown() {
        position.y++;
    }

    public Beam[] split() {
        Beam[] pair = new Beam[2];
        pair[0] = new Beam(position.x - 1, position.y);
        pair[1] = new Beam(position.x + 1, position.y);
        return pair;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Beam b) {
            return position.equals(b.position);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return position.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("Beam#%d[%s]", hashCode, position);
    }
}
