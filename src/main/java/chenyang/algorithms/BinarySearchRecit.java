package chenyang.algorithms;

import java.util.Arrays;

public class BinarySearchRecit {
	
	private static rankResult rank(int[] d, int key, int s, int e) {
		if (e<s) {return new rankResult(false, e);}
		int m = (e+s) >> 1;
		if (d[m] == key) {return new rankResult(true, m);}
		return (d[m] > key)? rank(d, key, s, m-1) : rank(d, key, m+1, e);
	}
	
	private static rankResult rank(int key, int[] d) {
		return rank(d, key, 0, d.length-1);
	}
	
	public static void main(String[] args) {
		int[] data = {50, 99, 13, 159, 166, 77, 85};
		Arrays.sort(data);
		int key = 77;
		rankResult result = rank(key, data);
		System.out.println("key:" + key + " result: " + result.isHit() + " index: " + result.getIndex());
		
		key = 78;
		result = rank(key, data);
		System.out.println("key:" + key + " result: " + result.isHit() + " index: " + result.getIndex());
	}
}

//class rankResult{
//	public int index;
//	public boolean hit;
//	
//	public rankResult(boolean hit, int index) {
//		this.index = index;
//		this.hit = hit;
//	}
//	
//	public int getIndex() {
//		return index;
//	}
//	public void setIndex(int index) {
//		this.index = index;
//	}
//	public boolean isHit() {
//		return hit;
//	}
//	public void setHit(boolean hit) {
//		this.hit = hit;
//	}
//}