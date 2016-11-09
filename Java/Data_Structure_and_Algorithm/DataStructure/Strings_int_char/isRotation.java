// Check if the str2 is a rotation of str1.
// ex.
// str1: "waterbottle"
// str2: "erbottlewat"
// Author: Kei Fukutani
// Date  : October 18, 2016

package pack01;


public class Lab01 {

    public static void main(String[] args) {

        
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        System.out.println(isRotation(s1, s2)); // true
        
        
        System.out.println("done.");
        return;
    } // end of main().
    
    public static boolean isRotation(String s1, String s2) {
        int len = s1.length();
        if (len != s2.length() || len <= 0) {
            return false;
        }

        // Point!
        String s1s1 = s1 + s1;      
        return isSubstring(s1s1, s2);
    }
    
    // In the case that you are allowed to use the library. 
    public static boolean isSubstring(String s1, String s2) {
        return s1.indexOf(s2) != -1;
    }
    

//  // Pending... Look at 続ア学p.121 Boyer-Moore string search algorithm. 
//  // In the case that you are not allowed to use the library. 
//  // Author: kei
//  // Date  : October 18, 2016
//  public static boolean isSubstring(String s1, String s2) {
//      
//  
//  }
    
    
    
    
    
    
    
    
}

























