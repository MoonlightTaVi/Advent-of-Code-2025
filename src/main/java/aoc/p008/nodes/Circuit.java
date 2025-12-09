package aoc.p008.nodes;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Circuit {
    final Integer id;
    
    List<Node> boxes = new ArrayList<>();
    
    
    public void add(Node box) {
        boxes.add(box);
    }
    
    public int size() {
        return boxes.size();
    }
    
}
