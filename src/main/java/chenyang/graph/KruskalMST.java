package chenyang.graph;

import java.util.LinkedList;
import java.util.Queue;

import chenyang.algorithms.WeightedQuickUnionUF;
import chenyang.auxiliary.In;
import chenyang.auxiliary.MinPQ;
import chenyang.auxiliary.StdOut;

public class KruskalMST {
	
	private double weight;						// weight of MST
	private Queue<Edge> mst = new LinkedList<Edge>();  // edges in MST
	
	public KruskalMST(EdgeWeightedGraph G) {
		// more efficient to build heap by passing array of edges
		MinPQ<Edge> pq = new MinPQ<Edge>();
		LinkedList<Edge> list = (LinkedList<Edge>) G.edges();
		for (Edge e : list) {
			pq.insert(e);
		}

		// run greedy algorithm
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(G.V());
		while (!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (!uf.connected(v, w)) { // v-w does not create a cycle
				uf.union(v, w);  // merge v and w components
				mst.add(e);  // add edge e to mst
				weight += e.weight();
			}
		}
	}
	
	public Iterable<Edge> edges() {
		return mst;
	}


	public double weight() {
		return weight;
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		
		StdOut.println(G);
		StdOut.println();
		
		KruskalMST mst = new KruskalMST(G);
		for (Edge e : mst.edges()) {
			StdOut.println(e);
		}
		StdOut.printf("%.2f\n", mst.weight());
	}
}
