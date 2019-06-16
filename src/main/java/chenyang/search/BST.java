package chenyang.search;

import chenyang.auxiliary.In;
import chenyang.auxiliary.StdIn;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;
	
	private class Node{
		private Key key;
		private Value val;
		private Node left, right, parent;
		private int N;
		
		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node x) {
		if (x == null) {return 0;}
		return x.N;
	}
	
//	public Value get(Key key) {
//		return get(root, key);
//	}
//	
//	private Value get(Node x, Key key) {
//		if (x == null) {
//			return null;
//		}
//		int cmp = key.compareTo(x.key);
//		if (cmp == 0) {
//			return x.val;
//		}
//		return (cmp < 0) ? get(x.left, key) : get(x.right, key);
//	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
		root.parent = null;
	}
	
	private Node put(Node x, Key key, Value val) {
		if (x == null) {
			return new Node(key, val, 1);
		}
		
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key, val);
			x.left.parent = x;
		}
		else if (cmp > 0) {
			x.right = put (x.right, key, val);
			x.right.parent = x;
		}
		else x.val = val;
		
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	

//	
//	private Node floor(Node x, Key key) {
//		if (x == null) {return null;}
//		int cmp = key.compareTo(x.key);
//		if (cmp == 0) {return x;}
//		if (cmp < 0) {return floor(x.left, key);}
//		Node t = floor(x.right, key);
//		return (t != null) ? t : x;
//	}
//	
//	public Key floor(Key key) {
//		Node x = floor(root, key);
//		return (x == null) ? null : x.key;
//	}
//	
//	private Node select(Node x, int k) {
//		if (x == null) {return null;}
//		int t = size(x.left);
//		if (t == k) {return x;}
//		return (t > k) ? select(x.left, k) : select(x.right, k-t-1);
//	}
//	
//	public Key select(int k) {
//		return select(root, k).key;
//	}
//	
//	private int rank(Key key, Node x) {
//		if (x == null) {return 0;}
//		int cmp = key.compareTo(x.key);
//		if (cmp == 0) {return size(x.left);}
//		return (cmp < 0) ? rank(key, x.left) : 1+size(x.left)+rank(key, x.right);
//	}
//	
//	public int rank(Key key) {
//		return rank(key, root);
//	}
//	
	private Node min(Node x) {
		return (x.left == null)? x : min(x.left);
	}
	
	public Key min() {
		return min(root).key;
	}
	
	private Node deleteMin(Node x) {
		if (x.left == null) {return x.right;}
		x.left = deleteMin(x.left);
		x.left.parent = x;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node delete(Node x, Key key) {
		if (x == null) {return null;}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = delete(x.left, key);
			x.left.parent = x;
		}
		else if (cmp > 0) {
			x.right = delete(x.right, key);
			x.right.parent = x;
		}
		else {
			if (x.right == null) {return x.left;}
			if (x.left == null) {return x.right;}
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
			x.right.parent = x.left.parent = x;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void delete(Key key) {
		root = delete(root, key);
		root.parent = null;
	}
    public static void main(String[] args) { 
        BST<String, Integer> st = new BST<String, Integer>();
        In input = new In(args[0]);
        for (int i = 0; !input.isEmpty(); i++) {
            String key = input.readString();
            st.put(key, i);
        }
        
        st.delete("E");

//        for (String s : st.levelOrder())
//            StdOut.print(s + ":" + st.get(s) + " || ");
//
//        StdOut.println();
//
//        for (String s : st.keys())
//            StdOut.print(s + ":" + st.get(s) + " || ");
    }
}
