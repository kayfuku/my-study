// TreeMap demo.
// Author: kei
// Date  : November 15, 2016 

package whiteboard;

import java.util.ArrayList;
import java.util.TreeMap;




public class Lab_whiteboard {

    public static void main(String[] args) {

        
        int[] array = new int[]{ 5, 1, 3, 4, 2 };

        TreeMap<Integer, Integer> tMap = new TreeMap<>();
        for (int i = 0; i < array.length; i++) {
            // Sort by the element value. 
            tMap.put(array[i], i);
        }
        
        // Get values in ascending order of keys. 
        for (int v : tMap.values()) {
            System.out.println(v);
        }
        
        // If you want to access the values by index. 
        ArrayList<Integer> ret = new ArrayList<>(tMap.values());
        
        
        
        

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    

    
    
    
    
    
}

























