// Create a minimal Binary Search Tree from a sorted array.
// Author: CtCI 4.2 p.242 + kei.
// Date  : February 10, December 8, 2016

TreeNode createMinimalBST(int[] array) {
    return createMinimalBST(array, 0, array.length - 1);
}

TreeNode createMinimalBST(int[] arr, int start, int end) {
    // Base case.
    // Like Binary Search. 
    if (end < start) {
        return null;
    }

    int mid = (start + end) / 2;

    // Create a root node. 
    TreeNode node = new TreeNode(arr[mid]);
    // Create the left subtree. 
    node.left = createMinimalBST(arr, start, mid - 1);
    // Create the right subtree. 
    node.right = createMinimalBST(arr, mid + 1, end);

    return node;
}




































