// Check if two arrays have the same relative order of values. 
// ex.
// { 5, 1, 3, 4, 2 } and { 15, 11, 13, 14, 12 } are same. 
// { 5, 1, 3, 4, 2 } and { 15, 11, 13, 12, 14 } are not same. 
// Author: kei
// Date  : November 15, 2016 

package whiteboard;

import java.util.ArrayList;
import java.util.TreeMap;


public class Lab_whiteboard {

    public static void main(String[] args) {

        
        int[] array1 = new int[]{ 5, 1, 3, 4, 2 };
        int[] array2 = new int[]{ 15, 11, 13, 14, 12 };
        int[] array3 = new int[]{ 15, 11, 13, 12, 14 };
        
        boolean b = isSameRelativeOrder(array1, array2);
        System.out.println(b); // true
        b = isSameRelativeOrder(array1, array3);
        System.out.println(b); // false 
        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    

    public static boolean isSameRelativeOrder(int[] array1, int[] array2) {
        
        ArrayList<Integer> aList1 = getRelativeOrder(array1);
        ArrayList<Integer> aList2 = getRelativeOrder(array2);

        for (int i = 0; i < aList1.size(); i++) {
            if (aList1.get(i) != aList2.get(i)) {
                return false;
            }
        }
        
        return true;    
    }
    
    private static ArrayList<Integer> getRelativeOrder(int[] array) {
        TreeMap<Integer, Integer> tMap = new TreeMap<>();
                
        for (int i = 0; i < array.length; i++) {
            // Sort by the element value. 
            tMap.put(array[i], i);
        }
        
        // Get tree map values in ascending order of the keys. 
        // Make it ArrayList to access the values by index. 
        ArrayList<Integer> ret = new ArrayList<>(tMap.values());
        
        return ret;
    }
    
    
    
    
}

























