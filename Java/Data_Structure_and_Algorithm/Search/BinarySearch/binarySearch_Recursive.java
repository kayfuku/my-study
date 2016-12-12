// Binary Search. (The array needs to be sorted in advance.)
// Recursive version. This is less efficient than iterative one. 
// Average Case : O(log N)
// Space Complexity : O(log N)
// Author: Cracking Coding Interview p.122  + kei.
// Date  : February 11, 2016


int binarySearchRecursive(int[] array, int x, int low, int high) {
    // Base case. 
    // This is necessary for recursive Binary Search.
    if (low > high) {
        // Not found. 
        return -1;
    }

    int mid = (low + high) / 2;
    if (array[mid] < x) {
        // Search in the right half part.
        return binarySearchRecursive(array, x, mid + 1, high);
    } else if (array[mid] > x) {
        // Search in the left half part.
        return binarySearchRecursive(array, x, low, mid - 1);
    } else {
        // Found x.
        return mid;
    }
}


















