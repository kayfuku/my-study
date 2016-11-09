// Find the largest and smallest number in a list. 
// The list is sorted as two sections, one in ascending order 
// and the other in descending order. 
// input: [2 3 4 5 6 7 10 9 8 7]
// smallest: 2
// largest: 10
// (CareerCup Google uno Sep/29/16)


package pack01;

public class Lab01 {

    public static void main(String[] args) {        
        
        //int[] arr = new int[]{2, 3, 4, 5, 6, 7, 10, 9, 8, 7};
        //int[] arr = new int[]{2, 3};
        //int[] arr = new int[]{2};
        //int[] arr = new int[]{2, 3, 4, 5, 6, 7, 10, 11};
        //int[] arr = new int[]{2, 3, 4, 5, 6, 7, 10, 11, 8};
        //int[] arr = new int[]{50, 34, 33, 21, 15, 5, 3, 2, 1};
        //int[] arr = new int[]{34, 50, 33, 21, 15, 5, 3, 2, 1};
        int[] arr = new int[]{0, 4, 3, 2, 1};
        System.out.println(findSummit(arr));
        //System.out.println(findSummitRecur(arr));
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end of main().

    
    // Solution 1. Iteration.
    // Author: anonymous in CareerCup Sep/29/16 + kei.
    // Date  : October 8-9, 2016
    public static int findSummit(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length == 2) {
            return arr[0] > arr[1] ? arr[0] : arr[1];
        }
        
        int left = 0;
        int right = arr.length - 1;
        // Compute middle index because it is used on the while
        // condition. 
        int mid = left + (right - left) / 2;;
        
        while (/* Avoid index out of bound. 
                  This will happen before left > right. */
               mid - 1 != -1 && mid + 1 != arr.length) {
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                // Summit.
                return arr[mid];
            } else if (arr[mid] > arr[mid - 1]) {
                // Search the right.
                left = mid + 1;
                // left = mid;
            } else {
                // Search the left.
                right = mid - 1;
                //right = mid;              
            }
            
            mid = left + (right - left) / 2;
        }

        // Summit is near head or tail.
        return (mid == 0) ? Math.max(arr[0], arr[1]) : arr[mid];
        
        
//      int left = 0;
//      int right = arr.length - 1;
//      int mid = left + (right - left) / 2;
//      
//      while (/* Avoid out of bound. */ 
//             mid - 1 != -1 && mid + 1 != arr.length 
//             /* Loop during not on the summit. */
//             && !(arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1])) {
//          if (arr[mid] > arr[mid - 1]) {
//              // Search the right.
//              left = mid + 1;
//              // left = mid;
//          } else {
//              // Search the left.
//              right = mid - 1;
//              //right = mid;              
//          }
//          
//          mid = left + (right - left) / 2;
//      }
//
//      return (mid == 0) ? Math.max(arr[0], arr[1]) : arr[mid];
        
    }
    
    
    // Solution 2. Recursion. 
    // Author: kei.
    // Date  : October 9, 2016
    public static int findSummitRecur(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length == 2) {
            return (arr[0] > arr[1]) ? arr[0] : arr[1];
        }
        
        return findSummitRecur(arr, 0, arr.length - 1);
    }
    
    public static int findSummitRecur(int[] arr, int left, int right) {
        // I don't know if this is necessary, but think about it lator.
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        // Avoid index out of bound. 
        if (mid == 0) {
            return Math.max(arr[0], arr[1]);
        }
        if (mid == arr.length - 1) {
            return arr[mid];
        }
        
        // Recursion.
        if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
            // Base case. 
            // Summit. 
            return arr[mid];
        } else if (arr[mid] > arr[mid - 1]) {
            return findSummitRecur(arr, mid + 1, right);            
        } else {
            return findSummitRecur(arr, left, mid - 1);
        }   
        
    }



    
    
    
}




















