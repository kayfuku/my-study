// Count the number of ones in the binary representation of 
// a given integer.
// Author: PIE p.204 + kei 
// Date  : October 28, 2016

// O(N) solution.
int countOnesInBinary(int n) {
    int count = 0;
    while (n != 0) {
        if ((n & 1) == 1) {
            count++;
        }
        n >>>= 1;
    }

    return count;
}

// O(k) solution, where k is the number of 1s in the integer. 
// But this solution would not be expected in an interview. 
// ex. 01100010 --> k: 3
int countOnesInBinary(int n) {
    int count = 0;

    // Iterate as much times as the number of 1s. 
    while (n != 0) {
        count++;
        // Drop the lowest set bit of x. 
        n &= n - 1;
    }

    return count;
}


// Drop the lowest set bit of n.
int n = 5;
System.out.println(Integer.toBinaryString(n)); // 101
// Drop the lowest set bit of n.
n &= n - 1;
System.out.println(Integer.toBinaryString(n)); // 100




















