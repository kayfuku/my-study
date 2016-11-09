// Binary Search. (array needs to be sorted in advance.)
// Iterative version. More efficient than recursive version. 
// Average Case : O(log N)

// Author: PIE p.113 + kei.
// Date  : November 2, 2016

package whiteboard;

import java.util.Arrays;

public class ForCopy {

    public static void main(String[] args) {
        
        int[] array = new int[]{12, 70, 812, 45, -1, 39, 1, 0, 5};  
        Arrays.sort(array);
        System.out.println(Arrays.toString(array)); // [-1, 0, 1, 5, 12, 39, 45, 70, 812]

        System.out.println(binarySearchIter(array, 45)); // 6

        

    }

    // Author: PIE p.113 + kei.
    // Date  : November 2, 2016
    public static int binarySearchIter(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1;
        int mid;

        while (left <= right) {
            // This could be 'left + (right - left) / 2;', which can 
            // prevent from integer overflow, but this is more readable. 
            mid = (left + right) / 2;

            if (x == arr[mid]) {
                return mid;
            } else if (x < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // Not found. 
        return -1;
    }


    // Author: Cracking Coding Interview p.122  + kei.
    // Date  : February 11, 2016
    int binarySearchIterative(int[] array, int x) {
        int low = 0;
        int high = array.length - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;
            if (array[mid] < x) {
                // Search the right side.
                low = mid + 1;
            } else if (array[mid] > x) {
                // Search the left side. 
                high = mid - 1;
            } else {
                // Found x. 
                return mid;
            }
        }

        // Not found.
        return -1;
    }




    

}

















