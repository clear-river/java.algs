//java DepthFirstOrder tinyDAG.txt
package chenyang.graph;

import java.util.LinkedList;
import java.util.Queue;

public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> preOrder;
	private Queue<Integer> postOrder;
	
	public DepthFirstOrder(Digraph g) {
		marked = new boolean[g.V()];
		preOrder = new LinkedList<Integer>();
		postOrder = new LinkedList<Integer>();
		
		for (int v = 0; v < g.V(); v++) {
			if (marked[v]) { continue; }
			depthFirstSearch(g, v);
		}
	}
	
	private void depthFirstSearch(Digraph g, int v) {
		marked[v] = true;
		preOrder.add(v);
		for (int w : g.adj(v)) {
			if (marked[w]) { continue; }
			depthFirstSearch(g, w);
		}
		postOrder.add(v);
	}
}
