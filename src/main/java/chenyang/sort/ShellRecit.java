package chenyang.sort;

public class ShellRecit {
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] d) {
		int h = 1;
		while (3*h < d.length) {
			h = 3*h+1;
		}
		while (h >= 1) {
			for (int i = h; i < d.length; i++) {
				for (int j = i; j >= h; j--) {
					if (SortHelper.less(d[j], d[j-h])) {
						SortHelper.exch(d, j, j-h);
					}
				}
			}
			h = h/3;
		}
	}
}
