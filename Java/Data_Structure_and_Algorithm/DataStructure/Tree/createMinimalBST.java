// Create a minimal Binary Search Tree from a sorted array.
// Author: Cracking Coding Interview p.218 + kei.
// Date  : February 10, 2016


TreeNode createMinimalBST(int[] arr, int start, int end) {
    // Base case.
    if (end < start) {
        return null;
    }

    int mid = (start + end) / 2;

    TreeNode node = new TreeNode(arr[mid]);
    node.left = createMinimalBST(arr, start, mid - 1);
    node.right = createMinimalBST(arr, mid + 1, end);

    return node;
}


TreeNode createMinimalBST(int[] array) {
    return createMinimalBST(array, 0, array.length - 1);
}


