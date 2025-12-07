package aoc.p005;


public class FreshCounter {

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
    
}
