package aoc.p007.map;

import java.util.Iterator;


/**
 * A 2D grid with fixed defined dimensions. <br>
 * Implements Iterable<Position>, so each cell position in a map
 * can be obtained via iteration.
 */
public class Map implements Iterable<Position> {
    final char[][] map;
    
    public final int width;
    public final int height;

    
    public Map(char[][] map) {
        this.map = map;
        height = map.length;
        width = map[0].length;
    }
    
    
    /**
     * Checks if a position is within the boundaries of the map.
     * @param atPosition Position to check.
     * @return true if the position is within the map, false if
     * the position is outside the map.
     */
    public boolean isInBoundaries(Position atPosition) {
        int x = Math.max(atPosition.x, 0);
        x = Math.min(x, width - 1);
        int y = Math.max(atPosition.y, 0);
        y = Math.min(y, height - 1);
        
        return atPosition.x == x && atPosition.y == y;
    }
    
    /**
     * Checks if a position is an emitter ('S'). <br>
     * An emitter emits beams.
     * @param atPosition Position to check.
     * @return true if an emitter is placed at the position.
     */
    public boolean isEmitter(Position atPosition) {
        return map[atPosition.y][atPosition.x] == 'S';
    }
    
    /**
     * Checks if a position is a splitter ('^'). <br>
     * A splitter splits a beam into two separate beams.
     * @param atPosition Position to check.
     * @return true if a splitter is placed at the position.
     */
    public boolean isSplitter(Position atPosition) {
        return map[atPosition.y][atPosition.x] == '^';
    }

    
    @Override
    public Iterator<Position> iterator() {
        return new MapIterator(this);
    }
    
    
    
    /**
     * Iterates over positions in the grid of a map.
     */
    class MapIterator implements Iterator<Position> {
        final int width;
        final int height;
        
        final Position step = new Position(0, 0);
        
        public MapIterator(Map map) {
            width = map.width;
            height = map.height;
        }

        @Override
        public boolean hasNext() {
            return step.y < height && step.x < width;
        }

        @Override
        public Position next() {
            Position position = new Position(step.x, step.y);
            step.x++;
            if (step.x >= width) {
                step.x = 0;
                step.y++;
            }
            return position;
        }
    }
}
