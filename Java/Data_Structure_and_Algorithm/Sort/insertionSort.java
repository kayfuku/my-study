// Insertion Sort.  
// Average case: O(N^2)
// Worst case  : O(N^2) (reverse order)
// Best case   : O(N)   (sorted)
// Author: アルゴリズムを学ぼう p.72 + kei
// Date  : September 27, 2016

void insertionSort(int[] arr) {
    if (arr == null) {
        return;
    }

    int N = arr.length;
    for (int i = 1; i < N; ++i) {
        int j = i;
        while (j > 0 && arr[j - 1] > arr[j]) {
            // Swap.
            int t = arr[j];
            arr[j] = arr[j - 1];
            arr[j - 1] = t;
            --j;
        }
    }
}































