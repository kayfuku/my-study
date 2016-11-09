// Heapsort. 
// Average case: O(N log N)
// Author: アルゴリズムを学ぼう p.85 + kei
// Date  : September 21, 2016

int[] heapsort(int[] values) {
    Heap heap = new Heap(values.length);

    // O(N log N)
    for (int i = 0; i < values.length; i++) {
        heap.insert(values[i]);
    }

    // O(N log N)
    for (int i = 0; i < values.length; i++) {
        values[i] = heap.extractMin();
    }

    return values;
}














