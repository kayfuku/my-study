// Sort an almost sorted array. 
// Author: EPI 11.3 p.164 + kei
// Date  : November 28, 2016

package whiteboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;


public class Lab_whiteboard {

    public static void main(String[] args) {

        Integer[] nums = new Integer[]{ 3, -1, 2, 6, 4, 5, 8, 10, 7, 9 };
        List<Integer> list = new ArrayList<>(Arrays.asList(nums));
        Iterator iter = list.iterator();
        sortAlmostSortedArray(iter, 2); // -1 2 3 4 5 6 7 8 9 10  
        

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    
    // O(n log k) time.
    // O(k) space. 
    public static void sortAlmostSortedArray(Iterator<Integer> sequence, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // Fill the heap. 
        for (int i = 0; i < k + 1 && sequence.hasNext(); i++) {
            minHeap.add(sequence.next());
        }
        
        Integer n;
        while ((n = minHeap.poll()) != null) {
            System.out.println(n);
            if (sequence.hasNext()) {
                minHeap.add(sequence.next());
            }           
        }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}

























