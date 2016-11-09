// Binary Search variant. 
// arr is sorted distinct integers. Find i such that arr[i] = i.
// Author: CtCI 6th p.346 + kei.
// Date  : Octover 6, 2016


int binarySearch_findX(int[] arr, int left, int right) {
    // Base case. 
    if (right < left) {
        // Not found. 
        return -1;
    }

    int mid = (left + right) / 2;
    if (arr[mid] < mid) {
        return mid;
    } else if (arr[mid] > mid) {
        return binarySearch_findX(arr, left, mid - 1);
    } else {
        return binarySearch_findX(arr, mid + 1, right);
    }
}


















