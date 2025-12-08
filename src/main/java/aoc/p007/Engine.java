package aoc.p007;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class Engine {
    long totalSplits = 0;
    
    final Map map;
    private Set<Beam> beams = new HashSet<>();
    final Set<Beam> beamSpawner = new HashSet<>();
    //final Set<Beam> beamGarbage = new HashSet<>();
    
    
    public Engine(Map map) {
        this.map = map;
    }
    
    public void start() {
        for (Position position : map) {
            if (map.isEmitter(position)) {
                Beam beam = new Beam(position);
                beams.add(beam);
            }
        }
    }
    
    public boolean isRunning() {
        return !beams.isEmpty();
    }
    
    public void run() {
        beams = beams.stream().peek(Beam::moveDown).filter(this::checkOutOfBounds).filter(this::checkSplit).collect(Collectors.toSet());
        tearDown();
    }
    
    public long getSplits() {
        return totalSplits;
    }
    
    private boolean checkOutOfBounds(Beam beam) {
        if (map.isInBoundaries(beam.position)) {
            return true;
        }
        //beamGarbage.add(beam);
        return false;
    }
    
    private boolean checkSplit(Beam beam) {
        if (!map.isSplitter(beam.position)) {
            return true;
        }
        totalSplits++;
        for (Beam split : beam.split()) {
            beamSpawner.add(split);
        }
        //System.out.println(beam);
        //beamGarbage.add(beam);
        return false;
    }
    
    private void tearDown() {
        //beams.removeAll(beamGarbage);
        //beamGarbage.clear();
        
        beams.addAll(beamSpawner);
        beamSpawner.clear();
    }
    
}
