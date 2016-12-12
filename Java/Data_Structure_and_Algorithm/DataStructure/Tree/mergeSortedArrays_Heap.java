// Merge sorted arrays. 
// Author: EPI 11.1 p.162 + kei
// Date  : November 28, 2016

package whiteboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;




public class Lab_whiteboard {

    public static void main(String[] args) {

        Integer[] nums1 = new Integer[]{3, 5, 7};
        Integer[] nums2 = new Integer[]{0, 6};
        Integer[] nums3 = new Integer[]{0, 6, 28};
        List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(nums1));
        List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(nums2));
        List<Integer> list3 = new ArrayList<Integer>(Arrays.asList(nums3));
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        
        // Test mergeSortedArrays(). 
        List<Integer> result = mergeSortedArrays(lists);
        System.out.println(result.toString()); // [0, 0, 3, 5, 6, 6, 7, 28]
        
        
        
        
        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        // Min-Heap. 
        PriorityQueue<ArrayEntry> minHeap = 
                new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, new Comparator<ArrayEntry>() {
                    @Override
                    public int compare(ArrayEntry o1, ArrayEntry o2) {
                        return Integer.compare(o1.value, o2.value);
                    }
                });
        
        // For managing pointers that point to the head of array. 
        List<Integer> pointers = new ArrayList<>(sortedArrays.size());
        
        // Fill the heap with the head element of each array. 
        for (int i = 0; i < sortedArrays.size(); i++) {
            if (sortedArrays.get(i).size() > 0) {
                minHeap.add(new ArrayEntry(sortedArrays.get(i).get(0), i));
                // Set the pointer to 1(the second element of the array).
                pointers.add(1);
            } else {
                pointers.add(0);
            }
        }
        
        List<Integer> result = new ArrayList<>(); 
        ArrayEntry headEntry;
        while ((headEntry = minHeap.poll()) != null) {
            result.add(headEntry.value);
            // Get the pointer. 
            int p = pointers.get(headEntry.arrayId);
            List<Integer> smallestArray = sortedArrays.get(headEntry.arrayId);
            if (p < smallestArray.size()) {
                // Add the next element in the array to the heap. 
                minHeap.add(new ArrayEntry(smallestArray.get(p), headEntry.arrayId));
                // Advance the pointer. 
                pointers.set(headEntry.arrayId, p + 1);
            }
        }
        
        return result;
    }
    
    
    

    
    
    
    
    
}

class ArrayEntry {
    public Integer value;
    public Integer arrayId;
    
    public ArrayEntry(Integer value, Integer arrayId) {
        this.value = value;
        this.arrayId = arrayId;
    }
}























