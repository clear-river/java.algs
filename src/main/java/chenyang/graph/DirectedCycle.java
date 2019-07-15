package chenyang.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import chenyang.auxiliary.In;
import chenyang.auxiliary.StdOut;

public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private boolean[] onStack;
	private Stack<Integer> cycle;
	
	private Queue<Integer> pre;
	private Queue<Integer> post;
	private Stack<Integer> reversePost;
	
	private void dfs(Digraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		
		pre.add(v);
		
		for (int w: G.adj(v)) {
			//If a cycle is found, stop dfs other vertices.
			if (cycle != null) {
				//It's pointless to record these lists in case there're cycles.
				pre = post = null;
				reversePost = null;
				return;
			}
			
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
		
		post.add(v);
		reversePost.push(v);
	}
	
	public DirectedCycle(Digraph G) {
		pre = new LinkedList<Integer>();
		post = new LinkedList<Integer>();
		reversePost = new Stack<Integer>();
		
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
	
	public boolean isDAG() {
		return !hasCycle();
	}
	
	public Iterable<Integer> topologicalOrder(){
		return (isDAG())? reversePost : null;
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
