// Find in-order successor in a Binary Search Tree.
// Assume that each node has a link to its parent.
// Author: CtCI 6th 4.6 p.249 + kei
// Date  : October 23, 2016

TreeNode findInorderSuccessor(TreeNode n) {
    if (n == null) {
        return null;
    }

    if (n.right != null) {
        // Return leftmost child on right subtree.
        return leftMostChild(n.right);
    } else {
        // Go up untill we're on left instead of right.
        TreeNode p = n;
        while (p != null && p.left != n) {
            n = p;
            p = n.parent;
        }

        return p;
    }

}

TreeNode leftMostChild(TreeNode n) {
    if (n == null) {
        return null;
    }

    while (n.left != null) {
        n = n.left;
    }

    return n;
}




































