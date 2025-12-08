package aoc.p007;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    /** All currently existent beams. */
    private Set<Beam> beams = new HashSet<>();
    /** Temporary hub for newly spawned beams. */
    private final Set<Beam> beamSpawner = new HashSet<>();
    
    
    /**
     * Initializes beam engine. 
     * The engine must first be {@code start()}'ed.
     * @param map
     * @see #start()
     */
    public Engine(Map map) {
        this.map = map;
    }
    
    
    /**
     * Spawns beams at their initial positions on the map.
     * @see #isRunning()
     * @see #run()
     */
    public void start() {
        for (Position position : map) {
            if (map.isEmitter(position)) {
                Beam beam = new Beam(position);
                beams.add(beam);
            }
        }
    }
    
    /**
     * Check if the engine is running.
     * @return true if at least one beam is existent inside the engine.
     */
    public boolean isRunning() {
        return !beams.isEmpty();
    }
    
    /**
     * Performs movement and splitting logic upon all existent beams.
     */
    public void run() {
        // The hash codes of the Beams change after movement,
        //  so a new HashSet is created
        beams = beams.stream()
                .peek(Beam::moveDown)
                .filter(this::checkOutOfBounds)
                .filter(this::checkSplit)
                .collect(Collectors.toSet());
        update();
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
        return false;
    }
    
    /**
     * Checks if a beam should not be split (and removed). <br>
     * Is used for filtering purposes.
     * @param beam A beam to test.
     * @return true if there's no splitter at the map grid position
     * of the beam, false if the beam must be split and removed.
     */
    private boolean checkSplit(Beam beam) {
        if (!map.isSplitter(beam.position)) {
            return true;
        }
        totalSplits++;
        
        beamSpawner.add(beam.split());
        
        return true;
    }
    
    /**
     * Spawns newly instantiated beams after splitting.
     * @see #beamSpawner
     */
    private void update() {
        beams.addAll(beamSpawner);
        beamSpawner.clear();
    }
    
}
