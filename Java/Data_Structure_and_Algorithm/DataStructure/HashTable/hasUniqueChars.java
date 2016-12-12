// Check if a string has all unique chars.

package whiteboard;

import java.util.HashSet;



public class Lab_whiteboard {

    public static void main(String[] args) {

        
        
        String string = "abcdefg";
        System.out.println(hasUniqueChars4(string)); // true
        String string2 = "abcdeeg";
        System.out.println(hasUniqueChars4(string2)); // false 
        
        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    // With Bit Vector, assuming that character set is 
    // lowercase alphabets. 
    // Author: CtCI 1.1 p.193 + kei
    // Date  : December 6, 2016
    public static boolean hasUniqueChars4(String s) {
        // Bit Vector. 
        int checker = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'a';
            // Not '== 1'!
            // 0010 & 0010 --> 0010 != 1
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);          
        }
        
        return true;            
    }

    // With Hash Table, assuming that character code is Unicode. 
    // O(N) time. 
    // O(c) space, where c is the size of the character set.  
    // Author: kei
    // Date  : December 6, 2016
    public static boolean hasUniqueChars3(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.contains(c)) {
                set.add(c);
            } else {
                return false;
            }
        }
        
        return true;    
    }

    // With Array, assuming that character code is reasonbale ASCII
    // which does not include extended ASCII. 
    // O(N) time. (or could be O(1)) 
    // O(1) space. 
    // Author: CtCI 1.1 p.192 + kei
    // Date  : December 6, 2016
    public static boolean hasUniqueChars2(String s) {
        if (s.length() > 128) {
            return false;
        }
        // Not char[]!
        boolean[] charSet = new boolean[128];
        
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i);
            if (charSet[val]) {
                return false;
            }
            charSet[val] = true;
        }
        
        return true;        
    }
    
    // With Array, assuming that character code is ASCII.
    // Author: Cracking the Coding Interview p.172 + kei.
    // Date  : February 14, 2016
    public boolean hasUniqueChars(String str) {
        if (str.length() > 256) {
            return false;
        }

        boolean[] checker = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (checker[c]) {
                return false;
            }
            checker[c] = true;
        }

        return true;
    }
    
}

























