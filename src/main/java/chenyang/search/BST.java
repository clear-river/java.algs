package chenyang.search;

import chenyang.auxiliary.In;
import chenyang.auxiliary.StdIn;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root = null;
	
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
	
	public void insert(Key key, Value val) {
		Node current = root, prt = null;
		int cmp = -1;
		while (current != null) {
			cmp = key.compareTo(current.key);
			if (cmp == 0) {
				current.val = val;
				return;
			}
			prt = current;
			current = (cmp < 0) ? current.left : current.right;
		}
		
		Node n = new Node(key, val, 1);
		if (prt == null) {
			root = n;
			return;
		}
		
		n.parent = prt;
		if (cmp < 0) {
			prt.left = n;
		}else {
			prt.right = n;
		}
		
		while (prt != null) {
			prt.N ++;
			prt = prt.parent;
		}
		
		return;
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
	
	private void delete(Node x) {
		if (x == null) {return;}
		Node node2del = x, child = null;
		if (x.left != null && x.right != null) {
			node2del = successor(x);
		}
		child = (node2del.left == null) ? node2del.right : node2del.left;
		if (node2del.parent == null) {
			root = child;
		}else {
			if (node2del == node2del.parent.left) {
				node2del.parent.left = child;
			}else {
				node2del.parent.right = child;
			}
		}
		if (child != null) {
			child.parent = node2del.parent;
		}
		if (node2del != x) {
			x.key = node2del.key;
			x.val = node2del.val;
		}
		
		Node prt = node2del.parent;
		for (;prt != null; prt = prt.parent) {
			prt.N = size(prt.left) + size(prt.right) + 1;
		}
	}
	
	public void delete(Key key) {
		delete(getNode(key));
	}
	
    public static void main(String[] args) { 
        BST<String, Integer> st = new BST<String, Integer>();
        In input = new In(args[0]);
        for (int i = 0; !input.isEmpty(); i++) {
            String key = input.readString();
            st.insert(key, i);
        }
        
        st.successor(st.root);
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
