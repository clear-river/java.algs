package chenyang.graph;

import chenyang.auxiliary.In;
import chenyang.auxiliary.StdOut;

//TODO: From my point of view, DFS is a method of Graph and should be integrated into Graph.java.
// Other functions may appear that preventing the integration, defer it first until this chapter
// is finished. 6th July, 2019.
public class DepthFirstSearch {
	private boolean[] marked; // marked[v] being true indicates at least one path between s and v.
	private int count;	// number of vertices connected to s.
	
	private void __DepthFirstSearch(Graph G, int v) {
		count ++;
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) { __DepthFirstSearch(G, w); }
		}
	}
	
	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		__DepthFirstSearch(G, s);
	}
	
	public boolean marked(int v) {
		return marked[v];
	}
	
	public int count() {
		return count;
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		int s = Integer.parseInt(args[1]);
		DepthFirstSearch dfs = new DepthFirstSearch(G, s);
		
		for (int v = 0; v < G.V(); v++) {
			if (dfs.marked(v)) {
				StdOut.print(v + " ");
			}
		}
		StdOut.println();
		StdOut.println((dfs.count() == G.V())? "Connected" : "Not Connected");
	}
}
