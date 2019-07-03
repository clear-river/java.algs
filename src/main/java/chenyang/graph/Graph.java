package chenyang.graph;

import java.util.LinkedList;

import chenyang.auxiliary.In;
import chenyang.auxiliary.StdOut;

public class Graph {
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final int Vertices;
	private int Edges;
	private LinkedList<Integer>[] adj;
	
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.Vertices = V;
		this.Edges = 0;
		adj = (LinkedList<Integer>[]) new LinkedList<?>[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new LinkedList<Integer>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Graph(In in) {
		this.Vertices = in.readInt();
		adj = (LinkedList<Integer>[]) new LinkedList<?>[Vertices];
		for (int v = 0; v < Vertices; v++) {
			adj[v] = new LinkedList<Integer>();
		}
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	
	public void addEdge(int v, int w) {
		Edges++;
		adj[v].addFirst(w);
		adj[w].addFirst(v);
	}
	
	public int V() {
		return Vertices;
	}
	
	public int E() {
		return Edges;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public int degree(int v) {
		return adj[v].size();
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(Vertices + " Vertices, " + Edges + " edges " + NEWLINE);
		for (int v = 0; v < Vertices; v++) {
			s.append(v + ": ");
			for (int w: adj[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		StdOut.println(G);
	}
}
