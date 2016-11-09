// Check if it is a permutation of a palindrome.
// Author: CtCI 6th p.198 + kei
// Date  : October 18, 2016

package pack01;


public class Lab01 {

	public static void main(String[] args) {

		
		String s = "Tact Coa";
		System.out.println(isPermOfPalin(s)); // true
		
		
		System.out.println("done.");
		return;
	} // end of main().
	
	
	public static boolean isPermOfPalin(String phrase) {
		int bitVector = createBitVector(phrase);		
		return bitVector == 0 || checkExactlyOneBitSet(bitVector);
	}

	// Create a bit vector for the string. 
	// For each letter with value i, toggle the ith bit.
	public static int createBitVector(String phrase) {
		// Type int allocate 4 byte memory, which is 32 bits.
		// This allows you to check whether it's on or off about 
		// each of 32 different characters.
		// This is enough if you just check 26 lower-case alphabets.
		int bitVector = 0;
		for (char c : phrase.toCharArray()) {
			// 0 ~ 25, -1.
			//int x = getCharNum(c);
			int x = getCharNumber(c);
			bitVector = toggle(bitVector,x);
		}
		
		return bitVector;
	}

	// Author for this: kei
	private static int getCharNumber(char c) {
    	c = Character.toLowerCase(c);
    	if (c >= 'a' && c <= 'z') {
			return c - 'a';
		}
    	return -1;
    }
	
    private static int getCharNum(char c) {
    	// getNumericValue() turns case insensitive char into num.
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int val = Character.getNumericValue(c);
		if (val >= a && val <= z) {
			// return 0 ~ 25 corresponds to 'a' ~ 'z'. 
			return val - a;
		}
		// Something other than 'a' ~ 'z'.
		return -1;
	}
	
	private static int toggle(int bitVector, int index) {
		if (index < 0) {
			// Non-letter.
			return bitVector;
		}
		// mask:  z ... d c b a 
		//        0 ... 0 1 0 0 
		int mask = 1 << index;
		if ((bitVector & mask) == 0) {
			// The bit of bitVector is 0.
			// Toggle it. 0 -> 1
			return bitVector |= mask;
		} else {
			// The bit of bitVector is 1.
			// Toggle it. 1 -> 0
			return bitVector &= ~mask;
		}
	}

	// This is like idiom to check if it has exactly one 1 in the bit sequence.
	// ex. 00001000 --> true	01001000 --> false
	//  -  00000001			 -  00000001
	//     00000111		        01000111
	//  &  00001000			 &  01001000
	//     00000000				01000000
	// The bits to the right of right-most 1 including the right-most 1 bit
	// toggle 1 to 0, or 0 to 1 without changing bits on the left hand side of the bit.
	private static boolean checkExactlyOneBitSet(int bitVector) {		
		return (bitVector & (bitVector - 1)) == 0;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

























