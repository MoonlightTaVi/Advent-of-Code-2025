package aoc.shared;

import java.util.Arrays;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;


/**
 * Binary search engine that is used to find the desired 
 * (nearest left, nearest right, nearest) item inside any array,
 * using any object as the search item. 
 * <br> <br>
 * The search item and the array
 * items may be of different types. 
 * <br> <br>
 * Uses integer comparison for the search.
 * <br> <br>
 * Uses two Functions (for the search item and the array items) 
 * that map the desired objects to integer value.
 * <br> <br>
 * The array is supposed to be sorted before searching.
 * @param <T> Type of the search item.
 * @param <V> Type of the array items.
 * @see #sort(Object[])
 */
@RequiredArgsConstructor
public class BinarySearch<T, V> {
    final Function<T, Integer> castT;
    final Function<V, Integer> castV;
    
    
    /**
     * Sorts the <V> array in ascending order, prepares it for search.
     * @param array Array of items.
     */
    public void sort(V[] array) {
        Arrays.sort(
                array, 
                (x, y) -> Integer.compare(
                        castV.apply(x), 
                        castV.apply(y)
                        )
                );
    }
    
    
    /**
     * Find the nearest array item that is <b>lower</b> that the search item.
     * @param item Item to search.
     * @param array Array to search in.
     * @return The highest ID inside the array that corresponds to:
     * the array item < search item.
     */
    public int castLeft(T item, V[] array) {
        return cast(item, array, -1);
    }
    
    /**
     * Find the nearest array item that is <b>higher</b> that the search item.
     * @param item Item to search.
     * @param array Array to search in.
     * @return The lowest ID inside the array that corresponds to:
     * the array item > search item.
     */
    public int castRight(T item, V[] array) {
        return cast(item, array, 1);
    }
    
    /**
     * Find the nearest array item for this the search item 
     * (may be both lower or higher).
     * 
     * @param item Item to search.
     * @param array Array to search in.
     * @return The ID of the array item that that is the nearest
     * to the search item.
     */
    public int findNearest(T item, V[] array) {
        return cast(item, array, 0);
    }
    
    
    /**
     * Cast a directional ray.
     * @see #castLeft(Object, Object[])
     * @see #castRight(Object, Object[])
     * @see #findNearest(Object, Object[])
     */
    public int cast(T item, V[] array, int dx) {
        int left = 0;
        int right = array.length;
        
        int i = 0;
        int compared = 0;
        while (right - left > 1) {
            i = left + (right - left) / 2;
            
            V arrayItem = array[i];
            compared = Integer.compare(castV.apply(arrayItem), castT.apply(item));
            // Array item is to the right of the item
            if (compared > 0) {
                right = i;
                continue;
            }
            // Array item is the same or to the left
            left = i;
        }
        
        // dx < 0 - find the one that is lower
        // dx > 0 - find the one that is higher
        // dx == 0 - find the closest
        // The result is the greatest closest - get the previous
        if (compared >= 0 && dx < 0) {
            i--;
        } 
        // The result is the lowest closest - get the next
        else if (compared <= 0 && dx > 0) {
            i++;
        }
        return i;
    }
}
