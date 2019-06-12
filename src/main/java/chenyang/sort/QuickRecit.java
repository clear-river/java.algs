package chenyang.sort;

public class QuickRecit {
	private static int partition(Comparable[] d, int s, int e) {
		int p = s, c = s+1;
		for(; c<=e; c++) {
			if (SortHelper.less(d[c], d[s])) {
				SortHelper.exch(d, ++p, c);
			}
		}
		SortHelper.exch(d, s, p);
		return p;
	}
	
	private static void sort(Comparable[] d, int s, int e) {
		if(e<=s) {return;}
		int p = partition(d, s, e);
		sort(d, s, p-1);
		sort(d, p+1, e);
	}
	
	public static void sort(Comparable[] d) {
		sort(d, 0, d.length-1);
	}
}
