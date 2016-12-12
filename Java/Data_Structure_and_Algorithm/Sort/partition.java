// Split the list into three sublists before 'end' inclusive. 
// Pivot here is array[end]. O(N)
// 1. values less than the pivot. 
// 2. values equal to the pivot. 
// 3. values greater than the pivot. 

// Author: Aizu Book p.161 + kei
// Date  : November 25, 2016 

package whiteboard;

import java.util.Arrays;

public class ForCopy {

    public static void main(String[] args) {


        int[] nums = new int[]{ 2, 0, -1, 4, 6, 1, 3, 2};
        int n = partition1(nums, 0, nums.length - 1);
        System.out.println(n); // 4
        System.out.println(Arrays.toString(nums)); // [2, 0, -1, 1, 2, 4, 3, 6] 
        
        int[] nums2 = new int[]{ 7, 9, 2, 8, 0, 1, 10, 4};
        n = partition1(nums2, 0, nums.length - 1);
        System.out.println(n); // 3
        System.out.println(Arrays.toString(nums2)); // [2, 0, 1, 4, 9, 7, 10, 8]
        
        int[] nums3 = new int[]{ 7, 9, 2, 8, 0, 1, 10, 4};
        n = partition1(nums3, 0, 5);
        System.out.println(n); // 1
        System.out.println(Arrays.toString(nums3)); // [0, 1, 2, 8, 7, 9, 10, 4]

        

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    // Split the list into three sublists before 'end' inclusive. 
    // Pivot here is array[end].  O(N) 
    // 1. values less than the pivot. 
    // 2. values equal to the pivot. 
    // 3. values greater than the pivot. 
    // Author: Aizu Book p.161 + kei
    // Date  : November 25, 2016 
    public static int partition1(int[] array, int start, int end) {
        int pivotIndx = end; 
        int pivot = array[pivotIndx];
        
        // i is a pointer that points to the end of the less-than-or-equal-to group. 
        int i = start - 1;
        // j is a pointer that points to the beginning of unknown group. 
        for (int j = start; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                // Swap. 
                int t = array[i]; 
                array[i] = array[j];
                array[j] = t;
            }
            // array[j] > pivot 
            // just increment j, which is in the increment clause. 
        }
        // Swap the pivot with the i+1 index element. 
        int t = array[i + 1];
        array[i + 1] = array[end];
        array[end] = t;
        
        return i + 1;
    }
    
    

    
    
    
    
    
}

























