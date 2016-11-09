// Use Binary Heap with PriorityQueue.
// offer(): add data　O(log N)
// poll() : get and remove min/max (head) element　O(log N)
// peek() : get min/max from head　O(1)
// remove(Object o), contains(Object o): retrieve o　O(N)

// Author: Data Abstraction & Problem Solving with JAVA p.691 + kei
// Date  : October 3, 2016

import java.util.Collections;
import java.util.PriorityQueue;

public class Heap {

    public static void main(String[] args) {        
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(2);
        minHeap.add(2);
        minHeap.add(6);
        minHeap.add(15);
        minHeap.add(8);
        System.out.println(minHeap); // [2, 6, 15, 8]
        int n = minHeap.peek();
        System.out.println(n); // 2 
        PriorityQueue<Integer> maxHeap = 
                new PriorityQueue<Integer>(Collections.reverseOrder());
        maxHeap.add(2);
        maxHeap.add(6);
        maxHeap.add(15);
        maxHeap.add(8);
        System.out.println(maxHeap); // [15, 8, 6, 2]
        n = maxHeap.peek();
        System.out.println(n); // 2 
        
        PriorityQueue<Integer> maxHeap3 = 
                new PriorityQueue<Integer>(2, Collections.reverseOrder());
        maxHeap3.add(5);
        maxHeap3.add(1);
        maxHeap3.add(3);
        maxHeap3.add(2);
        System.out.println(maxHeap3); // [5, 2, 3, 1]
        maxHeap3.poll();
        maxHeap3.poll();
        maxHeap3.poll();
        System.out.println(maxHeap3); // [1]

        // ?? How does initial capacity work? 







        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end of main().
}





















































