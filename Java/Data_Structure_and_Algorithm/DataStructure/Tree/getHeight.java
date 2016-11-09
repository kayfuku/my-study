// Get the height of a binary tree.
// Author: Cracking Coding Interview p.216 + kei
// Date  : February 8, 2016

public static int getHeight(TreeNode root) {
    // Base case.
    if (root == null) {
        return -1;
    }

    return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
}


// Get the height of binary tree. 
// Post-order because it needs the info of both left and right child.
// Author: kei
// Date  : September 25, 2016

int getHeight(SimpleTreeNode tNode) {
    
    if (tNode == null) {
        return -1;
    }
    
    int leftHeight = getHeight(tNode.left) + 1;
    int rightHeight = getHeight(tNode.right) + 1;
    
    int height = Math.max(leftHeight, rightHeight);
    
    return height;
}




























