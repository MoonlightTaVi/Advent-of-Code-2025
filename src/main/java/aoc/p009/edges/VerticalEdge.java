package aoc.p009.edges;


public class VerticalEdge {

    public final int x;
    public final int y1;
    public final int y2;
    
    public VerticalEdge(int x, int y1, int y2) {
        this.x = x;
        this.y1 = Math.min(y1,  y2);
        this.y2 = Math.max(y1,  y2);
    }
    
    
}
