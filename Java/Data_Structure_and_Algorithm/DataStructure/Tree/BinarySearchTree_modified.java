// Binary Search Tree with some tweak.
// In general, counting the number of nodes that are less than 
// or equal to x takes O(N) traversing in in-order until x. 
// But this BST takes O(log N) to get the number.

// Author: CtCI 10.10 p.413 + kei
// Date  : November 6, 2016

package whiteboard;


public class ForCopy {
	
	// Prepare for the root of a tree. 
	// You can hold the root like this. 
	public static BSTNodeWithLeftSize root = null;

	public static void main(String[] args) {

		
		int[] array = new int[]{ 10, 4, 15, 1, 6, 0, 12, 20, 14 };
		
		// Build a binary search tree. 
		//         10
		//        /   \
		//       4     15 
		//      / \   /  \ 
		//     1   6 12   20
		//    /        \
		//   0          14 
		for (int e : array) {
			buildBST(e);
		}
		
		// For test. 
		inorder(root);
		System.out.println(); // 0 1 4 6 10 12 14 15 20
		
		// Test countNodesLessThan()
		int count = countNodesLessThan(14);
		System.out.println(count); // 6
		
		
		
		
		System.out.println();
		root = null;
		array = new int[]{ 10, 4, 15, 1, 6, 0, 12, 20, 12 };
		
		// Build a binary search tree. 
		//         10
		//        /   \
		//       4     15 
		//      / \   /  \ 
		//     1   6 12   20
		//    /      / 
		//   0     12
		for (int e : array) {
			buildBST(e);
		}
		
		// For test. 
		inorder(root);
		System.out.println(); // 0 1 4 6 10 12 12 15 20
		
		// Test countNodesLessThan()
		count = countNodesLessThan(12);
		System.out.println(count); // 6

		
		

		System.out.println("done.");
		return;
	} // end main().
	
	
	public static void buildBST(int d) {
		if (root == null) {
			root = new BSTNodeWithLeftSize(d);
		} else {
			root.insert(d);
		}
	}
	
	public static int countNodesLessThan(int d) {
		return root.countNodesLessThan(d);
	}
	
	
	
	
	
	// For test. 
	public static void inorder(BSTNodeWithLeftSize n) {
		if (n == null) {
			return;
		}
		
		inorder(n.left);
		System.out.println(n.data);
		inorder(n.right);
	}

}


// You can create a tree node like this. 
// The fields and methods that manipulate them are in the 
// same class. 
class BSTNodeWithLeftSize {
	// For simplicity, all public. 
	public int leftSize = 0;
	public BSTNodeWithLeftSize left, right;
	public int data = 0;

	public BSTNodeWithLeftSize(int d) {
		data = d;
	}

	// When inserting a node, this stores the size of its
	// left subtree. O(log N)
	public void insert(int d) {
		if (d <= data) {
			if (left != null) {
				left.insert(d);
			} else {
				left = new BSTNodeWithLeftSize(d);
			}
			// Kind of memoization? ;)
			leftSize++;
		} else {
			if (right != null) {
				right.insert(d);
			} else {
				right = new BSTNodeWithLeftSize(d);
			}
		}
	} // end insert()
	
	// Count the number of nodes that are less than 
	// or equal to x (not including x itself). O(log N) 
	public int countNodesLessThan(int d) {
		if (d == data) {
			return leftSize;
		} else if (d < data) {
			if (left == null) {
				// Not found. 
				return -1;
			} else {
				return left.countNodesLessThan(d);
			}
		} else {
			// d > data
			if (right == null) {
				// Not found.
				return -1;
			} else {
				return leftSize
						+ 1 
						+ right.countNodesLessThan(d);
			}				
		}
	} // end countNodesLessThan(...)
	
} // end class BSTNodeWithLeftSize
































