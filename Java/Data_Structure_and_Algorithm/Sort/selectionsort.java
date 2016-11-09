// Selection Sort.  
// Average case: O(N^2)
// Worst case  : O(N^2)
// Comparation : N * (N - 1) /2
// Swap max    : N
// Author: アルゴリズムを学ぼう p.70 + kei
// Date  : September 27, 2016

void selectionSort(int[] arr) {
    if (arr == null) {
        return;
    }

    int N = arr.length;
    for (int i = 0; i < N; ++i) {
        int minIdx = i;
        // Find min from i.
        for (int j = i + 1; j < N; ++j) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j;
            }
        }

        // Swap i with minIdx.
        int t = arr[i];
        arr[i] = arr[minIdx];
        arr[minIdx] = t;
    }
}














