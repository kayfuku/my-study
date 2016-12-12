// Convert String to int and vice versa.  
// Author: EPI p.86 + kei
// Date  : November 8, 2016

package whiteboard;

public class Lab_whiteboard {

    public static void main(String[] args) {

        
        // Test intToString().
        int n = 413;
        System.out.println(intToString(n)); // 413
        
        // Test stringToInt().
        String s = "123";
        System.out.println(stringToInt(s)); // 123
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end of main().
    

    public static String intToString(int x) {
        boolean isNegative = false;
        if (x < 0) {
            x = -x;
            isNegative = true;
        }
        
        StringBuilder sb =  new StringBuilder();
        do {
            // x % 10: to get least significant digit. 
            // + '0': to get char code. 
            // Do not sb.append((char) (x % 10) + '0'));
            sb.append((char) (x % 10 + '0'));
            x /= 10;
        } while (x != 0);
        
        if (isNegative) {
            sb.append('-');
        }
        sb.reverse();
        
        return sb.toString();
    }

    // It only works if the string consists of an optional negative 
    // sign followed by digits. 
    // The number is too big, the result will be incorrect due to overflow. 
    public static int stringToInt(String s) {
        boolean isNegative = s.charAt(0) == '-';
        int result = 0;
        for (int i = (isNegative ? 1 : 0); i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            result = result * 10 + digit;
        }
        
        return isNegative ? -result : result;
    }










    
    
    
    
    
}

























