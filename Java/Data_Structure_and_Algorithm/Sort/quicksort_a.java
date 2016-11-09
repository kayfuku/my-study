// Quicksort.
// Average case: O(N log N)
// Worst case  : O(N^2) (almost sorted)
// Best case   : O(N) (all elements are equal)
// Space       : O(log N)
// Author: アルゴリズムを学ぼう p.78  + kei.
// Date  : September 27, 2016


void quicksort(int[] arr) {
    quicksort(arr, 0, arr.length);
}

void quicksort(int[] arr, int left, int right) {
    // right is one after the tail index.
    // No need to sort if length is less than 1.
    if (right - left <= 1) { return; }

    // Pick up a pivot. If impossible, return -1.
    int pivotIdx = findPivotIdx(arr, left, right);
    if (pivotIdx < 0) { return; }

    // Devide.
    int mid = partition(arr, left, right, arr[pivotIdx]);

    // Sort. 
    quicksort(arr, left, mid);
    quicksort(arr, mid, right);
}

// Return pivot index.
int findPivotIdx(int[] arr, int left, int right) {
    if (right - left <= 1) {
        return -1;
    }

    // Pick up two different values and select the 
    // smaller one as a pivot.
    int t = arr[left];
    for (int i = left + 1; i < right; ++i) {
        if (arr[i] == t) {
            continue;
        }

        if (arr[i] < t) {
            return i;
        } else {
            return left;
        }
    }

    // All values are same, which is all sorted.
    return -1;
}

// All numbers that are less than the pivot element 
// come before all numbers that are greater than it.
// Return the partitioning index.
int partition(int[] arr, int left, int right, int pivot) {
    // right is one after the tail element.
    int iLeft = left, iRight = right - 1;

    while (iLeft < iRight) {
        // Find the element that is greater than or equal to pivot.
        while (iLeft < right && arr[iLeft] <= pivot) {
            ++iLeft;
        }
        // Find the element that is less than pivot.
        while (left < iRight && pivot > arr[iRight]) {
            --iRight;
        }

        if (iLeft >= iRight) {
            break;
        }

        // Swap them.
        int t = arr[iLeft];
        arr[iLeft] = arr[iRight];
        arr[iRight] = t;
    }

    return iRight + 1;
}

























