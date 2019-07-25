package chenyang.graph;

import java.util.LinkedList;
import java.util.Queue;

import chenyang.auxiliary.In;
import chenyang.auxiliary.StdOut;

public class KosarajuSCC {
	private boolean[] marked;
	private int[] sccId;
	private int sccCount;
	
	public KosarajuSCC(Digraph g) {
		marked = new boolean[g.V()];
		sccId = new int[g.V()];
		sccCount = 0;
		
		DepthFirstOrder dfo = new DepthFirstOrder(g.reverse());
		for (int v : dfo.reversePost()) {
			if (marked[v]) { continue; }
			dfs(g, v);
			sccCount ++;
		}
	}
	
	private void dfs(Digraph g, int v) {
		marked[v] = true;
		sccId[v] = sccCount;
		for (int w: g.adj(v)) {
			if (marked[w]) { continue; }
			dfs(g, w);
		}
	}
	
	public int sccCount() {
		return sccCount;
	}
	
	public int sccId(int v) {
		return sccId[v];
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph g = new Digraph(in);
		KosarajuSCC scc = new KosarajuSCC(g);
		
		StdOut.println(scc.sccCount() + " strongly connected components detected.");
		
		Queue<Integer>[] components = (Queue<Integer>[]) new LinkedList<?>[scc.sccCount()];
		for (int i = 0; i < scc.sccCount(); i ++) {
			components[i] = new LinkedList<Integer>();
		}
		
		for (int i = 0; i < g.V(); i ++) {
			components[scc.sccId(i)].add(i);
		}
		
		for (int i = 0; i < scc.sccCount(); i++) {
			for (int j : components[i]) {
				StdOut.print(" " + j);
			}
			StdOut.println();
		}
	}
}
