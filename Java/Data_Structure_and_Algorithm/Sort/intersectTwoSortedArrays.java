// Find the intersection of two sorted arrays.
// Author: EPI 14.1 p.218 + kei
// Date  : November 16, 2016

package whiteboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lab_whiteboard {

    public static void main(String[] args) {

        
        Integer[] a = new Integer[]{ 2, 3, 3, 5, 7, 11 };
        Integer[] b = new Integer[]{ 3, 3, 7, 15, 31 };
        
        //System.out.println(intersectTwoSortedArraysBad(a, b)); // out of bound. 
        
        
        List<Integer> A = new ArrayList<Integer>(Arrays.asList(a));
        List<Integer> B = new ArrayList<Integer>(Arrays.asList(b));
        
        System.out.println(intersectTwoSortedArrays1(A, B).toString()); // [3, 7]
        System.out.println(intersectTwoSortedArrays2(A, B).toString()); // [3, 7] 

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    
    // O(M + N) solution, 
    // where M is the length of B and N is the length of A. 
    // Author: EPI 14.1 p.218 + kei
    // Date  : November 16, 2016
    public static List<Integer> intersectTwoSortedArrays1(
            List<Integer> A, List<Integer> B) {
        List<Integer> intersectionAB = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < A.size() && j < B.size()) {
            if (A.get(i) == B.get(j) 
                && (i == 0 /* Avoid out of bound. */
                   || A.get(i) != A.get(i - 1)) /* Avoid duplicates. */) {
                intersectionAB.add(A.get(i));
                i++;
                j++;
            } else if (A.get(i) < B.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        
        return intersectionAB;
    }
    
    
    // O(N log M) solution, 
    // where M is the length of B and N is the length of A. 
    // Author: EPI 14.1 p.218 + kei
    // Date  : November 16, 2016
    public static List<Integer> intersectTwoSortedArrays2(List<Integer> A, 
                                                          List<Integer> B) {
        List<Integer> intersectionAB = new ArrayList<>();
        
        for (int i = 0; i < A.size(); i++) {
            // binarySearch() returns -1 if the key is not there. 
            if ((i == 0 /* Avoid out of bound. */
                || A.get(i) != A.get(i - 1)) /* Avoid duplicates. */
                && Collections.binarySearch(B, A.get(i)) >= 0) {
                intersectionAB.add(A.get(i));
            }
        }
        
        return intersectionAB;  
    }
    
    
    // Bad code. 
    // Author: kei
    // Date  : November 16, 2016
    public static HashSet<Integer> intersectTwoSortedArraysBad(Integer[] a, Integer[] b) {
        HashSet<Integer> res = new HashSet<>();
        int p1 = 0, p2 = 0;
        
        while (p1 < a.length && p2 < b.length) {
            while (a[p1] > b[p2]) { // Could be array index out of bound. 
                if (a[p1] == b[p2]) {
                    res.add(a[p1]);
                }
                p1++;
            }
            while (b[p2] > a[p1]) {
                if (b[p2] == a[p1]) {
                    res.add(b[p2]);
                }
                p2++;
            }
        }
        
        return res;
    }
    
    

    
    
    
    
    
}

























