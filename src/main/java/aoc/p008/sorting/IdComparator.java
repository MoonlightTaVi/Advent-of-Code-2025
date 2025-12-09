package aoc.p008.sorting;

import java.util.Comparator;

import aoc.p008.nodes.Node;


public class IdComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        return Integer.compare(o1.id, o2.id);
    }

}
