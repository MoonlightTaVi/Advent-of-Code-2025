package aoc.p008;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Circuit {
    final Integer id;
    
    List<Node> boxes = new ArrayList<>();
    
    
    public int size() {
        return boxes.size();
    }
    
}
