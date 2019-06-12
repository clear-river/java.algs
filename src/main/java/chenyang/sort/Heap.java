package chenyang.sort;

public class Heap {
	private static void heapify(Comparable[] data, int size, int root) {
		int max_child_index = 0;
		while (2*root+1 < size) {
			max_child_index = (2*root+2 < size && SortHelper.less(data[2*root+1], data[2*root+2])) ?
					2*root+2 : 2*root+1;
			if (SortHelper.less(data[max_child_index], data[root])) {
				return;
			}
			SortHelper.exch(data, root, max_child_index);
			root = max_child_index;
		}
	}
	
	private static void createHeap(Comparable[] data) {
		for(int root_index = (data.length >> 1)-1; root_index >= 0; root_index--) {
			heapify(data, data.length, root_index);
		}
	}
	
	public static void sort(Comparable[] data) {
		createHeap(data);
		for(int pos = data.length-1; pos > 0; pos--) {
			SortHelper.exch(data, pos, 0);
			heapify(data, pos, 0);
		}
	}
}
