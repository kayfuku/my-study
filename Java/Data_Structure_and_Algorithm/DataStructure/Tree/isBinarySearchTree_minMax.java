// Check if a tree is Binary Search Tree.
// Min/Max version. 
// Author: Cracking Coding Interview p.222 + kei.
// Date  : February 11, 2016


boolean isBinarySearchTree(TreeNode node) {
    return isBinarySearchTree(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
}

boolean isBinarySearchTree(TreeNode node, int min, int max) {
    // Base Case.
    if (n == null) {
        return true;
    }

    // Check if the current node is less than all of the nodes that
    // are on the right side, and
    // check if the current node is greater than all of the nodes that 
    // are on the left side. 
    // If the definition of BST is left < mid < right, then 
    // "node.data >= max" is needed instead of "node.data > max".
    if (node.data <= min || node.data > max) {
        return false;
    }

    // Check the left and right subtrees. 
    if (!isBinarySearchTree(node.left, min, node.data) || 
        !isBinarySearchTree(node.right, node.data, max)) {
        return false;
    }

    // All good.
    return true;
}
















