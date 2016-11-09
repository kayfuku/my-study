// Pending...
// Check if array is sorted.
// Author: kei
// Date  : October 21, 2016

boolean isSortedArray(int[] arr) {
    if (arr[0] > arr[arr.length - 1]) {
        // Descending order.

    } else if (arr[0] < arr[arr.length - 1]) {
        // Ascending order.

    } else {
        // All are the same.
    }



    for (int i = 1; i < arr.length; i++) {
        if (arr[i - 1] > arr[i]){
            return false;
        }
    }



}



