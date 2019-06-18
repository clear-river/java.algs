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
	
	public Value get(Key key) {
		return get(root, key).val;
	}
	
	public Node getNode(Key key) {
		return get(root, key);
	}
	
	private Node get(Node x, Key key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp == 0) { return x; }
			x = (cmp < 0) ? x.left : x.right;
		}
		return null;
	}
	
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
	
	private Node min(Node x) {
		if (x == null) {return x;}
		while (x.left != null) {
			x = x.left;
		}
		return x;
	}
	
	public Key min() {
		return min(root).key;
	}
	
	private Node successor (Node x) {
		if (x == null) {return null;}
		if (x.right != null) {
			return min(x.right);
		}
		Node prt = x.parent;
		while (prt != null && prt.right == x) {
			x = prt;
			prt = x.parent;
		}
		return prt;
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
        
        BST.Node test = st.successor(st.getNode("C"));
        //st.delete("E");

//        for (String s : st.levelOrder())
//            StdOut.print(s + ":" + st.get(s) + " || ");
//
//        StdOut.println();
//
//        for (String s : st.keys())
//            StdOut.print(s + ":" + st.get(s) + " || ");
    }
}
