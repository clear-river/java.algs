package chenyang.sort;

public class Merge {
	private static Comparable[] aux;

	private static void merge(Comparable[] data, int start, int end, int mid) {
		int former = start, latter = mid + 1, index = start;

		while (former < mid + 1 && latter < end + 1) {
			aux[index++] = (SortHelper.less(data[former], data[latter])) ? data[former++] : data[latter++];
		}

		while (former < mid + 1) {
			aux[index++] = data[former++];
		}

		while (latter < end + 1) {
			aux[index++] = data[latter++];
		}

		for (index = start; index <= end; index++) {
			data[index] = aux[index];
		}
	}

	private static void sort(Comparable[] data, int start, int end) {
		int mid = 0;
		if (start >= end) {
			return;
		}

		mid = (start + end) >> 1;
		sort(data, start, mid);
		sort(data, mid + 1, end);
		merge(data, start, end, mid);
	}

	public static void sort(Comparable[] data) {
		aux = new Comparable[data.length];
		sort(data, 0, data.length - 1);
	}
}
