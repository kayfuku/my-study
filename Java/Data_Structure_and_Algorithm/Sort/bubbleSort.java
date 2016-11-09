// Bubble Sort.  
// Average case: O(N^2)
// Worst case  : O(N^2)
// Best case   : O(N)
// Comparation : N * (N - 1) / 2
// Swap max    : N * (N - 1) / 2
// Author: アルゴリズムを学ぼう p.69 + kei
// Date  : September 27, 2016

void bubbleSort(int[] arr) {
    if (arr == null) {
        return;
    }

    int N = arr.length;
    for (int i = N; i > 0; --i) {
        for (int j = 1; j < i; ++j) {
            // >= causes not stable sort.
            if (arr[j - 1] > arr[j]) {
                // Swap.
                int t = arr[j - 1];
                arr[j - 1] = arr[j]
                arr[j] = t;
            }
        }
    }
}







































