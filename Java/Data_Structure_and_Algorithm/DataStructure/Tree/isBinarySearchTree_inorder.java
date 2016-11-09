// Check if a tree is Binary Search Tree.
// In-order traversal version. 
// Time Complexity: O(n), Space Complexity: O(log n) if balanced.
// Author: Cracking Coding Interview 6th p.246 + kei.
// Date  : September 25, 2016

Integer lastPrinted = null;
boolean isBinarySearchTree(SimpleTreeNode n) {
    if (n == null) {
        return true;
    }

    // Check left subtree.
    if (!isBinarySearchTree(n.left)) {
        return false;
    }

    // Check current node.
    if (lastPrinted != null && n.data <= lastPrinted) {
        return false;
    }
    lastPrinted = n.data;

    // Check right subtree.
    if (!isBinarySearchTree(n.right)) {
        return false;
    }

    // All good!
    return true;
}


// Check if a tree is Binary Search Tree.
// In-order traversal version. 
// Time Complexity: O(n), Space Complexity: O(log n) if balanced.
// Author: Cracking Coding Interview p.221 + kei.
// Date  : February 11, 2016


public static int lastVisited = Integer.MIN_VALUE;

public static boolean isBinarySearchTree(TreeNode node) {
    // Base Case.
    if (node == null) {
        return true;
    }

    // In-order traversal.
    // Check the left subtree.
    if (!isBinarySearchTree(node.left)) {
        return false;
    }

    // Check the current node. 
    // If you traverse a BST in in-order, 
    // the current node should be bigger than last visited.
    // That is all nodes are sorted in ascending order.
    // The definition of BST is left < mid < last, then
    // '<=' is needed instead of just '<'.
    if (node.data < LAST_VISITED) {
        return false;
    }
    lastVisited = node.data;

    // Check the right subtree.
    if (!isBinarySearchTree(node.right)) {
        return false;
    }

    // All good.
    return true;
}





// // Bad code.
// public boolean isBinarySearchTree(SimpleTreeNode tNode) {
        
//         if (tNode == null) {
//             return true;
//         }
        
//         if (!isBinarySearchTree(tNode.left)
//             || !isBalancedBinaryTree(tNode.right)) {            
//             return false;
//         } else {
//             if (tNode.left != null) {
//                 if(tNode.left.data <= tNode.data) {
//                     if (tNode.right != null) {
//                         if (tNode.data <= tNode.right.data) {
//                             return true;
//                         }
//                     } else {
//                         return true;
//                     }
//                 }
//             } else {
//                 if (tNode.right != null) {
//                     if (tNode.data <= tNode.right.data) {
//                         return true;
//                     }
//                 } else {
//                     return true;
//                 }
//             }
            
//             return false;
//         }
        
//     }












