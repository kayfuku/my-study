// Traverse a binary search tree in 
// in-order, pre-order, post-order and level-order(BFS). 
// Author: kei
// Date  : September 24, 2016


package whiteboard;


public class CreateBinarySearchTree {

	public static void main(String[] args) {
		
		SimpleBinarySearchTree binarySearchTree = new SimpleBinarySearchTree();
		SimpleTreeNode tNode = null;

		tNode = buildBinarySearchTree(binarySearchTree, tNode);
		
		System.out.println("in-order");
		binarySearchTree.inorder(tNode);
		System.out.println();
		System.out.println("pre-order");
		binarySearchTree.preorder(tNode);
		System.out.println();
		System.out.println("post-order");
		binarySearchTree.postorder(tNode);
		System.out.println();
		
		
		System.out.println("done.");		
		
	}
	
	public static SimpleTreeNode buildBinarySearchTree(
			SimpleBinarySearchTree binarySearchTree, SimpleTreeNode tNode) {

		tNode = binarySearchTree.insert(tNode, 6);
		tNode = binarySearchTree.insert(tNode, 3);
		tNode = binarySearchTree.insert(tNode, 12);
		tNode = binarySearchTree.insert(tNode, 1);
		tNode = binarySearchTree.insert(tNode, 9);
		tNode = binarySearchTree.insert(tNode, 4);
		tNode = binarySearchTree.insert(tNode, 14);
		
		return tNode;
	}
	
	
	
	
	
	
}

class SimpleBinarySearchTree {
	
	public SimpleBinarySearchTree() {
	}
	
	public SimpleTreeNode insert(SimpleTreeNode tNode, int newData) {
		
		// Base case.
		if (tNode == null) {
			tNode = new SimpleTreeNode(newData);
			return tNode;			
		}
		
		// Recurse.
		if (tNode.data > newData) {
			// Search left subtree.
			tNode.left = insert(tNode.left, newData);
			return tNode;
		} else {
			// tNode.data <= newData
			// Search right subtree.
			tNode.right = insert(tNode.right, newData);
			return tNode;
		}
	}
	
	public void inorder(SimpleTreeNode tNode) {
		if (tNode != null) {	
			// In-order traversal.
			inorder(tNode.left);
			System.out.println(tNode.data);
			inorder(tNode.right);
		}
	}
	
	public void preorder(SimpleTreeNode tNode) {
		if (tNode != null) {	
			// Pre-order traversal.
			System.out.println(tNode.data);
			preorder(tNode.left);
			preorder(tNode.right);
		}
	}
	
	public void postorder(SimpleTreeNode tNode) {
		if (tNode != null) {	
			// Post-order traversal.
			postorder(tNode.left);
			postorder(tNode.right);
			System.out.println(tNode.data);
		}
	}

	// Level-order traversal. (BFS)
	// Author: kei
	// Date  : October 31, 2016
	public void levelOrder(SimpleTreeNode tNode, int key) {
		// No need to store nodes that has been visited. 
	    //Set<SimpleTreeNode> visited = new HashSet<SimpleTreeNode>();
	    LinkedList<SimpleTreeNode> queue = new LinkedList<SimpleTreeNode>();
	    System.out.println(node.data);

	    queue.add(tNode);

	    while (!queue.isEmpty()) {
	        SimpleTreeNode node = queue.poll();

	        if (node.data == key) {
	            // Found.
				System.out.println("Found.");
				return;
	        }
	        
	        // Add all children of the node to queue.
			if (node.left != null) { queue.add(node.left); }
			if (node.right != null) { queue.add(node.right); } 
	    } // end while (...)

	    // Not found.
		System.out.println("Not found.");
		return;
	}

	
	
}


class SimpleTreeNode {
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
	
	@Override
	public String toString() {
		return String.valueOf(data);
	}
}













