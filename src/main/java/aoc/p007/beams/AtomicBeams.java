package aoc.p007.beams;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AtomicBeams implements Beams<Set<Beam>> {
    Set<Beam> beams = new HashSet<>();
    Set<Beam> spawnStorage = new HashSet<>();

    @Override
    public Set<Beam> getBeams() {
        return beams;
    }
    
    @Override
    public Stream<Beam> stream() {
        return beams.stream();
    }

    @Override
    public Collector<Beam, ?, Set<Beam>> getCollector() {
        return Collectors.toSet();
    }

    @Override
    public void update(Set<Beam> updatedBeams) {
        beams = updatedBeams;
    }

    @Override
    public Collection<Beam> getSpawnStorage() {
        return spawnStorage;
    }

    @Override
    public void spawn() {
        beams.addAll(spawnStorage);
        spawnStorage.clear();
    }

    @Override
    public boolean isEmpty() {
        return beams.isEmpty();
    }

}
