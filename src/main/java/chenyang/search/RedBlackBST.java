package chenyang.search;

import chenyang.auxiliary.In;
import chenyang.auxiliary.StdIn;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private Node root = null;
	
	private class Node{
		private Key key;
		private Value val;
		private Node left, right, parent;
		private boolean color;
		private int N;
		
		public Node(Key key, Value val, boolean color) {
			this.key = key;
			this.val = val;
			this.N = 1;
			this.color = color;
		}
	}
	
	private boolean color(Node x) {
		if (x == null) {return BLACK;}
		return x.color;
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
		
		Node n = new Node(key, val, RED);
		if (prt == null) {
			root = n;
			insert_fixup(n);
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
		
		insert_fixup(n);
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
		
		for (Node prt = node2del.parent; prt != null; prt = prt.parent) {
			prt.N = size(prt.left) + size(prt.right) + 1;
		}
		
		if (color(node2del) == BLACK) {
			delete_fixup(child);
		}
	}
	
	private void delete_fixup(Node node) {
		Node bro = null;
		while (node != root && color(node) == BLACK) {
			if (node == node.parent.left) {
				bro = node.parent.right;
				// Case 1: brother.color if red.
				if (color(bro) == RED) {
					bro.color = BLACK;
					node.parent.color = RED;
					left_rotate(node.parent);
					bro = node.parent.right;
				}
				// Case 2: brother.color is black, and color of both its children is BLACK.
				if (color(bro.left) == BLACK && color(bro.right) == BLACK) {
					bro.color = RED;
					node = node.parent;
					continue;
				}
				//Case 3: brother.color is BLACK w/ left child red and right black.
				if (color(bro.right) == BLACK) {
					bro.left.color = BLACK;
					bro.color = RED;
					right_rotate(bro);
					bro = node.parent.right;
				}
				//Case 4: brother.color is BLACK with right child RED.
				bro.color = color(node.parent);
				node.parent.color = bro.right.color = BLACK;
				left_rotate(node.parent);
				node = root;
				break;
			}else {
				//Symmetric with node being left child of its parent.
				bro = node.parent.left;
				if (color(bro) == RED) {
					bro.color = BLACK;
					node.parent.color = RED;
					right_rotate(node.parent);
					bro = node.parent.left;
				}
				if (color(bro.left) == BLACK && color(bro.right) == BLACK) {
					bro.color = RED;
					node = node.parent;
					continue;
				}
				if (color(bro.left) == BLACK) {
					bro.right.color = BLACK;
					bro.color = RED;
					left_rotate(bro);
					bro = node.parent.left;
				}
				bro.color = color(node.parent);
				node.parent.color = bro.left.color = BLACK;
				right_rotate(node.parent);
				node = root;
				break;
			}
		}
		node.color = BLACK;
	}
	
	public void delete(Key key) {
		delete(getNode(key));
	}
	
	private void left_rotate(Node x) {
		Node rch = x.right;
		x.right = rch.left;
		if (rch.left != null) {
			rch.left.parent = x;
		}
		rch.parent = x.parent;
		if (x.parent == null) {
			root = rch;
		}else if(x == x.parent.left) {
			x.parent.left = rch;
		}else {
			x.parent.right = rch;
		}
		
		x.parent = rch;
		rch.left = x;
		x.N = size(x.left) + size(x.right) + 1;
		rch.N = size(rch.left) + size(rch.right) + 1;
	}
	
	private void right_rotate(Node x) {
		Node lch = x.left;
		x.left = lch.right;
		if (lch.right != null) {
			lch.right.parent = x;
		}
		lch.parent = x.parent;
		if (x.parent == null) {
			root = lch;
		}else if(x == x.parent.left) {
			x.parent.left = lch;
		}else {
			x.parent.right = lch;
		}
		lch.right = x;
		x.parent = lch;
		x.N = size(x.left) + size(x.right) + 1;
		lch.N = size(lch.left) + size(lch.right) + 1;
	}
	
	private void insert_fixup(Node n) {
		Node uncle = null;
		System.out.println("fixing up node" + n.key);
		while (color(n.parent) == RED) {
			if (n.parent == n.parent.parent.left) {
				uncle = n.parent.parent.right;
				if (color(uncle) == RED) {
					uncle.color = n.parent.color = BLACK;
					n.parent.parent.color = RED;
					n = n.parent.parent;
					continue;
				}
				if (n == n.parent.right) {
					n = n.parent;
					left_rotate(n);
				}
				n.parent.color = BLACK;
				n.parent.parent.color = RED;
				right_rotate(n.parent.parent);
			} else {
				uncle = n.parent.parent.left;
				if (color(uncle) == RED) {
					uncle.color = n.parent.color = BLACK;
					n.parent.parent.color = RED;
					n = n.parent.parent;
					continue;
				}
				if (n == n.parent.left) {
					n = n.parent;
					right_rotate(n);
				}
				n.parent.color = BLACK;
				n.parent.parent.color = RED;
				left_rotate(n.parent.parent);
			}
		}
		root.color = BLACK;
	}
	
    public static void main(String[] args) { 
        RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
        In input = new In(args[0]);
        for (int i = 0; !input.isEmpty(); i++) {
            String key = input.readString();
            st.insert(key, i);
        }
//        RedBlackBST<String, Integer>.Node n = st.getNode("H");
//        st.left_rotate(n);
//        st.right_rotate(st.root);
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
