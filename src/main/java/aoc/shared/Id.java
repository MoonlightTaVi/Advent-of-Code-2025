package aoc.shared;


public abstract class Id {

    public abstract int getId();
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Id id) {
            return getId() == id.getId();
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return getId();
    }
    
}
