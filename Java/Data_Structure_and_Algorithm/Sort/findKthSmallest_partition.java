// Find k-th smallest element in an array. 
// Author: kei + Coursera, "Mastering Software Engineering Interview", week 4
// (https://www.coursera.org/learn/cs-tech-interview/lecture/r6mFR/case-study-next-steps)
// Date  : November 25, 2016

package whiteboard;

import java.util.Random;

public class ForCopy {

    public static void main(String[] args) {

        // Test partition(). 
//        int[] nums = new int[]{ 2, 0, -1, 4, 6, 1, 3, 2};
//        int n = partition(nums, 0, nums.length - 1, 3);
//        System.out.println(n); // 5
//        System.out.println(Arrays.toString(nums)); // [2, 0, -1, 1, 2, 4, 3, 6] 
//        int[] nums2 = new int[]{ 2, 0, -1, 4, 6, 1, 3, 2};
//        n = partition(nums2, 0, nums.length - 1, 1);
//        System.out.println(n); // 2
//        System.out.println(Arrays.toString(nums2)); // [0, -1, 2, 4, 6, 1, 3, 2] 
//        System.out.println();

        // Test findKthSmallest(..). 
        // Sometimes causes stack overflow! I don't know why. 
//        int[] nums3 = new int[]{ 2, 0, -1, 4, 6, 1, 3, 2};
//        //k = 2;
//        int kthSmallest = findKthSmallest(nums3, 2);
//        System.out.println(kthSmallest); // 0 (Sometimes stack overflow.)
//        int[] nums4 = new int[]{ 2, 0, -1, 4, 6, 1, 3, 2};
//        //k = 3;
//        int num = findKthSmallest(nums4, 3);
//        System.out.println(num); // 1 (Sometimes stack overflow.)
        
        
        // Calculate median of an array. 
        int[] nums5 = new int[]{ 2, 0, -1, 4, 6, 1, 3, 2};
        int m1 = findKthSmallest(nums5, nums5.length / 2);
        int m2 = findKthSmallest(nums5, nums5.length / 2 - 1); 
        double median = ((double) m1 + (double) m2) / 2;
        System.out.println("m1: " + m1); // 2
        System.out.println("m2: " + m2); // 1
        System.out.println("median: " + median); // 1.5
        
        
        
        
        
        
        
        // Random integer between 0 and 2 inclusive. 
//      int r = rand.nextInt(3); 
//      System.out.println(r);

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    private static Random rand = new Random();
    //private static int k;
    
    public static int findKthSmallest(int[] array, int k) {
        if (k <= 0 || k > array.length) {
            throw new IllegalArgumentException();
        }
        return findKthSmallest(array, 0, array.length - 1, k);
    }
    private static int findKthSmallest(int[] array, int left, int right, int k) {
        int pivotIndex = rand.nextInt(right - left + 1);
        int pivot = array[pivotIndex];
        
        // Get the size of smaller values group. 
        int sizeSmall = partition(array, left, right, pivot) - left + 1;
        
        if (sizeSmall + 1 == k) {
            return pivot;
        } else if (sizeSmall + 1 > k) {
            return findKthSmallest(array, left, left + sizeSmall - 1, k);
        } else {
            // sizeSmall + 1 < k 
            //k -= sizeSmall;
            return findKthSmallest(array, left + sizeSmall, right, k - sizeSmall);
        }
    }
    // Split the array into two groups. 
    // One is less-than-pivot group and the other is
    // greater-than-or-equal-to-pivot group. 
    // Returns the last index of the less-than group. 
    // j is a pointer that points to the beginning of unknown group. 
    // i is a pointer that points to the last of the less-than group. 
    public static int partition(int[] array, int left, int right, int pivot) {
        int i = left - 1;
        for (int j = left; j < right + 1; j++) {
            if (array[j] < pivot) {
                i++;
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }
        // At this point, i indicates the last index of the less-than group. 
        return i;
    }

    
    
    
    
    
}

























