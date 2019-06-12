package chenyang.sort;

import chenyang.auxiliary.In;

public class SortWrapper {
	    /**
	     * Reads in a sequence of strings from standard input; Shellsorts them; 
	     * and prints them to standard output in ascending order. 
	     *
	     * @param args the command-line arguments
	     */
	    public static void main(String[] args) {
	        //String[] a = In.readStrings(args[0]);
	    	int[] data = new In(args[0]).readAllInts();
	    	Integer[] a = new Integer[data.length];
	    	for (int i = 0; i < data.length; i++) {
	    		a[i] = new Integer(data[i]);
	    	}
	    	SortHelper.show(a);
	    	System.out.println("====================================================");
	        HeapRecit.sort(a);
	        SortHelper.show(a);
	    }
}
