package chenyang.graph;

import java.util.Stack;

import chenyang.auxiliary.In;
import chenyang.auxiliary.StdOut;

//TODO: From my point of view, DFS is a method of Graph and should be integrated into Graph.java.
// Other functions may appear that preventing the integration, defer it first until this chapter
// is finished. 6th July, 2019.

public class DepthFirstSearch {
	private boolean[] marked; // marked[v] being true indicates at least one path between s and v.
	private int[] edgeTo; // edge[v] means path from s to v passes vertex edgeTo[v], namely
							// s->...edgeTo[v]->v.
	private int count; // number of vertices connected to s.
	private final int s;

	private void __DepthFirstSearch(Graph G, int v) {
		count++;
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				__DepthFirstSearch(G, w);
			}
		}
	}

	public DepthFirstSearch(Graph G, int s) {
		this.s = s;
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		__DepthFirstSearch(G, s);
	}

	public boolean marked(int v) {
		return marked[v];
	}

	public int count() {
		return count;
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Stack<Integer> pathTo(int v){
		if (!hasPathTo(v)) {return null;}
		Stack<Integer> path = new Stack<Integer>();
		
		//edgeTo[s] is not initialized.
		for (int x = v; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		return path;
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		int s = Integer.parseInt(args[1]);
		DepthFirstSearch dfs = new DepthFirstSearch(G, s);

		for (int v = 0; v < G.V(); v++) {
			if (dfs.hasPathTo(v)) {
				StdOut.printf("%d to %d: ", s, v);
				
				Stack<Integer> path = dfs.pathTo(v);
				StdOut.print(path.pop());
				while(!path.isEmpty()) {
					int x = path.pop();
					StdOut.print("-" + x);
				}
//				for (int x: dfs.pathTo(v)) {
//					StdOut.print((x == s)? x : "-" + x);
//				}
				StdOut.println();
			}else {
				StdOut.printf("%d to %d: not connected.\n", s, v);
			}
		}
		StdOut.println();
		StdOut.println((dfs.count() == G.V()) ? "Gragh Connected" : "Graph Not Connected");
	}
}
