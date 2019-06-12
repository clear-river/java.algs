package chenyang.sort;

public class MergeRecit {
	private static Comparable[] aux;
	private static void merge(Comparable[] data, int start, int end, int mid) {
		int former = start, latter = mid+1, i = start;
		while (former <= mid && latter <= end) {
			aux[i++] = (SortHelper.less(data[former], data[latter])) ? data[former++] : data[latter++];
		}
		while (former <= mid) {aux[i++] = data[former++];}
		while (latter <=end ) {aux[i++] = data[latter++];}
		for (i = start; i<=end; i++) {
			data[i] = aux[i];
		}
	}
	
	private static void sort(Comparable[] d, int s, int e) {
		if (e <= s) {return;}
		int m = (e+s) >> 1;
		sort(d, s, m);
		sort(d, m+1, e);
		merge(d, s, e, m);
	}
	
	public static void sort(Comparable[] d) {
		aux = new Comparable[d.length];
		sort(d, 0, d.length-1);
	}
}
