package aoc.p008.nodes;

import java.util.Arrays;

import aoc.shared.Id;
import aoc.shared.Vector;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Node extends Id {
    public static int count = 0;

    
    public final int id;
    public final Vector<Integer> vector;
    public int circuitId;
    
    
    public Node(String line) {
        id = count++;
        circuitId = id;
        
        int[] vec = Arrays.
                stream(line.split(","))
                .mapToInt(Integer::valueOf)
                .toArray();
        int i = 0;
        vector = new Vector<>(vec[i++], vec[i++], vec[i++]);
    }


    @Override
    public int getId() {
        return id;
    }
    
}
