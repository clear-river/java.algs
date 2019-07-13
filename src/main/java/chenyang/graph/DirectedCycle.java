package chenyang.graph;

import java.util.Stack;

import chenyang.auxiliary.In;
import chenyang.auxiliary.StdOut;

public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private boolean[] onStack;
	private Stack<Integer> cycle;
	
	private void dfs(Digraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for (int w: G.adj(v)) {
			//If a cycle is found, stop dfs other vertices.
			if (cycle != null) {return;}
			
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
			else if (onStack[w]) {
				cycle = new Stack<Integer>();
				for (int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}
	
	public DirectedCycle(Digraph G) {
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			
			if (!marked[v] && cycle == null/*If a cycle is found, stop dfs other vertices.*/) {
				dfs(G, v);
			}
		}
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}
	
	public Iterable<Integer> cycle(){
		return cycle;
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		
		DirectedCycle finder = new DirectedCycle(G);
		if (finder.hasCycle()) {
			StdOut.print("Directed cycle:");
			for (int v: finder.cycle()) {
				StdOut.print(v + "-");
			}
			StdOut.println();
		}else {
			StdOut.println("No directed cycle.");
		}
		
	}
}
