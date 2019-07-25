//java DepthFirstOrder tinyDAG.txt
package chenyang.graph;

import java.util.Deque;
import java.util.LinkedList;

import chenyang.auxiliary.In;
import chenyang.auxiliary.StdOut;

public class DepthFirstOrder {
	private boolean[] marked;
	//private Queue<Integer> preOrder;
	private Deque<Integer> reversePostOrder;
	
	public DepthFirstOrder(Digraph g) {
		marked = new boolean[g.V()];
		//preOrder = new LinkedList<Integer>();
		reversePostOrder = new LinkedList<Integer>();
		
		for (int v = 0; v < g.V(); v++) {
			if (marked[v]) { continue; }
			depthFirstSearch(g, v);
		}
	}
	
	private void depthFirstSearch(Digraph g, int v) {
		marked[v] = true;
		//preOrder.add(v);
		for (int w : g.adj(v)) {
			if (marked[w]) { continue; }
			depthFirstSearch(g, w);
		}
		reversePostOrder.addFirst(v);
	}
	
	public Iterable<Integer> reversePost(){
		return reversePostOrder;
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph g = new Digraph(in);
		for (int i : (new DepthFirstOrder(g).reversePost())) {
			StdOut.print(" " + i);
		}
	}
}
