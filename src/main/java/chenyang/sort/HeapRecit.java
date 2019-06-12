package chenyang.sort;

public class HeapRecit {
	private static void heapify(Comparable[] d, int r, int s) {
		int mci = 0;
		while(2*r+1 < s) {
			mci = (2*r+2 < s && SortHelper.less(d[2*r+1], d[2*r+2]))? 2*r+2 : 2*r+1;
			if (SortHelper.less(d[mci], d[r])) {return;}
			SortHelper.exch(d, r, mci);
			r = mci;
		}
	}
	
	private static void createHeap(Comparable[] d) {
		int s = d.length;
		for (int i = (s>>1)-1; i>=0; i--) {
			heapify(d, i, s);
		}
	}
	
	public static void sort(Comparable[] d) {
		createHeap(d);
		for( int i = d.length-1; i > 0; i--) {
			SortHelper.exch(d, 0, i);
			heapify(d,0,i);
		}
	}
}
