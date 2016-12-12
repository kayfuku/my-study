// Check if the characters in magazine can cover characters in letter. 
// Author: EPI 13.2 p.196 + kei
// Date  : December 1, 2016

package whiteboard;

import java.util.HashMap;



public class Lab_whiteboard {

    public static void main(String[] args) {

        
        String letter = "Hello, world.";
        String magazine = "AboHcdldfebao w,cuitolppqrl.";
        
        boolean b = isLetterConstructibleFromMagazine(letter, magazine);
        System.out.println(b); // true 
        
        
        
        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    // O(m + n), where m and n are the number of characters in the letter and magazine. 
    public static boolean isLetterConstructibleFromMagazine(String letter, String magazine) {
        // Count the number of characters in the letter. 
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < letter.length(); i++) {
            char c = letter.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        
        // Check if the characters in magazine can cover characters in letter. 
        for (char c : magazine.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    map.remove(c);
                    if (map.isEmpty()) {
                        return true;
                    }
                }
            }           
        }
        
        return false;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

























