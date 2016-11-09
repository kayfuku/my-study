// Example to use Binary Search Tree.
// Author: kei

package whiteboard;

import java.util.ArrayList;
import java.util.LinkedList;


public class BinarySearchTree_usage {

	public static void main(String[] args) {
		
		SimpleBinarySearchTree binarySearchTree = new SimpleBinarySearchTree();
		SimpleTreeNode tNode = null;

		// Push data into BST.
		tNode = buildBinarySearchTree(binarySearchTree, tNode);
		
		// Test in-order, pre-order, and post-order.
//		System.out.println("in-order");
//		binarySearchTree.inorder(tNode);
//		System.out.println();
//		
//		System.out.println("pre-order");
//		binarySearchTree.preorder(tNode);
//		System.out.println();
//		
//		System.out.println("post-order");
//		binarySearchTree.postorder(tNode);
//		System.out.println();
		
		// Test retrieve().
//		//Integer n = binarySearchTree.retrieve(tNode, 0);
//		Integer n = binarySearchTree.retrieve(tNode, 9);
//		System.out.println(n);
		
		// Test delete().
//		SimpleTreeNode tNodeDeleted = binarySearchTree.delete(tNode, 6);
//		binarySearchTree.inorder(tNodeDeleted);
		
		// Pre-order traversal with level number.
//		int level = 0;
//		traverseWithLevelNum(tNode, level);
		
		// Test createLevelLinkedList().
//	    ArrayList<LinkedList<SimpleTreeNode>> lists = createLevelLinkedList(tNode);
//	    for (LinkedList<SimpleTreeNode> linkedList : lists) {
//			for (SimpleTreeNode simpleTreeNode : linkedList) {
//				System.out.print(simpleTreeNode + " ");
//			}
//			System.out.println();
//		}

		// Test getHeight() 
		System.out.println("height");
		int h = getHeight(tNode);
		System.out.println(h);
		System.out.println();
		
	    // Test isBlanced().
//	    boolean b = isBalancedBinaryTree(tNode);
//	    System.out.println(b);
	    
	    // Test isBinarySearchTree().
//		boolean b = isBinarySearchTree(tNode);
//		System.out.println(b);
		
		// Test isBinarySearchTreeMinMax().
//		boolean b = isBinarySearchTreeMinMax(tNode);
//		System.out.println(b);

		
		

		
		
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
			SimpleBinarySearchTree binarySearchTree, SimpleTreeNode tNode) {

		tNode = binarySearchTree.insert(tNode, 6);
		tNode = binarySearchTree.insert(tNode, 3);
		tNode = binarySearchTree.insert(tNode, 12);
		tNode = binarySearchTree.insert(tNode, 1);
		tNode = binarySearchTree.insert(tNode, 9);
		tNode = binarySearchTree.insert(tNode, 4);
		tNode = binarySearchTree.insert(tNode, 14);
		//tNode = binarySearchTree.insert(tNode, 25);
		//tNode = binarySearchTree.insert(tNode, 39); // With all above, not balanced.
		
		return tNode;
	}
	
	// Author: kei
	// Date  : September 24, 2016
	public static void traverseWithLevelNum(SimpleTreeNode tNode, int level) {
		if (tNode != null) {	
			// Pre-order traversal.
			System.out.println(tNode.data + " level: " + level);
			traverseWithLevelNum(tNode.left, level + 1);
			traverseWithLevelNum(tNode.right, level + 1);
		}
	}
	
	// You need to write lists creating outside recursion.
	// Author: CtCI 6th p.243 + kei
	// Date  : September 24, 2016
	public static ArrayList<LinkedList<SimpleTreeNode>> createLevelLinkedList(SimpleTreeNode root) {
	    ArrayList<LinkedList<SimpleTreeNode>> lists = new ArrayList<>();
	    createLevelLinkedList(root, lists, 0);
	    return lists;
	}
	// The whole idea is DFS pre-order traversal in a binary tree.
	// Author: CtCI 6th p.243 + kei
	// Date  : September 24, 2016
	private static void createLevelLinkedList(
	    SimpleTreeNode root, ArrayList<LinkedList<SimpleTreeNode>> lists, int level) {

	    // Base case.
	    if (root == null) {
	        return;
	    }

	    // Prepare a linked list at the level.
	    LinkedList<SimpleTreeNode> listEachLevel = null;
	    if (lists.size() == level) {
	        listEachLevel = new LinkedList<SimpleTreeNode>();
	        lists.add(listEachLevel);        
	    } else {
	        listEachLevel = lists.get(level);
	    }

	    // Add the node.
	    listEachLevel.add(root);

	    // Part of DFS pre-order traversal in a binary tree.
	    createLevelLinkedList(root.left, lists, level + 1);
	    createLevelLinkedList(root.right, lists, level + 1);
	}
	
	// Get the height of binary tree. 
	// Post-order because it needs the info of both left and right child.
	// Author: CtCI 6th p.244 + kei
	// Date  : September 24, October 31, 2016
	public static int getHeight(SimpleTreeNode tNode) {
		
		if (tNode == null) {
			// Why -1?
			// Think about when the tree has just one node. 
			// Its height should be 0, so return value 
			// should be 0. Be careful about the definition of height. 
			return -1;
		}
		
		int leftHeight = getHeight(tNode.left);
		int rightHeight = getHeight(tNode.right);
		
		int height = Math.max(leftHeight, rightHeight) + 1; 
		
		return height;
	}
	
	// Check if the binary tree is balanced.
	// Author: CtCI 6th p.245 + kei
	// Date  : September 24, 2016
	public static boolean isBalancedBinaryTree(SimpleTreeNode tNode) {
	    if (checkHeight(tNode) == Integer.MIN_VALUE) {
	        return false;
	    } else {
	        return true;
	    }
	}
	// Get the height at each node and
	// If the subtree is not balanced, return Integer.MIN_VALUE 
	// to break the recursion immediately.
	// Author: CtCI 6th p.245 + kei
	// Date  : September 24, 2016
	private static int checkHeight(SimpleTreeNode tNode) {
	    if (tNode == null) {
	        // Reason why -1 is because the height becomes 0 
	        // when one node added.
	        return -1;
	    }

	    // Check if the left subtree is balanced.
	    int leftHeight = checkHeight(tNode.left);
	    if (leftHeight == Integer.MIN_VALUE) {
	        // Left subtree is not balanced.
	        // So no need to check right subtree.
	        return Integer.MIN_VALUE;
	    }

	    // Check if the right subtree is balanced.
	    int rightHeight = checkHeight(tNode.right);
	    if (rightHeight == Integer.MIN_VALUE) {
	        // Right subtree is not balanced.
	        return Integer.MIN_VALUE;
	    }

	    // Check if this tree is balanced.
	    if (Math.abs(leftHeight - rightHeight) > 1) {
	        // Not balanced.
	        return Integer.MIN_VALUE;
	    } else {
	        // Return the height of this tree.
	        return Math.max(leftHeight, rightHeight) + 1;
	    }
	}
	
	// Check if it's Binary Search Tree. Assume that left < mid < right.
	// In-order traversal. But what if left <= mid < right?
	// Author: CtCI 6th p.246 + kei
	// Date  : September 24, 2016
	public static Integer lastPrinted = null;
	public static boolean isBinarySearchTree(SimpleTreeNode n) {
	    if (n == null) {
	        return true;
	    }

	    // Check left subtree. In-order part 1.
	    if (!isBinarySearchTree(n.left)) {
	        return false;
	    }

	    // Check current node. In-order part 2.
	    // Assumed that left < mid < right.
	    if (lastPrinted != null && n.data <= lastPrinted) {
	        return false;
	    }
	    lastPrinted = n.data;

	    // Check right subtree. In-order part 3.
	    if (!isBinarySearchTree(n.right)) {
	        return false;
	    }

	    // All good!
	    return true;
	}
	
	// Check if it's Binary Search Tree. 'lastPrinted' is a local variable.
	// In-order traversal. 
	// Assume that left < mid < right. But what if left <= mid < right?
	// Author: CtCI 6th p.246 + kei
	// Date  : October 21, 2016
	public static boolean isBinarySearchTree2(SimpleTreeNode n) {
		Integer lastPrinted = null;
		return isBinarySearchTree2(n, lastPrinted);
	}
	private static boolean isBinarySearchTree2(SimpleTreeNode n, Integer lastPrinted) {
	    if (n == null) {
	        return true;
	    }

	    // Check left subtree. In-order part 1.
	    if (!isBinarySearchTree2(n.left, lastPrinted)) {
	        return false;
	    }

	    // Check current node. In-order part 2.
	    // Assumed that left < mid < right.
	    if (lastPrinted != null && n.data <= lastPrinted) {
	        return false;
	    }
	    lastPrinted = n.data;

	    // Check right subtree. In-order part 3.
	    if (!isBinarySearchTree2(n.right, lastPrinted)) {
	        return false;
	    }

	    // All good!
	    return true;
	}
	
	// Check if it's Binary Search Tree.
	// Min/Max solution. 
	// Assume that left <= mid < right.
	// Author: CtCI 6th p.248 + kei
	// Date  : October 21, 2016
	public static boolean isBinarySearchTreeMinMax(SimpleTreeNode tNode) {
		return isBinarySearchTreeMinMax(tNode, null, null);
	}
	private static boolean isBinarySearchTreeMinMax(SimpleTreeNode tNode, Integer min, Integer max) {
		if (tNode == null) {
			return true;
		}
		
		// If it violates the rule, stop recursing here on this branch.
		// Be careful for null cases.
		if ((max != null && tNode.data > max) /* Left child. */ 
		    || (min != null && tNode.data <= min) /* Right child. */) {
			return false;
		}
		
		if (isBinarySearchTreeMinMax(tNode.left, min, tNode.data) 
		    && isBinarySearchTreeMinMax(tNode.right, tNode.data, max)) {
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	
	
}

class SimpleBinarySearchTree {
	
	public SimpleBinarySearchTree() {
	}
	
	// Insert. O(log N)
	// Assume that left < mid <= right.
	// Author: kei
	// Date  : September 24, 2016
	public SimpleTreeNode insert(SimpleTreeNode tNode, int newData) {
		// Base case.
		if (tNode == null) {
			tNode = new SimpleTreeNode(newData);
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
	
	// Retrieve. O(log N)
	// Assume that left < mid <= right.
	// Author: JAVA p.617 + kei
	// Date  : October 21, 2016
	public Integer retrieve(SimpleTreeNode tNode, int key) {
		if (tNode == null) {
			return null;
		}
		
		if (key == tNode.data) {
			// Found.
			return tNode.data;
		} else if (key < tNode.data) {
			// Search left subtree.
			return retrieve(tNode.left, key);
		} else {
			// key >= tNode.data.
			// Search right subtree.
			return retrieve(tNode.right, key);
		}
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
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


// You might want to mention that the fields should be private and
// expose public methods to manipulate them, but for simplicity, 
// they have no modifier.
class SimpleTreeNode implements Comparable<SimpleTreeNode> {
	int data;
	SimpleTreeNode left;
	SimpleTreeNode right;
	
	public SimpleTreeNode() {
	}
	
	public SimpleTreeNode(int data) {
		this.data = data;
		left = null;
		right = null;
	}
	
	public void setLeft(SimpleTreeNode node) {
		left = node;
	}
	
	public void setRight(SimpleTreeNode node) {
		right = node;
	}
	
	@Override
	public int compareTo(SimpleTreeNode o) {
		if (this.data < o.data) {
			return -1;
		} else if (this.data == o.data) {
			return 0;
		} else {
			return 1;
		}
	}
	
	@Override
	public String toString() {
		return String.valueOf(data);
	}

	
}














