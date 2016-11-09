// Check if the string is palindromic. 
// Author: EPI p.84 + kei
// Date  : November 8, 2016

package whiteboard;

public class Lab_whiteboard {

    public static void main(String[] args) {

        
        String s1 = "abcba";
        String s2 = "abccba";
        String s3 = "abcda";
        
        // Test isPalindromic().
        System.out.println(isPalindromic(s1)); // true
        System.out.println(isPalindromic(s2)); // true
        System.out.println(isPalindromic(s3)); // false
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end of main().
    

    // Check if the string is palindromic. 
    // O(N) time, O(1) space.
    // Author: EPI p.84 + kei
    // Date  : November 8, 2016
    public static boolean isPalindromic(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;    
    }
















    
    
    
    
    
}

























