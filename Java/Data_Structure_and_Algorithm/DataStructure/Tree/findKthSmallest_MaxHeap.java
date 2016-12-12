// Find k-th smallest element in an array. O(n log k)
// Note that you can actually get k-th smallest using partitioning, 
// which takes O(n) time. (findKthSmallest_partition.java)
// Author: Coursera "Mastering Software Engineer Interview" + kei
// https://www.coursera.org/learn/cs-tech-interview/lecture/Qu7hy/case-study-going-deeper
// Date  : October 24, 2016

public int kthSmallestViaHeap(int[] arr, int k) {
    if (k <= 0 || k > arr.length) {
        throw new IllegalArgumentException();
    }
    // Create a max heap whose size is k.
    PriorityQueue<Integer> smallestK = 
            new PriorityQueue<Integer>(k, Collections.reverseOrder());
    // Add array elements to the heap until it's full. 
    for (int i = 0; i < Math.min(arr.length, k); i++) {
        smallestK.add(arr[i]);
    }
    // Traverse the array and just keep k smallest elements in the heap.
    for (int i = k; i < arr.length; i++) {
        if (arr[i] < smallestK.peek()) {
            smallestK.poll();
            smallestK.add(arr[i]);
        }
    }

    return smallestK.peek();
}






























