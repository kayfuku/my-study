// Find nearest repetition. 
// Author: EPI 13.6 p.200 + kei
// Date  : December 1, 2016

package whiteboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Lab_whiteboard {

    public static void main(String[] args) {

        
        String[] strings = {"a", "b", "c", "d", "b", "x", "b"};
        List<String> sList = new ArrayList<>(Arrays.asList(strings));
        System.out.println(findNearestRepetition(sList)); // 2 
        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    
    // Find nearest repetition.
    // O(N) time. O(d) space, where d is the number of distinct entries 
    // in the array. 
    // Author: EPI 13.6 p.200 + kei
    // Date  : December 1, 2016
    public static int findNearestRepetition(List<String> paragraph) {
        Map<String, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < paragraph.size(); i++) {
            String string = paragraph.get(i);
            if (map.containsKey(string)) {
                min = Math.min(min, i - map.get(string));
            }       
            map.put(string, i);
        }
        
        return min;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
}

























