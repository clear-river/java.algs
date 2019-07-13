package chenyang.graph;

import java.util.LinkedList;

import chenyang.auxiliary.In;
import chenyang.auxiliary.StdOut;

public class Digraph {
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final int V;
	private int E;
	private LinkedList<Integer>[] adj;
	private int[] indegree;
	
	@SuppressWarnings("unchecked")
	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		indegree = new int[V];
		adj = (LinkedList<Integer>[]) new LinkedList<?>[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new LinkedList<Integer>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Digraph(In in) {
		this.V = in.readInt();
		indegree = new int[V];
		adj = (LinkedList<Integer>[]) new LinkedList<?>[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new LinkedList<Integer>();
		}
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		indegree[w] ++;
		E++;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public int outdegree(int v) {
		return adj[v].size();
	}
	
	public int indegree(int v) {
		return indegree[v];
	}
	
	public Digraph reverse() {
		Digraph reverse = new Digraph(V);
		for (int v = 0; v < V; v++) {
			for (int w: adj(v)) {
				reverse.addEdge(w, v);
			}
		}
		return reverse;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj(v)) {
				s.append(v + "->" + w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		StdOut.println(new Digraph(in));
	}
}
