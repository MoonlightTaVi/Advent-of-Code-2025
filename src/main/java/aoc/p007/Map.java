package aoc.p007;

import java.util.Iterator;

public class Map implements Iterable<Position> {
    final char[][] map;
    
    public final int width;
    public final int height;

    public Map(char[][] map) {
        this.map = map;
        height = map.length;
        width = map[0].length;
    }
    
    public boolean isInBoundaries(Position atPosition) {
        int x = Math.max(atPosition.x, 0);
        x = Math.min(x, width - 1);
        int y = Math.max(atPosition.y, 0);
        y = Math.min(y, height - 1);
        
        return atPosition.x == x && atPosition.y == y;
    }
    
    public boolean isEmitter(Position atPosition) {
        return map[atPosition.y][atPosition.x] == 'S';
    }
    
    public boolean isSplitter(Position atPosition) {
        return map[atPosition.y][atPosition.x] == '^';
    }

    @Override
    public Iterator<Position> iterator() {
        return new MapIterator(this);
    }
    
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
