package aoc.shared;


/**
 * A basis for unique objects that are assigned some numeric ID. <br>
 * Provides predefined {@code compareTo()}, {@code equals()} and 
 * {@code hashCode()} methods based on the IDs of the objects. <br>
 * It is useful when working with hash and tree collections.
 */
public abstract class Id implements Comparable<Id> {

    /**
     * Retrieves the integer ID of the object that is then used
     * in {@code equals()}, {@code compareTo()} and {@code hashCode()}
     * methods
     * @return
     */
    public abstract int getId();
    
    
    @Override
    public int compareTo(Id o) {
        return Integer.compare(getId(), o.getId());
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        
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
