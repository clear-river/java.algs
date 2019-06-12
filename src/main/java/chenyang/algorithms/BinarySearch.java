package chenyang.algorithms;

import java.util.Arrays;

public class BinarySearch {

	private static rankResult rank(int key, int[] data, int start, int end) {
		if (end < start) {
			return new rankResult(false, end);
		}
		int mid = (start + end) >> 1;
		if (data[mid] == key) {
			return new rankResult(true, mid);
		}
		
		return (data[mid] > key)? rank(key, data, start, mid-1) : rank (key, data, mid+1, end);
	}
	
	public static rankResult rank(int key, int[] data) {
		return rank(key, data, 0, data.length - 1);
	}
	
	// Proved not working as expected.
	public static void testParam(Boolean param) {
		param = new Boolean(!param);
		return;
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

class rankResult{
	public int index;
	public boolean hit;
	
	public rankResult(boolean hit, int index) {
		this.index = index;
		this.hit = hit;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isHit() {
		return hit;
	}
	public void setHit(boolean hit) {
		this.hit = hit;
	}
}