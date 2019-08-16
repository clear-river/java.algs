package chenyang.graph;

import chenyang.auxiliary.StdOut;

public class DirectedEdge {
    private final int fr;
    private final int t;
    private final double weight;
    
    public DirectedEdge(int from, int to, double weight) {
        this.fr = from;
        this.t = to;
        this.weight = weight;
    }
    
    public int from() {
        return fr;
    }

    /**
     * Returns the head vertex of the directed edge.
     * @return the head vertex of the directed edge
     */
    public int to() {
        return t;
    }

    /**
     * Returns the weight of the directed edge.
     * @return the weight of the directed edge
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns a string representation of the directed edge.
     * @return a string representation of the directed edge
     */
    public String toString() {
        return fr + "->" + t + " " + String.format("%5.2f", weight);
    }

    /**
     * Unit tests the {@code DirectedEdge} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        DirectedEdge e = new DirectedEdge(12, 34, 5.67);
        StdOut.println(e);
    }
}
