package aoc.p007;

import java.util.Collection;
import java.util.stream.Collector;

import aoc.p007.map.Map;
import aoc.p007.map.Position;


/**
 * A beam engine that handles beam spawn and dispose logic 
 * and simulates their life cycle.
 */
public class Engine {
    final Map map;
    
    /** @see #getSplits() */
    private long totalSplits = 0;
    /** @see #getKills() */
    private long totalKills = 0;
    
    
    /**
     * Initializes beam engine. 
     * The engine must first be {@code start()}'ed.
     * @param map
     * @see #start()
     */
    public Engine(Map map) {
        this.map = map;
    }
    
    
    public void start(Collection<Beam> beams) {
        for (Position position : map) {
            if (map.isEmitter(position)) {
                Beam beam = new Beam(position);
                beams.add(beam);
            }
        }
    }
    
    public <T extends Collection<Beam>> T run(T beams, Collection<Beam> spawnStorage, Collector<Beam, ?, T> collector) {
        // The hash codes of the Beams change after movement,
        //  so a new HashSet is created
        beams = beams.stream()
                .peek(Beam::moveDown)
                .filter(this::checkOutOfBounds)
                .filter(beam -> checkSplit(beam, spawnStorage))
                .collect(collector);
        
        beams.addAll(spawnStorage);
        
        return beams;
    }
    
    /**
     * Returns current total number of beam splits. <br>
     * The number is incremented (by 1) when a beam hits a splitter
     * during its movement.
     * @return Total number of splits that happened.
     */
    public long getSplits() {
        return totalSplits;
    }
    
    /**
     * Returns current total number of beam that moved outside the map. 
     * <br>
     * The number is incremented (by 1) when a beam hits the boundary
     * of the map.
     * @return Total number of splits that happened.
     */
    public long getTotalKills() {
        return totalKills;
    }
    
    /**
     * Checks if a beam is within the map. <br>
     * Is used for filtering purposes.
     * @param beam A beam to test.
     * @return true if the beam is still within the map, false if the
     * beam moved outside the map and should be removed.
     */
    private boolean checkOutOfBounds(Beam beam) {
        if (map.isInBoundaries(beam.position)) {
            return true;
        }
        totalKills++;
        return false;
    }
    
    private boolean checkSplit(Beam beam, Collection<Beam> spawnStorage) {
        if (!map.isSplitter(beam.position)) {
            return true;
        }
        totalSplits++;
        
        spawnStorage.add(beam.split());
        
        return true;
    }
    
}
