// Explore binary tree in pre-order, in-order, or post-order.
// This is actually DFS in tree. 
// Author: kei
// Date  : September 24, 2016


// **************** class TreeIterator *************************
// For binary tree traversals in preorder, inorder, or postorder.
class TreeIterator<T> implements Iterator<T>
{
    private BinaryTreeBasis<T> binTree;
    private TreeNode<T> currentNode;
    private LinkedList<TreeNode<T>> queue; // from JCF

    public TreeIterator(BinaryTreeBasis<T> bTree)
    {
        binTree = bTree;
        currentNode = null;
        // empty queue indicates no traversal type currently
        // selected or end of current traversal has been reached
        queue = new LinkedList<TreeNode<T>>();
    } // end constructor

    @Override
    public boolean hasNext()
    {
        return !queue.isEmpty();
    } // end hasNext

    @Override
    public T next() throws java.util.NoSuchElementException
    {
        // Dequeue. Remove node from head.
        currentNode = queue.remove();
        return currentNode.item;
    } // end next

    @Override
    public void remove() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    } // end remove

    public void setPreorder()
    {
        queue.clear();
        preorder(binTree.root);
    } // setPreOrder

    public void setInorder()
    {
        queue.clear();
        inorder(binTree.root);
    } // end setInorder

    public void setPostorder()
    {
        queue.clear();
        postorder(binTree.root);
    } // end setPostorder

    private void preorder(TreeNode<T> treeNode)
    {
        if (treeNode != null)
        {
            // Add node to tail.
            queue.add(treeNode);
            preorder(treeNode.leftChild);
            preorder(treeNode.rightChild);
        } // end if
    } // end preorder

    private void inorder(TreeNode<T> treeNode)
    {
        if (treeNode != null)
        {
            inorder(treeNode.leftChild);
            queue.add(treeNode);
            inorder(treeNode.rightChild);
        } // end if
    } // end inorder

    private void postorder(TreeNode<T> treeNode)
    {
        if (treeNode != null)
        {
            postorder(treeNode.leftChild);
            postorder(treeNode.rightChild);
            queue.add(treeNode);
        } // end if
    } // end postorder
} // end TreeIterator



























