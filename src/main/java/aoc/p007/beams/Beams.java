package aoc.p007.beams;

import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Stream;

public interface Beams<T extends Collection<Beam>> {

    public T getBeams();
    public boolean isEmpty();
    public Stream<Beam> stream();
    public Collector<Beam, ?, T> getCollector();
    public void update(T updatedBeams);
    public Collection<Beam> getSpawnStorage();
    public void spawn();
    
}
