// Create a LRU (Least Recently Used) cache using 
// LinkedHashMap. 
// Author: EPI 13.3 p.197 + kei
// Date  : December 1, 2016

package whiteboard;

import java.util.LinkedHashMap;
import java.util.Map;


public class Lab_whiteboard {

    public static void main(String[] args) {

        int capacity = 5;
        
        LinkedHashMap<String, Integer> cache = 
                /* true: access order, false: insertion order. */
                new LinkedHashMap<String, Integer>(capacity, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> e) {
                return this.size() > capacity;
            }
        };
        
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.put("d", 4);
        cache.put("e", 5);
        System.out.println(cache.toString()); 
        // {a=1, b=2, c=3, d=4, e=5}
        
        cache.put("f", 6); // "a" is going to be deleted. 
        System.out.println(cache.toString()); 
        // {b=2, c=3, d=4, e=5, f=6}
        
        int n = cache.get("b"); // "b" is going to be at the end. 
        System.out.println(cache.toString()); 
        // {c=3, d=4, e=5, f=6, b=2}
        
        n = cache.put("c", 3);
        System.out.println(cache.toString()); 
        // {d=4, e=5, f=6, b=2, c=3}
        
        cache.put("g", 7);
        System.out.println(cache.toString()); 
        // {e=5, f=6, b=2, c=3, g=7}
        
        
        
        

        
        
        
        
        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
}

























