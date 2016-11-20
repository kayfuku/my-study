// Example to use Binary Search Tree.
// Warning! In eclipse, you need to open BinarySearchTree_usage 
// for getting class SimpleTreeNode.
// Author: kei

package whiteboard;

import java.util.HashMap;
import java.util.LinkedList;

public class BinarySearchTree_usage2 {

	// Warning! In eclipse, you need to open BinarySearchTree_usage 
	// for getting class SimpleTreeNode.

	public static void main(String[] args) {
		
		SimpleBinarySearchTree2 bst = new SimpleBinarySearchTree2();

		// Push data into BST.
		SimpleTreeNode bstNode = buildBinarySearchTree(bst);
		// Push data into BST in worst case. 
		SimpleTreeNode bstNodeW = buildBinarySearchTreeW(bst);
		// Push data into BST with parent link.
		SimpleTreeNodeP bstNodeP = buildBinarySearchTreeP(bst);
		
		

		// Test coverP(). 
//		boolean b = coverP(bstNodeP, bst.mapP.get(4));
//		System.out.println(b); // true
//		b = coverP(bstNodeP, bst.mapP.get(14));
//		System.out.println(b); // true
//		b = coverP(bstNodeP, bst.mapP.get(6));
//		System.out.println(b); // true
//		b = coverP(bstNodeP, bst.mapP.get(8));
//		System.out.println(b); // false
		
		// Test address and parent.
//		System.out.println(getAddressP(bst, 6).data); // 6
//		System.out.println(getAddressP(bst, 1).parent.data); // 3
//		System.out.println(getAddressP(bst, 6).parent); // null

		// Test getMin()
//		int min = bst.getMin(bstNode);
//		//System.out.println(min); // 1
//		min = bst.getMin(bstNodeW);
//		//System.out.println(min); // 1
		
		// Test levelOrder()
//		bst.levelOrder(bstNode, 14); // 6 3 12 1 4 9 14 Found. 
//		bst.levelOrder(bstNode, 1); // 6 3 12 1 Found. 
//		bst.levelOrder(bstNode, 0); // 6 3 12 1 4 9 14 Not found.

		// Test preorderIter() 
//		bst.preorderIter(bstNode); // 6 3 1 4 12 9 14 
		
		// Test inorder2() 
		//bst.inorder2(bstNode); // 1 3 4 6 9 12 14

		// Test countNodes(), countN().
		int c = bst.countNodes(bstNode);
		System.out.println(c); // 7
		c = bst.countNodes(bstNodeW);
		System.out.println(c); // 3
		c = bst.countN(bstNode);
		System.out.println(c); // 7
		c = bst.countN(bstNodeW);
		System.out.println(c); // 3

		
		
		
		
		System.out.println();
		System.out.println("done.");		
	}
	
	// Build Binary Search Tree.
	//
	//                   6
	//                  /  \
	//                 3    12
	//                / \   / \
	//               1   4 9  14
	// Author: kei
	// Date  : September 24, 2016
	public static SimpleTreeNode buildBinarySearchTree(
			SimpleBinarySearchTree2 bst) {
		SimpleTreeNode tNode = null;

		tNode = bst.insert(tNode, 6);
		tNode = bst.insert(tNode, 3);
		tNode = bst.insert(tNode, 12);
		tNode = bst.insert(tNode, 1);
		tNode = bst.insert(tNode, 9);
		tNode = bst.insert(tNode, 4);
		tNode = bst.insert(tNode, 14);
		//tNode = bst.insert(tNode, 25);
		//tNode = bst.insert(tNode, 39); // With all above, not balanced.
		
		return tNode;
	}
	
	// Build Binary Search Tree such that each node has a link to its parent.
	// Author: kei
	// Date  : October 23, 2016
	public static SimpleTreeNodeP buildBinarySearchTreeP(
			SimpleBinarySearchTree2 bst) {
		SimpleTreeNodeP tNodeP = null;

		tNodeP = bst.insertP(tNodeP, 6);
		tNodeP = bst.insertP(tNodeP, 3);
		tNodeP = bst.insertP(tNodeP, 12);
		tNodeP = bst.insertP(tNodeP, 1);
		tNodeP = bst.insertP(tNodeP, 9);
		tNodeP = bst.insertP(tNodeP, 4);
		tNodeP = bst.insertP(tNodeP, 14);
		//tNodeP = bst.insertP(tNodeP, 25);
		//tNodeP = bst.insertP(tNodeP, 39); // With all above, not balanced.
		
		return tNodeP;
	}
	
	// Build Binary Search Tree.
	//
	//                   6
	//                  /  
	//                 3    
	//                /
	//               1
	// Author: kei
	// Date  : September 24, 2016
	public static SimpleTreeNode buildBinarySearchTreeW(
			SimpleBinarySearchTree2 bst) {
		SimpleTreeNode tNode = null;

		tNode = bst.insert(tNode, 6);
		tNode = bst.insert(tNode, 3);
		tNode = bst.insert(tNode, 1);
		
		return tNode;
	}

	
	// Get node address from node data.
	// Author: kei
	// Date  : October 23, 2016
	public static SimpleTreeNodeP getAddressP(SimpleBinarySearchTree2 bst, int key) {
		return bst.mapP.get(key);
	}
	public static SimpleTreeNode getAddress(SimpleBinarySearchTree2 bst, int key) {
		return bst.map.get(key);
	}
	


	
	
	
	
	
}

class SimpleBinarySearchTree2 {
	
	// For getting tree node address. 
	HashMap<Integer, SimpleTreeNode> map = new HashMap<Integer, SimpleTreeNode>();
	HashMap<Integer, SimpleTreeNodeP> mapP = new HashMap<Integer, SimpleTreeNodeP>();
	
	public SimpleBinarySearchTree2() {
	}
	
	// Insert. O(log N)
	// Assume that left < mid <= right.
	// Author: kei
	// Date  : September 24, 2016
	public SimpleTreeNode insert(SimpleTreeNode tNode, int newData) {
		// Base case.
		if (tNode == null) {
			tNode = new SimpleTreeNode(newData);
			map.put(newData, tNode);
			return tNode;			
		}
		
		// Recurse.
		if (newData < tNode.data) {
			// Search left subtree.
			tNode.left = insert(tNode.left, newData);
			return tNode;
		} else {
			// newData >= tNode.data
			// Search right subtree.
			tNode.right = insert(tNode.right, newData);
			return tNode;
		}
	}
	
	// Insert nodes with link to their parent. O(log N)
	// Assume that left < mid <= right.
	// Author: kei
	// Date  : October 23, 2016
	public SimpleTreeNodeP insertP(SimpleTreeNodeP tNode, int newData) {
		// Base case.
		if (tNode == null) {
			tNode = new SimpleTreeNodeP(newData);
			mapP.put(newData, tNode);
			return tNode;			
		}
		
		// Recurse.
		if (newData < tNode.data) {
			// Search left subtree.
			tNode.left = insertP(tNode.left, newData);
			tNode.left.parent = tNode;
			return tNode;
		} else {
			// newData >= tNode.data
			// Search right subtree.
			tNode.right = insertP(tNode.right, newData);
			tNode.right.parent = tNode;
			return tNode;
		}
	}
	
	// In-order traversal. O(N)
	// Author: CtCI 6th p.103 + kei
	// Date  : September 24, 2016
	public void inorder(SimpleTreeNode tNode) {
		if (tNode != null) {	
			// In-order traversal.
			inorder(tNode.left);
			System.out.println(tNode.data);
			inorder(tNode.right);
		}
	}
	
	// In-order traversal. O(N)
	// Author: PIE p.70 + kei
	// Date  : October 31, 2016
	public void inorder2(SimpleTreeNode tNode) {
		if (tNode == null) { return; }	
		// In-order traversal.
		inorder(tNode.left);
		System.out.println(tNode.data);
		inorder(tNode.right);
	}

	// Pre-order traversal. O(N)
	// Author: CtCI 6th p.103 + kei
	// Date  : September 24, 2016
	public void preorder(SimpleTreeNode tNode) {
		if (tNode != null) {	
			// Pre-order traversal.
			System.out.println(tNode.data);
			preorder(tNode.left);
			preorder(tNode.right);
		}
	}
	
	// Pre-order traversal. Iterative version. O(N)
	// Author: PIE p.72 + kei
	// Date  : October 31, 2016
	public void preorderIter(SimpleTreeNode tNode/* , int key */) {
	    LinkedList<SimpleTreeNode> stack = new LinkedList<SimpleTreeNode>();

	    stack.push(tNode);

	    while (!stack.isEmpty()) {
	        SimpleTreeNode node = stack.poll();
			System.out.println(node.data);

//	        if (node.data == key) {
//	            // Found.
//				System.out.println("Found.");
//	            return;
//	        }
	        
	        // Add all the children of the node to stack.
			// Note that right child is first, then left. 
			if (node.right != null) { stack.push(node.right); }
			if (node.left != null) { stack.push(node.left); } 
	    } // end while (...)

//	    // Not found.
//		System.out.println("Not found.");
//	    return;		
	}
	
	// Post-order traversal. O(N)
	// Author: CtCI 6th p.103 + kei
	// Date  : September 24, 2016
	public void postorder(SimpleTreeNode tNode) {
		if (tNode != null) {	
			// Post-order traversal.
			postorder(tNode.left);
			postorder(tNode.right);
			System.out.println(tNode.data);
		}
	}
	
	// Level-order traversal. (BFS) O(N)
	// Author: kei
	// Date  : October 31, 2016
	public void levelOrder(SimpleTreeNode tNode, int key) {
		// No need to store nodes that has been visited. 
	    //Set<SimpleTreeNode> visited = new HashSet<SimpleTreeNode>();
	    LinkedList<SimpleTreeNode> queue = new LinkedList<SimpleTreeNode>();

	    queue.add(tNode);

	    while (!queue.isEmpty()) {
	        SimpleTreeNode node = queue.poll();
			System.out.println(node.data);

			// This if statement is not required
			// if just traversal is needed. 
	        if (node.data == key) {
	            // Found.
				System.out.println("Found.");
				return;
	        }

	        // Add all the children of the node to queue.
			if (node.left != null) { queue.add(node.left); }
			if (node.right != null) { queue.add(node.right); } 
	    } // end while (...)

	    // Not found.
	    // If just traversal is needed, these two lines are not requisite. 
		System.out.println("Not found.");
		return;
	}

	// Retrieve. O(log N)
	// Assume that left < mid <= right.
	// Author: JAVA p.617 + kei
	// Date  : October 21, 2016
	public Integer retrieve(SimpleTreeNode tNode, int key) {
		if (tNode == null) {
			// Not found. 
			return null;
		}
		
		if (key == tNode.data) {
			// Found.
			return tNode.data;
		} else if (key < tNode.data) {
			// Search left subtree.
			return retrieve(tNode.left, key);
		} else {
			// key > tNode.data.
			// Search right subtree.
			return retrieve(tNode.right, key);
		}
	}
	
	// Retrieve and return node. Iterative version. O(log N)
	// Assume that left < mid <= right.
	// Author: PIE p.64 + kei
	// Date  : October 31, 2016
	public SimpleTreeNode retrieveIter(SimpleTreeNode tNode, int key) {
		while (tNode != null) {
			if (key == tNode.data) {
				break;
			} else if (key < tNode.data) {
				tNode = tNode.left;
			} else {
				// key > tNode.data
				tNode = tNode.right;
			}	
		}
		
		return tNode;
	}
	
	// Delete. O(log N)
	// Author: JAVA p.618 + kei
	// Date  : October 21, 2016
	public SimpleTreeNode delete(SimpleTreeNode tNode, int key) {
		if (tNode == null) {
			return null;
		}
		
		if (key == tNode.data) {
			// Found. 
			// deleteNode() returns a node substituted for the deleted node.
			tNode = deleteNode(tNode);
		} else if (key < tNode.data) {
			// Search left subtree.
			// Note that tNode.left gets the return.
			tNode.left = delete(tNode.left, key);
		} else {
			// key > tNode.data.
			// Search right subtree.
			// Note that tNode.right gets the return.
			tNode.right = delete(tNode.right, key);
		}
		
		return tNode;
	}
	// Return a node substituted for the node to be deleted.
	private SimpleTreeNode deleteNode(SimpleTreeNode targetNode) {
		if ((targetNode.left == null) && (targetNode.right == null)) {
			// No child.
			return null;
		} else if (targetNode.left == null) {
		    // No left child.
			return targetNode.right;
		} else if (targetNode.right == null) {
		    // No right child.
			return targetNode.left;
		} else {
		    // There are two children.
			// Retrieve the in-order successor data.
			int replacementData = findLeftmost(targetNode.right);
			// Write over the target node data.
			targetNode.data = replacementData;
			// Delete the in-order successor node.
			targetNode.right = deleteLeftmost(targetNode.right);
			return targetNode;			
		}		
	}
	// Find data on the leftmost node.
	private int findLeftmost(SimpleTreeNode tNode) {
		if (tNode.left == null) {
			return tNode.data;
		}
		
		return findLeftmost(tNode.left);
	}
	// Delete leftmost node.
	private SimpleTreeNode deleteLeftmost(SimpleTreeNode tNode) {
		if (tNode.left == null) {
			// Point! Base case.
			return tNode.right;
		}
		// Down to left subtree.
		tNode.left = deleteLeftmost(tNode.left);
		return tNode;		
	}
	
	// Get minimum in BST. O(log N)
	// Author: kei
	// Date  : October 31, 2016
	public int getMin(SimpleTreeNode tNode) {
		if (tNode == null) {
			throw new RuntimeException("Empty tree.");
		}
		
		while (tNode.left != null) {
			tNode = tNode.left;
		}
		
		return tNode.data;
	}
	
	// Count the number of nodes in BT. O(N)
	// Author: kei
	// Date  : November 1, 2016
	public int countNodes(SimpleTreeNode tNode) {
		int count = 0;	
		return countNodes(tNode, count);
	}
	private int countNodes(SimpleTreeNode tNode, int count) {
		if (tNode == null) { return 0; }
		
		count = countNodes(tNode.left, count)
				+ countNodes(tNode.right, count);
		
		return count + 1;
	}
	// Alternate to count the number of nodes in BT. O(N)
	// Author: kei
	// Date  : November 4, 2016
	public int countN(SimpleTreeNode tNode) {
		if (tNode == null) { return 0; }

		return countNodes(tNode.left) + countNodes(tNode.right) + 1;		
	}
		

	
	
	
	
	

	
	
}

// You might want to mention that the fields should be private and
// expose public methods to manipulate them, but for simplicity, 
// they have no modifier.
class SimpleTreeNodeP {
	int data;
	SimpleTreeNodeP left;
	SimpleTreeNodeP right;
	SimpleTreeNodeP parent;
	
	public SimpleTreeNodeP() {
	}
	
	public SimpleTreeNodeP(int data) {
		this.data = data;
		left = null;
		right = null;
	}
	
	@Override
	public String toString() {
		return String.valueOf(data);
	}
}













