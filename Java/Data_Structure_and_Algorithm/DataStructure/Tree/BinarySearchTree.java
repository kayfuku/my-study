// Binary Search Tree.
// Author: kei
// Date  : September 22, 2016

// **************** class BinarySearchTree *********************
// Reference-Based ADT Binary Search Tree.
// Assumption: A tree contains at most one item with a
// given search key at any time.
class BinarySearchTree<T extends KeyedItem<KT>, KT extends Comparable<? super KT>>
		extends BinaryTreeBasis<T> {
	// inherits isEmpty(), makeEmpty(), getRootItem(), and
	// the use of the constructors from BinaryTreeBasis

	public BinarySearchTree() {
	} // end default constructor

	public BinarySearchTree(T rootItem) {
		super(rootItem);
	} // end constructor

	@Override
	public void setRootItem(T newItem) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	} // end setRootItem

	public void insert(T newItem) {
		root = insertItem(root, newItem);
	} // end insert

	public T retrieve(KT searchKey) {
		return retrieveItem(root, searchKey);
	} // end retrieve

	public void delete(KT searchKey) throws TreeException {
		root = deleteItem(root, searchKey);
	} // end delete

	public void delete(T item) throws TreeException {
		root = deleteItem(root, item.getKey());
	} // end delete

	// Insert newItem into the Binary Search Tree tNode. O(log(n))
	protected TreeNode<T> insertItem(TreeNode<T> tNode, T newItem) {
		TreeNode<T> newSubtree;
		
		// Base case.
		if (tNode == null) {
			// Position of insertion found.
			// Insert after leaf create a new node.
			tNode = new TreeNode<T>(newItem, null, null);
			return tNode;
		} // end if
		
		T nodeItem = tNode.item;

		// Search for the insertion position.
		if (newItem.getKey().compareTo(nodeItem.getKey()) < 0) {
			// Search the left subtree and insert into it.
			newSubtree = insertItem(tNode.leftChild, newItem);
			tNode.leftChild = newSubtree;
			return tNode;
		} else { 
			// Search the right subtree and insert into it.
			newSubtree = insertItem(tNode.rightChild, newItem);
			tNode.rightChild = newSubtree;
			return tNode;
		} // end if
	} // end insertItem

	// Search for searchKey in the Binary Search Tree tNode. O(log(n))
	protected T retrieveItem(TreeNode<T> tNode, KT searchKey) {
		T treeItem;
		if (tNode == null) {
			// Base case.
			treeItem = null;
		} else {
			T nodeItem = tNode.item;
			if (searchKey.compareTo(nodeItem.getKey()) == 0) {
				// Item is in the root of some subtree.
				treeItem = tNode.item;
			} else if (searchKey.compareTo(nodeItem.getKey()) < 0) {
				// Search the left subtree.
				treeItem = retrieveItem(tNode.leftChild, searchKey);
			} else { 
				// Search the right subtree.
				treeItem = retrieveItem(tNode.rightChild, searchKey);
			} // end if
		} // end if
		return treeItem;
	} // end retrieveItem

	// Delete searchKey from the Binary Search Tree tNode. O(log(n))
	protected TreeNode<T> deleteItem(TreeNode<T> tNode, KT searchKey) {
		// Calls: deleteNode.
		TreeNode<T> newSubtree;
		if (tNode == null) {
			throw new TreeException("TreeException: Item not found");
		} else {
			T nodeItem = tNode.item;
			if (searchKey.compareTo(nodeItem.getKey()) == 0) {
				// Item is in the root of some subtree.
				// Delete the item.
				tNode = deleteNode(tNode); 
			}
			// Search for the item.
			else if (searchKey.compareTo(nodeItem.getKey()) < 0) {
				// Search the left subtree.
				newSubtree = deleteItem(tNode.leftChild, searchKey);
				tNode.leftChild = newSubtree;
			} else { 
			    // Search the right subtree.
				newSubtree = deleteItem(tNode.rightChild, searchKey);
				tNode.rightChild = newSubtree;
			} // end if
		} // end if
		return tNode;
	} // end deleteItem

	protected TreeNode<T> deleteNode(TreeNode<T> tNode) {
		// Algorithm note: There are four cases to consider:
		// 1. The tNode is a leaf.
		// 2. The tNode has no left child.
		// 3. The tNode has no right child.
		// 4. The tNode has two children.
		// Calls: findLeftmost and deleteLeftmost
		T replacementItem;

		// test for a leaf
		if ((tNode.leftChild == null) && (tNode.rightChild == null)) {
			return null;
		} // end if leaf

		// test for no left child
		else if (tNode.leftChild == null) {
			return tNode.rightChild;
		} // end if no left child

		// test for no right child
		else if (tNode.rightChild == null) {
			return tNode.leftChild;
		} // end if no right child

		// there are two children:
		// retrieve and delete the inorder successor
		else {
			// Find the in-order successor.
			replacementItem = findLeftmost(tNode.rightChild);
			// Set the in-order successor to the deleted node's item.
			tNode.item = replacementItem;
			// Delete the in-order successor.
			tNode.rightChild = deleteLeftmost(tNode.rightChild);
			return tNode;
		} // end if
	} // end deleteNode

	// Find in-order successor.
	protected T findLeftmost(TreeNode<T> tNode) {
		if (tNode.leftChild == null) {
			// At this point, the tNode is in-order successor.
			return tNode.item;
		} else {
			return findLeftmost(tNode.leftChild);
		} // end if
	} // end findLeftmost

	// Delete in-order successor.
	protected TreeNode<T> deleteLeftmost(TreeNode<T> tNode) {
		if (tNode.leftChild == null) {
			// At this point, the tNode is in-order successor.
			// The node does not have a left child.
			// Return the right child and move it into its parent place.
			return tNode.rightChild;
		} else {
			tNode.leftChild = deleteLeftmost(tNode.leftChild);
			return tNode;
		} // end if
	} // end deleteLeftmost

} // end BinarySearchTree































