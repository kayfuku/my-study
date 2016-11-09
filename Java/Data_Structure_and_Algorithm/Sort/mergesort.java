// Mergesort.
// Average case: O(N log N)
// Worst case  : O(N log N)
// Space       : O(N) (or O(1), which is called in-place mergesort.)
// Author: Cracking Coding Interview p.120  + kei.
// Date  : February 11, 2016


void mergesort(int[] array, int low, int high) {
    // if the piece size is 1, it's a base case doing nothing.
    if (low < high) {
        int middle = (low + high) / 2;
        // Sort left half part.
        mergesort(array, low, middle);
        // Sort right half part.
        mergesort(array, middle + 1, high);
        // Merge.
        merge(array, low, middle, high);
    }
}

void merge(int[] array, int low, int middle, int high) {
    int[] helper = new int[array.length];

    // Copy both halves to the helper array.
    for (int i = low; i <= high; i++) {
        helper[i] = array[i];
    }

    int helperLeft = low;
    int helperRight = middle + 1;
    int currOriginalArray = low;

    // In helper array, compare left half part with right half part and 
    // copy smaller value to the original array. 
    while (helperLeft <= middle && helperRight <= high) {
        if (helper[helperLeft] <= helper[helperRight]) {
            array[currOriginalArray] = helper[helperLeft];
            helperLeft++;
        } else {
            array[currOriginalArray] = helper[helperRight];
            helperRight++;
        }
        currOriginalArray++;
    }

    // Append all the rest of the values in the left half part 
    // to the original array.
    int remaining = middle - helperLeft;
    for (int i = 0; i <= remaining; i++) {
        array[currOriginalArray + i] = helper[helperLeft + i];
    }
}

























