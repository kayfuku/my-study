// Find leftmost child.

// Iterative version.
// Author: CtCI 6th p.249 + kei
// Date  : October 23, 2016
TreeNode findLeftMostChild(TreeNode n) {
    if (n == null) {
        return null;
    }
    while (n.left != null) {
        n = n.left;
    }
    return n;
}

// Recursive version.
// Author: JAVA p.619 + kei
// Date  : October 23, 2016
TreeNode findLeftMostChild(TreeNode n) {
    if (n.left == null) {
        return n;
    }
    return findLeftMostChild(n.left);
}



