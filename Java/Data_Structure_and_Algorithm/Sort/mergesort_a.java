// Mergesort. ** interview prep **
// Average case: O(N log N)
// Worst case  : O(N log N) (Faster than any other sorting algorithm.)
// Space       : O(N) (or O(1), which is called in-place mergesort.)
// Author: アルゴリズムを学ぼう p.75  + kei.
// Date  : September 27, November 3, 2016

package whiteboard;

import java.util.Arrays;

public class ForCopy {

    public static void main(String[] args) {
        
        int[] array = new int[]{12, 70, 812, 45, -1, 39, 1, 0, 5};          

        mergesort(array);
        
        System.out.println(Arrays.toString(array));
        // [-1, 0, 1, 5, 12, 39, 45, 70, 812]



        

    }

    public static void mergesort(int[] arr) {
        mergesort(arr, 0, arr.length);
    }
    // Sort between left and right.
    // right is one after the tail index.
    public static void mergesort(int[] arr, int left, int right) {
        // Base case.
        // If array size is one or less, sort is done.
        // right - left reduces. 
        if (right - left <= 1) return;

        // Divide the arr into two array, from left to mid
        // and from mid to right and then mergesort.
        int mid = left + (right - left) / 2;
        mergesort(arr, left, mid);
        mergesort(arr, mid, right);

        // Merge the two arrays. O(N)
        merge(arr, left, mid, right);
    }
    private static void merge(int[] arr, int left, int mid, int right) {
        // right - left reduces. 
        int n = right - left;
        // temp array.
        int[] temp = new int[n];

        // iLeft is head index of left array. 
        // iRight is head index of right array.
        // Two arrays are already sorted. 
        int iTemp = 0, iLeft = left, iRight = mid;

        // Pick up smaller one from the either head of two arrays. 
        // If it is same, pick up from the left array for stable sorting. 
        // Break the loop when either one is finished. 
        while (iLeft < mid && iRight < right) {
            if (arr[iLeft] <= arr[iRight]) {
                // Pick up from left array. 
                temp[iTemp] = arr[iLeft];
                iTemp++;
                iLeft++;
            } else {
                // Pick up from right array. 
                temp[iTemp] = arr[iRight];
                iTemp++;
                iRight++;
            }
        }

        // Put all of the rest into the temp array.
        // At this point, either of two array should be finished already
        // due to the while loop condition. 
        while (iLeft < mid) {
            temp[iTemp] = arr[iLeft];
            iTemp++;
            iLeft++;
        }
        while (iRight < right) {
            temp[iTemp] = arr[iRight];
            iTemp++;
            iRight++;
        }

        // Copy the temp array to the original array.
        System.arraycopy(temp, 0, arr, left, n);
    }


    

}

















