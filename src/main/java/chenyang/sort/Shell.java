package chenyang.sort;

public class Shell {
    @SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        int h = 1;
        while (h < n/3) {
        	h = 3*h; 
        }

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h ; j -= h) {
                	if (SortHelper.less(a[j], a[j-h])) {
                		SortHelper.exch(a, j, j-h);
                	}
                }
            }
            //assert isHsorted(a, h); 
            h /= 3;
        }
        //assert SortHelper.isSorted(a);
    }
    
    // is the array h-sorted?
    /*@SuppressWarnings({ "rawtypes", "unused" })
	private static boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (SortHelper.less(a[i], a[i-h])) return false;
        return true;
    }*/
}
