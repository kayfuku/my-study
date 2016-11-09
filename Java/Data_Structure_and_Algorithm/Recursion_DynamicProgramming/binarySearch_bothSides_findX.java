// Binary Search variant. 
// Search both the left and right side.
// arr is sorted integers including dups. Find i such that arr[i] = i.
// Return the first index found. So, if there are two indices, one is
// on the left, the other is on the right, then return the left one. 

// Author: CtCI 6th p.347 + kei.
// Date  : Octover 6, 2016


int binarySearch_bothSides_findX(int[] arr) {
    return binarySearch_bothSides_findX(arr, 0, arr.length - 1);
}

int binarySearch_bothSides_findX(int[] arr, int left, int right) {
    // Base case. 
    if (right < left) {
        // Not found. 
        return -1;
    }
    int midIndex = (left + right) / 2;
    if (arr[midIndex] == midIndex) {
        return midIndex;
    }

    // First, search the left side. 
    int returnIndexL = binarySearch_bothSides_findX(
                           arr, left, Math.min(midIndex - 1, arr[midIndex]));
    if (returnIndexL >= 0) {
        // Found.
        return returnIndexL;
    }

    // Not found in the left side. 
    // Then, search the right side.
    int returnIndexR = binarySearch_bothSides_findX(
                           arr, Math.max(midIndex + 1, arr[midIndex]), right);
    return returnIndexR;

}

























