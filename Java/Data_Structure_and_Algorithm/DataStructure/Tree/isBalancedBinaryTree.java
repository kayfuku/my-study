// Check if a binary tree is balanced.
// Author: Cracking Coding Interview 6th p.245 + kei.
// Date  : September 25, 2016


// Get the height at each node and
// If the subtree is not balanced, return Integer.MIN_VALUE 
// to break the recursion immediately.
public static int checkHeight(TreeNode root) {
    if (root == null) {
        // Reason why -1 is because the height becomes 0 
        // when one node added.
        return -1;
    }

    // Check if the left subtree is balanced.
    int leftHeight = checkHeight(root.left);
    if (leftHeight == Integer.MIN_VALUE) {
        // Left subtree is not balanced.
        // So no need to check right subtree.
        return Integer.MIN_VALUE;
    }

    // Check if the right subtree is balanced.
    int rightHeight = checkHeight(root.right);
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

public static boolean isBalancedBinaryTree(TreeNode root) {
    if (checkHeight(root) == Integer.MIN_VALUE) {
        return false;
    } else {
        return true;
    }
}




// Not efficient.
boolean isBalancedBinaryTree(SimpleTreeNode tNode) {
    
    if (tNode == null) {
        return true;
    }
    
    if (isBalancedBinaryTree(tNode.left)
        && isBalancedBinaryTree(tNode.right)) {
        
        int leftHeight = getHeight(tNode.left);
        int rightHeight = getHeight(tNode.right);
        
        int diff = Math.abs(leftHeight - rightHeight);
        
        return diff <= 1;           
        
    } else {
        return false;
    }
    
}























