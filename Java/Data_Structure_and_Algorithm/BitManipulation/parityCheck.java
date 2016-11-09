// Compute the parity of a word.  
// If the number of ones is odd, return 1, otherwise return 0.
// Author: EPI p.42 + kei 
// Date  : October 28, 2016

// O(N) solution.
public static short parity(long x) {
    short result = 0;
    while (x != 0) {
        // If x's LSB is 1, then toggle result. 
        result ^= (x & 1);
        // Logical right shift. 
        x >>>= 1;
    }

    return result;
}

// O(k) solution, where k is the number of bits set to 1 in a word.
// ex. 01100010 --> k: 3
public static short parity(long x) {
    short result = 0;

    // Iterate as much times as the number of 1s. 
    while (x != 0) {
        // There is at least one 1, so toggle result. 
        result ^= 1;
        // Drop the lowest set bit of x. 
        x &= x - 1;
    }
    
    return result;
}


// Drop the lowest set bit of n.
int n = 5;
System.out.println(Integer.toBinaryString(n)); // 101
// Drop the lowest set bit of n.
n &= n - 1;
System.out.println(Integer.toBinaryString(n)); // 100































