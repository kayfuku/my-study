// Find k-th longest string in the sequence of streaming strings. O(n log k)
// Note that you can actually get k-th smallest/largest using partitioning, 
// which takes O(n) time. (findKthSmallest_partition.java)
// Author: EPI Heaps boot camp p.160 + kei
// Date  : November 28, 2016

package whiteboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Lab_whiteboard {

    public static void main(String[] args) {

        String[] strings = {"a", "abcde", "c", "aaa", "bbb", "ccc", 
                "aabb", "bbcc", "aaabbbccc", "abcabc", "b", "dd"};
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList(strings));
        System.out.println(stringList.toString());

        Iterator<String> iter = stringList.iterator();
        String kthLongest = findKthLongest(3, iter);
        System.out.println(kthLongest); // abcde

        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    
    
    public static String findKthLongest(int k, Iterator<String> iter) {
        // Create a Min-Heap. 
        PriorityQueue<String> minHeap = 
                new PriorityQueue<>(k, new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        // Natural ordering leads to Min-Heap. 
                        return Integer.compare(s1.length(), s2.length());
                        
                        // Turns into Max-Heap. 
//                      if (s1.length() == s2.length()) {
//                          return 0;
//                      } else if (s1.length() > s2.length()) {
//                          return -1;
//                      } else {
//                          return 1;
//                      }
                
                    }
                });
        
        // Fill the heap. 
        for (int i = 0; i < k; i++) {
            minHeap.add(iter.next());
        }
        
        while (iter.hasNext()) {
            // Note that once you call iter.next(), then 
            // the cursor returns the element and move to the next. 
            // So you should assign the return value to the variable. 
            String nextString = iter.next();
            int len = nextString.length();
            if (len > minHeap.peek().length()) {
                minHeap.poll();     
                minHeap.add(nextString);
            }
        }
        
        return minHeap.peek();
    }
    

    
    
    
    
    
}

























