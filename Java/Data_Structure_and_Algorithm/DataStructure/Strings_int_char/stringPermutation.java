// Get string permutation. 
// ex. "abc"
// abc, acb, bac, bca, cab, cba

package whiteboard;

import java.util.ArrayList;
import java.util.HashSet;

public class ForCopy {

    public static void main(String[] args) {
        
        ArrayList<String> perms = null;
        
        String str = "abc";
        String str2 = "aaa";
        String str3 = "aab";
        String str4 = "aaab";
        String str5 = "xyz";
        
        // Test solution 1 or 2.
        perms = getPerms(str);
        //System.out.println(perms);  // [abc, bac, bca, acb, cab, cba]
        perms = getPerms2(str);   
        //System.out.println(perms);  // [abc, acb, bac, bca, cab, cba]
        perms = getPerms2(str2);  
        //System.out.println(perms);  // [aaa, aaa, aaa, aaa, aaa, aaa]
        perms = getPerms2(str3);  
        //System.out.println(perms);  // [aab, aba, aab, aba, baa, baa]
        perms = getPerms2(str4);  
        //System.out.println(perms);  // [aaab, aaba, aaab, ... , baaa, baaa]
        
        // Test solution 3. 
        //getPerm3(string);
        
        // Test getPerms2a(). 
        perms = getPerms2a(str); 
        //System.out.println(perms);  // [abc, acb, bac, bca, cab, cba]
        perms = getPerms2a(str2); 
        //System.out.println(perms);  // [aaa]
        perms = getPerms2a(str3); 
        //System.out.println(perms);  // [aab, aba, baa]
        perms = getPerms2a(str4); 
        //System.out.println(perms);  // [aaab, aaba, abaa, baaa]

        // Test getCombinations().
        ArrayList<String> combinations = getCombinationsOfString(str5); 
        System.out.println(combinations); // [x, xy, xyz, xz, y, yz, z]

        
        
        
        
    }
    
    
    // Solution 2 + α 
    // What if there are dups in the string? 
    // P(a1 a2 a3 a4) = 
    //     {a1 + P(a2 a3 a4)}
    //   + {a2 + P(a1 a3 a4)}
    //   + {a3 + P(a1 a2 a4)}
    //   + {a4 + P(a1 a3 a3)}
    // Author: kei
    // Date  : November 2, 2016
    public static ArrayList<String> getPerms2a(String str) {
        int len = str.length();
        // These variables are declared on each layer in the call stack. 
        // Each layer corresponds to the position of the string. 
        ArrayList<String> perms = new ArrayList<String>();
        HashSet<Character> used = new HashSet<>();
        
        // Base case.
        if (len == 0) {
            perms.add("");
            return perms;
        }
        
        for (int i = 0; i < len; i++) {
            if (used.contains(str.charAt(i))) {
                continue;           
            } else {
                // Get permutation of word that has been removed i-th char.
                // substring(0, 0) returns empty string. 
                String before = str.substring(0, i);
                String after = str.substring(i + 1, len);
                
                // before + after reduces the size of problem. 
                ArrayList<String> permsOfRemainder = getPerms2a(before + after); 
                            
                // Prepend i-th char to each permutation. 
                for (String s : permsOfRemainder) {
                    perms.add(str.charAt(i) + s);
                }
                
                // Avoid using the same character on the same position. 
                used.add(str.charAt(i));
            }
        } // end for (...)
        
        return perms;
    }
        

    // ★ Need to review!
    // Get combinations of a string.  
    // ex. "xyz"
    //     x, y, z, xy, yz, zx, xyz
    //     > C(xyz) = {x + C(yz)}
    //              = {x + {y + C(z)}}
    //              = {x + {y + {z + C("")}}}
    // Author: PIE p.118 + The internet + kei
    // Date  : November 2, 2016
    public static ArrayList<String> getCombinationsOfString(String str) {
        ArrayList<String> combs = new ArrayList<>();
        getCombinationsOfString(str, new StringBuilder(), 0, combs);
        return combs;
    }
    // Since we need to generate combinations, we can start with a single character 
    // and then continue to add a character to combinations we have seen so far. 
    private static void getCombinationsOfString(
            String inStr, StringBuilder outStr, int index, ArrayList<String> combs) {
        for (int i = index; i < inStr.length(); i++) {
            outStr.append(inStr.charAt(i));
            combs.add(outStr.toString());
            getCombinationsOfString(inStr, outStr, i + 1, combs);
            outStr.deleteCharAt(outStr.length() - 1);
        }       
    }
    
    
    // Solution 1.
    // Author: CtCI 6th 8.7 p.355~ + kei
    // Date  : October 11, 2016
    public static ArrayList<String> getPerms(String str) {
        if (str == null) {
            return null;
        }

        ArrayList<String> perms = new ArrayList<String>();
        // Base case.
        if (str.length() == 0) {
            perms.add("");
            return perms;
        }

        char c = str.charAt(0);
        // Reduce the size of problem.
        String remainder = str.substring(1);
        // Get (n - 1) case. 
        ArrayList<String> permsOfRemainder = getPerms(remainder);
        // Get n case.
        for (String word : permsOfRemainder) {
            for (int j = 0; j <= word.length(); j++) {
                String s = insertCharAt(word, c, j);
                perms.add(s);
            }
        }

        return perms;
    }
    // Insert char c at index i in word.
    private static String insertCharAt(String word, char c, int i) {
        String before = word.substring(0, i);
        String after = word.substring(i);
        return before + c + after;      
    }

    
    // Solution 2. I like it. 
    // P(a1 a2 a3 a4) = 
    //     {a1 + P(a2 a3 a4)}
    //   + {a2 + P(a1 a3 a4)}
    //   + {a3 + P(a1 a2 a4)}
    //   + {a4 + P(a1 a3 a3)}
    // Author: CtCI 6th 8.7 p.355~ + kei
    // Date  : October 11, 2016
    public static ArrayList<String> getPerms2(String str) {
        int len = str.length();
        ArrayList<String> perms = new ArrayList<String>();
        
        // Base case.
        if (len == 0) {
            perms.add("");
            return perms;
        }
        
        for (int i = 0; i < len; i++) {
            // Get permutation of word that has been removed i-th char.
            // substring(0, 0) returns empty string. 
            String before = str.substring(0, i);
            String after = str.substring(i + 1, len);
            
            // before + after reduces the size of problem. 
            ArrayList<String> permsOfRemainder = getPerms2(before + after); 
            
            // Prepend i-th char to each permutation. 
            for (String s : permsOfRemainder) {
                perms.add(str.charAt(i) + s);
            }
        }
        
        return perms;
    }

    
    // Solution 3. I don't really understand this. 
    // Author: CtCI 6th 8.7 p.355~ + kei
    // Date  : October 11, 2016    
    public static void getPerm3(String str) {
        getPerm3(str, "");
    }
    private static void getPerm3(String str, String prefix) {
        //System.out.println("gerPerm() called.");
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String remainder = str.substring(0, i) + str.substring(i + 1);
                getPerm3(remainder, prefix + str.charAt(i));
            }
        }       
    }
    
    

}





















