package chenyang.graph;

import java.util.LinkedList;

import chenyang.auxiliary.In;
import chenyang.auxiliary.StdOut;

public class EdgeWeightedGraph {
	private final int v;
	private int e;
	private LinkedList<Edge>[] adj;
	
	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int v) {
		this.v = v;
		this.e = 0;
		adj = (LinkedList<Edge>[]) new LinkedList<?>[this.v];
		for (int i = 0; i < this.v; i++) {
			adj[i] = new LinkedList<Edge>();
		}
	}
	
	public EdgeWeightedGraph(In in) {
		this(in.readInt());
		int e = in.readInt();
		for (int i = 0; i < e; i++) {
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			addEdge(new Edge(v, w, weight));
		}
	}
	
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].addFirst(e);
		adj[w].addFirst(e);
		this.e ++;
	}
	
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	
	public int degree(int v) {
		return adj[v].size();
	}
	
	public Iterable<Edge> edges(){
		LinkedList<Edge> list = new LinkedList<Edge>();
		for (int i = 0; i < this.v; i++) {
			int selfLoops = 0;
			for (Edge e : adj[i]) {
				if (e.other(v) > v) { list.add(e); }
				if (e.other(v) == v) {
					//2 Edges indicating the same self loop are consecutive
					//due to implementation of func addEdge. Only add the first
					//of them.
					if (selfLoops % 2 == 0) { list.add(e); }
					selfLoops ++;
				}
			}
		}
		return list;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(v + " " + e + System.getProperty("line.separator"));
		for (int i = 0; i < v; i++) {
			s.append(i + ": ");
			for (Edge j : adj[i]) {
				s.append(j + " ");
			}
			s.append(System.getProperty("line.separator"));
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		EdgeWeightedGraph g = new EdgeWeightedGraph(in);
		StdOut.println(g);
	}
}
