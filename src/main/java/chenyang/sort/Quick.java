package chenyang.sort;

public class Quick {
	public static void sort(Comparable[] data) {
		sort(data, 0, data.length-1);
	}
	
	private static void sort(Comparable[] data, int start, int end) {
		if (end <= start) {return;}
		int spliter = partition(data, start, end);
		sort(data, start, spliter-1);
		sort(data, spliter+1, end);
	}
	
	private static int partition(Comparable[] data, int start, int end) {
		int pos = start, curr = start+1;
		for(; curr <= end; curr++) {
			if (SortHelper.less(data[start], data[curr])) {
				continue;
			}
			SortHelper.exch(data, ++pos, curr);
		}
		SortHelper.exch(data, pos, start);
		return pos;
	}
}
