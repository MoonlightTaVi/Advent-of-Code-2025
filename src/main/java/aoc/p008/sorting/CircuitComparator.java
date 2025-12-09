package aoc.p008.sorting;

import java.util.Comparator;

import aoc.p008.nodes.Node;


public class CircuitComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        return Integer.compare(o1.circuitId, o2.circuitId);
    }

}
