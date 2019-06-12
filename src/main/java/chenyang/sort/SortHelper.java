package chenyang.sort;

import chenyang.auxiliary.StdOut;

public class SortHelper {
	   /***************************************************************************
	    *  Helper sorting functions.
	    ***************************************************************************/
	    
	    // is v < w ?
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public static boolean less(Comparable v, Comparable w) {
	        return v.compareTo(w) < 0;
	    }
	    
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public static boolean lessOrEqual(Comparable v, Comparable w) {
	        return v.compareTo(w) <= 0;
	    }
	        
	    // exchange a[i] and a[j]
	    public static void exch(Object[] a, int i, int j) {
	        Object swap = a[i];
	        a[i] = a[j];
	        a[j] = swap;
	    }
	    
	    @SuppressWarnings("rawtypes")
		public static boolean isSorted(Comparable[] a) {
	        for (int i = 1; i < a.length; i++)
	            if (less(a[i], a[i-1])) return false;
	        return true;
	    }

	    // print array to standard output
	    @SuppressWarnings("rawtypes")
		public static void show(Comparable[] a) {
	        for (int i = 0; i < a.length-1; i++) {
	            StdOut.print(a[i]);
	            StdOut.print(',');
	        }
	        StdOut.print(a[a.length-1]);
	        StdOut.print('\n');
	    }
}
