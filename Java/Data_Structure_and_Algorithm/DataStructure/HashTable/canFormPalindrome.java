// Check if a string can be permuted to form a palindrome. 
// O(N) time, where N is the length of the string.
// O(c) space, where c is the number of distinct characters 
// appearing in the string.  
// Author: EPI 13.1 p.194 + kei
// Date  : November 17, 2016

package whiteboard;

import java.util.HashMap;
import java.util.Map;


public class Lab_whiteboard {

    public static void main(String[] args) {

        
        String string = "affhhaikiok";
        boolean b = canFormPalindrome(string);
        System.out.println(b); // true 
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    

    public static boolean canFormPalindrome(String s) {
        Map<Character, Integer> charFrequenciesMap = new HashMap<>();
        // Compute the frequency of each char in s. 
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!charFrequenciesMap.containsKey(c)) {
                charFrequenciesMap.put(c, 1);
            } else {
                charFrequenciesMap.put(c, charFrequenciesMap.get(c) + 1);
            }
        }
        
        // A string can be permuted to form a palindrome if and only if 
        // the number of chars whose frequencies is odd is at most 1. 
        int oddCount = 0;
        for (Map.Entry<Character, Integer> entry : charFrequenciesMap.entrySet()) {
            if (entry.getValue() % 2 != 0 && ++oddCount > 1) {
                return false;               
            }                       
        }
        
        return true;                
    }

    
    
    
    
    
    
    
    
    
    
    
}

























