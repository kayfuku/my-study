// Put m into n between j-th bit and i-th bit.
// ex.
// m: 11, n: 10000001, i: 2, j: 3
// output: 10001101
// Author: Cracking the Coding Interview p.234 + kei
// Date  : October 2, 2016


public static void main(String[] args) {
        
        int n = Integer.parseInt("10000001", 2);  
        int m = Integer.parseInt("11", 2); 
        int result = updateBits(n, m, 2, 3);
        
        System.out.println("n: " + Integer.toBinaryString(n));
        System.out.println("m: " + Integer.toBinaryString(m));
        System.out.println(Integer.toBinaryString(result));        

}

// ------------ updateBits() ----------------------------------------------
// Put m into n between j-th bit and i-th bit.
public static int updateBits(int n, int m, int i, int j) {
    /* Create a mask that allows to clear all bits between i-th and j-th in n. 
     * ex. i = 2, j = 4 => 11100011 
     */
    
    // 1s from MSB through (j + 1)-th, and then 0s from j-th through LSB.
    // ex. allOnes : 11111111
    //     left    : 11100000
    int allOnes = ~0;
    int left = allOnes << (j + 1);
    
    // 0s from MSB through i-th, and then 1s from (i - 1)-th through LSB.
    // ex. right   : 00000011      
    int right = (1 << i) - 1;
    
    // 0s between i-th and j-th inclusive, all the rest are 1s. 
    // ex. mask    : 11100011
    int mask = left | right;
    
    // Clear j-th through i-th. 
    // ex. n       : 10101101
    //     mask    : 11100011
    //     nCleared: 10100001
    int nCleared = n & mask; 
            
    // Merge m into n. 
    // ex. m       : 00000111
    //     mShifted: 00011100
    //     nCleared: 10100001
    //     return  : 10111101
    int mShifted = m << i;
    return nCleared | mShifted;
}



// Author: kei
// Date  : October 2, 2016
public static void main(String[] args) {

    int N = Integer.parseInt("10000001", 2);
    System.out.println("N: " + Integer.toBinaryString(N));
    int M = Integer.parseInt("11", 2);
    System.out.println("M: " + Integer.toBinaryString(M));
    int i = 2, j = 3;
    
    System.out.println("re: " + Integer.toBinaryString(insert(N, M, j, i)));

    System.out.println("done.");
    return;
}

public static int insert(int N, int M, int j, int i) {
    for (int p = 0; p < j - i + 1; p++) {
        int v = getBit(M, p);
        N = updateBit(N, i + p, v);
    }
    
    return N;
}
    



























