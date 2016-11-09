// Find dups in an array. 
// Example of bit vector. 
// Author: Cracking Coding Interview p.327 + kei.
// Date  : February 14, 2016


public static void checkDuplicates(int[] array) {
    // We can create a bit vector with 32000 bits, 
    // where each bit represents one integer.
    BitSet bs = new BitSet(32000);

    for (int i = 0; i < array.length; i++) {
        int num = array[i];
        // bitset starts at 0, numbers start at 1.
        int num0 = num - 1;
        if (bs.get(num0)) {
            // if it's true, then that num is duplicate.
            System.out.println(num);
        } else {
            // Set 1 on the corresponding bit in the bitset.
            bs.set(num0);
        }
    }
}


// You can use this class, but
// You might want to use BitSet class in Java JDK.
class BitSet {
    int[] bitset;

    public BitSet(int size) {
        // Create bit vector.
        // "size >> 5" means divide size by 32.
        // int is 32 bit each. 
        // Each bit represents 0 or 1.
        // 1 is set when the corresponding int is found.
        bitset = new int[size >> 5];
    }

    boolean get(int n) {
        // Divide it by 32.
        int fieldNumber = (n >> 5);
        // Remainder when divided by 32.
        int bitNumber = (n & 0x1F);
        // If it's 1, then the number is already set, so 
        // return true.
        return (bitset[fieldNumber] & (1 << bitNumber)) != 0;
    }

    void set(int n) {
        // Divide it by 32.
        int fieldNumber = (n >> 5);
        // Remainder when divided by 32.
        int bitNumber = (n & 0x1F);
        // Set 1 on the corresponding bit in the bitset.
        bitset[fieldNumber] |= 1 << bitNumber;
    }
}



























