package chenyang.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import chenyang.auxiliary.In;
import chenyang.auxiliary.StdOut;

public class BreadthFirstPaths {
	private final int src;
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	
	public BreadthFirstPaths(Graph g, int s) {
		src = s;
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		distTo = new int[g.V()];
		bfs(g, src);
	}
	
	private void bfs(Graph g, int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int v = 0; v < g.V(); v++) {
			distTo[v] = INFINITY;
		}
		distTo[s] = 0;
		marked[s] = true;
		q.add(s);
		
		while (!q.isEmpty()) {
			int v = q.remove();
			for (int w : g.adj(v)) {
				if (marked[w]) {continue;}
				edgeTo[w] = v;
				distTo[w] = distTo[v] + 1;
				marked[w] = true;
				q.add(w);
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public int distTo(int v) {
		return distTo[v];
	}
	
	public Stack<Integer> pathTo(int v){
		if (!hasPathTo(v)) {return null;}
		Stack<Integer> path = new Stack<Integer>();
		int x;
		for (x = v; distTo(x) != 0; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(x);
		return path;
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph g = new Graph(in);
		
		int s = Integer.parseInt(args[1]);
		BreadthFirstPaths bfs = new BreadthFirstPaths(g, s);
		
		for(int v = 0; v < g.V(); v++) {
			if (bfs.hasPathTo(v)) {
				StdOut.printf("%d to %d(%d): ", s, v, bfs.distTo(v));
				Stack<Integer> path = bfs.pathTo(v);
				StdOut.print(path.pop());
				while (!path.isEmpty()) {
					StdOut.print("-" + path.pop());
				}
				StdOut.println();
			}else {
				StdOut.printf("%d to %d is not connected.", s, v);
			}
		}
		
		
	}
}
