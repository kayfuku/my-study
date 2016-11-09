// Binary Heap.
// Author: アルゴリズムを学ぼう p.83 + kei
// Date  : September 21, 2016

public class Heap {
    // index that is right after the tail element.
    private int end;
    private int[] values;

    public Heap(int n) {
        this.end = 0;
        this.values = new int[n];
    }

    public void insert(int v) {
        if (end >= values.length) {
            // The array is already full.
            throw new RuntimeException("Exceeds the limit.");
        }

        // First, add it to the tail.
        int p = end;
        values[p] = v;
        end++;

        // Second, rebuild the heap.
        // Repeat until root.
        while (p > 0) {
            if (values[p] <= values[(p - 1) / 2]) {
                // Parent is smaller than or equal to the inserted node.
                break;
            }

            // Parent is bigger than the node.
            // Swap the parent with the node.
            int t = values[(p - 1) / 2];
            values[(p - 1) / 2] = values[p];
            values[p] = t;

            // Move p to its parent.
            p = (p - 1) / 2;
        }
    }

    public int extractMin() {
        if (end <= 0) {
            throw new RuntimeException("Exceeds the limit.");
        }

        // Get the root. This is min.
        int result = values[0];
        // Move the end node to the root node.
        end--;
        values[0] = values[end];

        // Rebuild the heap.
        int p = 0;
        while (p < end) {
            // If the node has a child, get the child.
            int left = 2 * p + 1 < end ? values[2 * p + 1] : Integer.MAX_VALUE;
            int right = 2 * p + 2 < end ? values[2 * p + 2] : Integer.MAX_VALUE;

            // Parent is smaller than or equal to the node.
            if (values[p] <= left && values[p] <= right) {
                break;
            }

            // Swap the node with the child node that is
            // smaller than the other.
            if (left < right) {
                values[2 * p + 1] = values[p];
                values[p] = left;
                p = 2 * p + 1;
            } else {
                values[2 * p + 2] = values[p];
                values[p] = right;
                p = 2 * p + 2;
            }
        }

        return result;
    }
    
}



















