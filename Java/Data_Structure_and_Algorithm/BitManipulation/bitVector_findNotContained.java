// Find non-negative integers that is not contained in the file. 
// Memory size is 10GB. 
// Author: Cracking Coding Interview p.324, CtCI 6th p.403 + kei.
// Date  : February 14, November 4, 2016


// The number of non-negative integers on int type is 2^31,
// which is 2*10^9. 
// 10GB has 10^9 bytes, which is 8*10^9 bits. 
// MAX_VALUE: 2^31 - 1
long numberOfInts = ((long) Integer.MAX_VALUE) + 1;
byte[] bitfield = new byte[(int) (numberOfInts / 8)];

void findOpenNumber() throws FileNotException {
    Scanner in = new Scanner(new FileReader("file.txt"));
    // Set 1 if the integer exists in the file. 
    while (in.hasNextInt()) {
        int n = in.nextInt();
        // Finds the corresponding number in the bitfield by using the
        // OR operator to set the nth bit of a byte (e.g.. 10 would
        // correspond to the 3rd bit of the 2nd byte in the byte array). 
        // |0000 0000|0000 0100|
        bitfield[n / 8] |= 1 << (n % 8);
    }

    // Search the integer that does not exist. 
    for (int i = 0; i < bitfield.length; i++) {
        for (int j = 0; j < 8; j++) {
            // Retrieves the individual bits of each byte. When 0 bit * is found, 
            // finds the corresponding decimal value. 
            if ((bitfield[i] & (1 << j)) == 0) {
                System.out.println(i * 8 + j);
                return;
            }
        }
    }
}

































