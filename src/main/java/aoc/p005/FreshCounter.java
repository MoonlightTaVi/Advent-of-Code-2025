package aoc.p005;


/**
 * Counts fresh ingredients.
 */
public class FreshCounter {

    /**
     * Counts only existing IDs that fall into 'fresh' ranges.
     * @param ingredients Source of ingredients.
     * @return Number of fresh ingredients.
     */
    public long count(Ingredients ingredients) {
        long count = 0;
        for (String idStr : ingredients.ids()) {
            long id = Long.parseLong(idStr);
            FreshRange range = ingredients.getClosestRange(id);
            if (range != null && range.isFresh(id)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts all possible ingredients taken from fresh ranges.
     * @param ingredients Source of ingredients.
     * @return Maximal possible number of fresh ingredients.
     */
    public long countAll(Ingredients ingredients) {
        long count = 0;
        for (FreshRange range : ingredients.ranges) {
            count += range.length();
        }
        return count;
    }
    
}
