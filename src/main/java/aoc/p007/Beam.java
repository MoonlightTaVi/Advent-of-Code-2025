package aoc.p007;

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
     * Splits itself into two sub-parts, to the left and to the right
     * of itself.
     * @return The result of a split as a Beam[2] array 
     * with two sub-parts.
     */
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
        return String.format("Beam#%d[%s]", hashCode(), position);
    }
    
}
