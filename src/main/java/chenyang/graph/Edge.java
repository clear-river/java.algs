package chenyang.graph;

import chenyang.auxiliary.StdOut;

public class Edge implements Comparable<Edge>{
	private final int v;
	private final int w;
	private final double weight;
	
	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public double weight() {
		return weight;
	}
	
	public int either() {
		return v;
	}
	
	public int other(int vertex) {
		return (vertex == v) ? w : v;
	}
	
	public int compareTo(Edge that) {
		return Double.compare(this.weight, that.weight);
	}
	
	public String toString() {
		return String.format("%d-%d %.2f", v, w, weight);
	}
	
	public static void main(String[] args) {
		Edge e = new Edge(12, 34, 5.67);
		StdOut.println(e);
	}
}
