package aoc.p007.beams;

import aoc.p007.map.Position;

/**
 * A tachyon beam used for teleportation. Moves forward (increasing its
 * Y-position) during its life cycle. Its hash code depends on its
 * current position and may change.
 */
public class Beam {
    
    public final Position position;
    
    
    /**
     * Creates a beam with initial position on the map.
     * @param x X-position of the beam.
     * @param y Y-position of the beam.
     */
    public Beam(int x, int y) {
        this(new Position(x, y));
    }
    
    /**
     * Creates a beam with initial position on the map.
     * @param position Position of the beam.
     */
    public Beam(Position position) {
        this.position = position;
    }
    
    
    /**
     * Moves down the grid by 1 (forward).
     */
    public void moveDown() {
        position.y++;
    }

    /**
     * Splits itself into two parts. 
     * Creates a copy of self to the left and shifts itself to the right.
     * @return A copy of self, shifted left.
     */
    public Beam split() {
        Beam split = new Beam(position.x - 1, position.y);
        position.x += 1;
        return split;
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
        return String.format("Beam#%d[%s]", hashCode(), position);
    }
    
}
