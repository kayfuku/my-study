// Fundamentals about how bit works. 
// Author: Cracking the Coding Interview p.91 + kei
// Data  : October 2, 2016


package pack01;

public class Bit01
{

    public static void main(String[] args)
    {
        
        
        // How bit shift works in negative number and positive number.
        int s = 10;
        int x = 2000000000;
        int y = -2000000000;
        // 32th digit is a sign bit(?)
        // If you put 32 digits in like "10...0", an exception occurs. 
        // So this example has 31 digits.
        int numOnly31thDigitIs1 = Integer.parseInt(
                "1000000000000000000000000000000", 2);  
        
        System.out.println("--- positive number: ");
        System.out.println(Integer.toBinaryString(x) + " (31 digits)");
        System.out.println("30th digit is " + getBit(x, 29)); 
        System.out.println("31th digit is " + getBit(x, 30)); 
        System.out.println("32th digit is " + getBit(x, 31)); 
        
        System.out.println("--- one bit right shift");
        x = x >> 1;
        System.out.println(Integer.toBinaryString(x)); 
        System.out.println("30th digit is " + getBit(x, 29)); 
        System.out.println("31th digit is " + getBit(x, 30));
        System.out.println("32th digit is " + getBit(x, 31));

        System.out.println();
        System.out.println("--- negative number: ");
        System.out.println(Integer.toBinaryString(y) + " (32 digits)");
        System.out.println("30th digit is " + getBit(y, 29)); 
        System.out.println("31th digit is " + getBit(y, 30)); 
        System.out.println("32th digit is " + getBit(y, 31)); 
        System.out.println("--- one bit right shift");
        y = y >> 1;
        System.out.println("You can see 1 which is the same as the sign bit is put in the 32th digit.");
        System.out.println(Integer.toBinaryString(y) + " (32 digits)"); 
        System.out.println("30th digit is " + getBit(y, 29)); 
        System.out.println("31th digit is " + getBit(y, 30));
        System.out.println("32th digit is " + getBit(y, 31) + " < Here!");
        
        System.out.println();
        System.out.println("--- numOnly31thDigitIs1: ");
        System.out.println(Integer.toBinaryString(numOnly31thDigitIs1) + " (31 digits)");
        System.out.println("30th digit is " + getBit(numOnly31thDigitIs1, 29));
        System.out.println("31th digit is " + getBit(numOnly31thDigitIs1, 30));
        System.out.println("32th digit is " + getBit(numOnly31thDigitIs1, 31));
        System.out.println("--- one bit right shift");
        numOnly31thDigitIs1 = numOnly31thDigitIs1 >> 1;
        System.out.println(Integer.toBinaryString(numOnly31thDigitIs1));
        System.out.println("30th digit is " + getBit(numOnly31thDigitIs1, 29));
        System.out.println("31th digit is " + getBit(numOnly31thDigitIs1, 30));
        System.out.println("32th digit is " + getBit(numOnly31thDigitIs1, 31));
        
        System.out.println();
        System.out.println("--- max value of int");
        System.out.println("2^31 = " + (int) Math.pow(2, 31) + " (This is not correct.)");
        System.out.println("Interger.MAX_VALUE: " + Integer.MAX_VALUE + " (= 2^31 - 1)");
        System.out.println("Interger.MAX_VALUE + 1: " + (Integer.MAX_VALUE + 1) + " (MIN_VALUE)");
        System.out.println("2^2 = " + (int) Math.pow(2, 2));
        int numOnly3thDigitIs1 = Integer.parseInt("100", 2);  
        System.out.println("numOnly3thDigitIs1: " + numOnly3thDigitIs1);    
        System.out.println("2^30 = " + (int) Math.pow(2, 30));
        numOnly31thDigitIs1 = Integer.parseInt("1000000000000000000000000000000", 2); 
        System.out.println("numOnly31thDigitIs1: " + numOnly31thDigitIs1);    
        
        System.out.println();
        System.out.println("--- min value of int");
        System.out.println("-2^30 = " + ((int) Math.pow(2, 30)) * (-1));
        System.out.println("-2^31 = " + ((int) Math.pow(2, 30)) * (-1) * 2);
        System.out.println("-2^31 - 1 = " + (((int) Math.pow(2, 30)) * (-1) * 2 - 1) + " (This is not correct.)");
        System.out.println("-2^32 = " + ((int) Math.pow(2, 30)) * (-1) * 2 * 2 + " (This is not correct.)");
        System.out.println("Interger.MIN_VALUE: " + Integer.MIN_VALUE + " (= -2^31)");
        System.out.println("Interger.MIN_VALUE - 1: " + (Integer.MIN_VALUE - 1) + " (MAX_VALUE)");

        System.out.println();

        // How bit shift works in negative number and positive number.
        System.out.println("13 (" + Integer.toBinaryString(13) + ") >> 2 = " + 
                           (13 >> 2) + " (" + Integer.toBinaryString(13 >> 2) + ")");
        System.out.println("-13 (" + Integer.toBinaryString(-13) + ") >> 2 " + "\n = " + 
                           (-13 >> 2) + " (" + Integer.toBinaryString(-13 >> 2) + ")");
        System.out.println();
        
        // Test getBit().
        System.out.println("Test getBit().");
        int num = 4; // 0100
        System.out.println(Integer.toBinaryString(num));
        System.out.println(getBit(num, 2));

        // Test setBit().
        System.out.println("Test setBit().");
        System.out.println(Integer.toBinaryString(num));
        num = setBit(num, 1);
        System.out.println(Integer.toBinaryString(num));
        
        // Test clearBit().
        System.out.println("Test clearBit().");
        System.out.println(Integer.toBinaryString(num));
        num = clearBit(num, 1);
        System.out.println(Integer.toBinaryString(num));
        
        // Test clearBitsMSBthroughI().
        System.out.println("Test clearBitsMSBthroughI().");
        num = 255;
        System.out.println(Integer.toBinaryString(num));
        num = clearBitsMSBthroughI(num, 2);
        System.out.println(Integer.toBinaryString(num));
        
        // Test clearBitsIthroughLSB().
        System.out.println("Test clearBitsIthroughLSB().");
        num = 15;
        System.out.println(Integer.toBinaryString(num));
        num = clearBitsIthroughLSB(num, 2);
        System.out.println(Integer.toBinaryString(num));
        
        // Test updateBit().
        System.out.println("Test updateBit().");
        System.out.println(Integer.toBinaryString(num));
        num = updateBit(num, 1, true);
        System.out.println(Integer.toBinaryString(num));
        
        
        
        
        
        System.err.println("done.");  
        return;
    }
    
    
    
    // ------------ getBit() ----------------------------------------
    // Get value on i-th bit of num.
    // ex.    i: (MSB) 4 3 2 1 0 (LSB)
    //    value:       0 1 0 0 1 
    public static int getBit(int num, int i) // O(1)
    {
       return (((num & (1 << i)) != 0) ? 1 : 0);        
    	// If return boolean.
        //return ((num & (1 << i)) != 0);        
    }
    
    // ------------ setBit() ----------------------------------------
    // Set 1 on i-th bit of num. 
    public static int setBit(int num, int i)
    {
        return num | (1 << i);
    }
    
    // ------------ clearBit() --------------------------------------
    // Set 0 on i-th bit of num.
    public static int clearBit(int num, int i)
    {
    	// (1 << i): 000000100
    	// mask    : 111111011
        int mask = ~(1 << i);
        return num & mask;
    }
    
    // ------------ clearBitsMSBthroughI() --------------------------
    // Set 0 on MSB through i-th bit of num.
    // ex. 11111111 => 00000011 (i=2) 
    public static int clearBitsMSBthroughI(int num, int i)
    {
    	// (1 << i): 00000100
    	// mask    : 00000011
        int mask = (1 << i) - 1;
        return num & mask;
    }
    
    // ------------ clearBitsIthroughLSB() --------------------------
    // Set 0 on i-th through LSB bit of num.
    // ex. 1111 => 1000 (i=2) 
    public static int clearBitsIthroughLSB(int num, int i)
    {
    	// -1   : 11111111
    	// mask : 11111000
        int mask = -1 << (i + 1);
        return num & mask;
    }
    
    // ------------ updateBit() -------------------------------------
    // Set v on i-th bit of num.
    public static int updateBit(int num, int i, boolean bitIs1)
    {
    	int value = bitIs1 ? 1 : 0;
    	// First clear i-th bit and then 
    	// set v on i-th bit.
        int mask = ~(1 << i);
        return num & mask | (value << i);
    }    
    
    
    
    
    
    
    
    
    
    
    
    

}
