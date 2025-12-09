package aoc.p008.sorting;

import java.util.Comparator;

import aoc.p008.nodes.Node;


public class DistanceComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        return Double.compare(o1.getDist(), o2.getDist());
    }

}
