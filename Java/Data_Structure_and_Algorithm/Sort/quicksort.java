// Quicksort. ** interview prep **
// Average case: O(N log N)
// Worst case  : O(N^2) (almost sorted) 
// Space       : O(log N)
// Author: Cracking Coding Interview p.121 + kei.
// Date  : February 11, 2016

package whiteboard;

import java.util.Arrays;

public class ForCopy {

    public static void main(String[] args) {
        
        int[] array = new int[]{12, 70, 812, 45, 39, 1, 0, 5};

        quicksort(array, 0, array.length - 1);
        
        System.out.println(Arrays.toString(array)); 
        // [0, 1, 5, 12, 39, 45, 70, 812]
        
        
        

    }

    public static void quicksort(int[] array, int left, int right) {

        int index = partition(array, left, right);

        // If the half part size is 1, it's a base case doing nothing.
        // Sort the left half part.
        if (left < index - 1) {
            quicksort(array, left, index - 1);
        }
        // Sort the right half part.
        if (index < right) {
            quicksort(array, index, right);
        }
    }
    private static int partition(int[] array, int left, int right) {

        // Pick up a pivot.
        int pivot = array[(left + right) / 2];

        while (left <= right) {
            // Find a value in the left half part, which is greater
            // than pivot and should be in the right half part.
            // But I suspect '<'... and '>' below.
            // Either of left and right stops at the pivot index. 
            while (array[left] < pivot) {
                left++;
            }

            // Find a value in the right half part, which should be
            // in the left half part.
            while (array[right] > pivot) {
                right--;
            }

            // Swap them.
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }

        return left;
    }
    private static void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }
    
    

}

















