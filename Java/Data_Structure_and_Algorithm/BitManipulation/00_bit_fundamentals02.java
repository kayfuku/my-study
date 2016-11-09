// Fundamentals about how bit works. 
// Author: kei

package whiteboard;

public class Lab_whiteboard {

    public static void main(String[] args) {

        // Test modulo2ToPowerOf()
//      modulo2ToPowerOf(34);
//      x % 2: 0
//      x % 4: 2
//      x % 8: 2
//      x % 32: 2
        
        // -1 is all ones. 
        //System.out.println(Integer.toBinaryString(-1)); 
        // 11111111111111111111111111111111 (32 ones.)
        
        // Test bitIterate()
        System.out.println(Integer.toBinaryString(4638201)); //10001101100010111111001
        bitIterate(4638201); // 10011111101000110110001000000000
 
        
        
        
        
        
        
        
        
        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end of main().
    
    // Modulo of power of 2. 
    // Author: the internet + kei
    // Data  : November 4, 2016
    public static void modulo2ToPowerOf(int x) {
        System.out.println("x % 2: " + (x & 1));
        System.out.println("x % 4: " + (x & 3));
        System.out.println("x % 8: " + (x & 7));
        System.out.println("x % 32: " + (x & 0x1F));

    }
    
    // Bit digit iterate pattern. 
    // For the problem that it doesn't matter how significant the digit is. 
    // It only matters which the value is 0 or 1.  
    // Author: CtCI p.279 + kei
    // Data  : November 6, 2016
    public static void bitIterate(int n) {
        for (int i = 0; i < Integer.BYTES * 8; i++) {
            // Do something. 
            System.out.print(n & 1);
            
            // Logical right shift. 
            n >>>= 1;           
        }
    }
    public static void bitIterate2(int n) {
        while (n != 0) {
            if ((n & 1) == 1) {
                // If the current bit is 1, do something. 
            } else {
                // If the current bit is 0, do something. 
            }
            
            n >>>= 1;
        }
    }





















    
    
    
    
    
}

























