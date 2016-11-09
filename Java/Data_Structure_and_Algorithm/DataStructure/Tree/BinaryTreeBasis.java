// A basic class for Binary Tree.
// Author: kei
// Date  : September 20, 2016

class BinaryTreeBasis<T> {
    protected TreeNode<T> root;

    public BinaryTreeBasis() {
        root = null;
    }

    public BinaryTreeBasis(T rootItem) {
        root = new TreeNode<T>(rootItem, null, null);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }

    public T getRootItem() throws TreeException {
        if (root == null) {
            throw new TreeException("TreeException: Empty tree.");
        } else {
            return root.item;
        }
    }

    public void setRootItem(T newItem) {
        root.item = newItem;
    }

}








































