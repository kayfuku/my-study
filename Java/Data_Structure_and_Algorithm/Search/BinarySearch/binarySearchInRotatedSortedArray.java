// Find a value in a rotated sorted array.
// Author: Cracking Coding Interview p.338 + kei.
// Date  : February 13, 2016


public int binarySearchInRotatedSortedArray(
    int[] array, int left, int right, int x) {

    // Binary Search preparation.
    int mid = (left + right) / 2;

    // Found x.
    if (array[mid] == x) {
        return mid;
    }
    // Error.
    if (right < left) {
        return -1;
    }

    // Binary Search.
    // Search range varies depending on the value of 
    // left-most, mid, and right-most in the array.
    if (array[left] < array[mid]) { 
        // The left half is in increasing order.
        if (x >= array[left] && x <= array[mid]) {
            // Search the left half.
            return binarySearchInRotatedSortedArray(array, left, mid - 1, x);
        } else {
            // Search the right half.
            return binarySearchInRotatedSortedArray(array, mid + 1, right, x);
        }        
    } else if (array[left] > array[mid]) { 
        // The right half is in increasing order.
        if (x >= array[mid] && x <= array[right]) {
            // Search the right half.
            return binarySearchInRotatedSortedArray(array, mid + 1, right, x);
        } else {
            // Search the left half.
            return binarySearchInRotatedSortedArray(array, left, mid - 1, x);
        }        
    } else if (array[left] == array[mid]) {
        if (array[mid] != array[right]) {
            // Search the right half.
            return binarySearchInRotatedSortedArray(array, mid + 1, right, x);
        } else {
            // array[left] == array[mid] == array[right]
            // Both halves need to be searched.
            // Search the left half.
            int result = binarySearchInRotatedSortedArray(array, left, mid - 1, x);
            if (result = -1) {
                // Search the right half.
                return binarySearchInRotatedSortedArray(array, mid + 1, right, x);
            } else {
                return result;
            }
        }
    } // end else if (array[left] == array[mid]) {

    // Not found.
    return -1;
}
    











































