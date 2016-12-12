// Check if one string is a permutation of the other.
// Character code is ASCII.

package whiteboard;

import java.util.Arrays;



public class Lab_whiteboard {

	public static void main(String[] args) {

		String s1 = "eleven plus two"; 
		String s2 = "twelve plus one";
		System.out.println(isPermutation2(s1, s2)); // true 
		
		
		
		
		
		System.out.println();
		System.out.println("done.");
		return;
	} // end main().
	
	
	// Author: CtCI 1.2 p.194 + kei
	// Date  : December 6, 7, 2016
	public static boolean isPermutation(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		
		int[] count = new int[128];
		// By the way, toCharArray() takes O(N) time, 
		// but it is called once here. 
		// It might be better to use normal for loop and 
		// charAt(). 
		for (char c : s1.toCharArray()) {
			count[c]++;
		}
		
		for (char c : s2.toCharArray()) {
			count[c]--;
			if (count[c] < 0) {
				return false;
			}
		}
		
		return true;	
	}
	
	
	// Author: kei
	// Date  : December 6, 2016
	public static boolean isPerm(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		
		int[] count = new int[128];
		for (int i = 0; i < s1.length(); i++) {
			char c = s1.charAt(i);
			count[c]++;
		}
		
		// Not so great here and there!
		for (int i = 0; i < s2.length(); i++) {
			char c = s2.charAt(i);
			count[c]--;
		}
		
		for (int i = 0; i < count.length; i++) {
			if (count[i] != 0) {
				return false;
			}
		}
		
		return true;	
	}
	
	
	// Check if one string is a permutation of the other.
	// Character code is ASCII.
	// Author: Cracking Coding Interview p.174  + kei.
	// Date  : February 14, 2016

	// Solution 1.
	public static String sortChars(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
	public static boolean isPermutation1(String s1, String s2) {
		// Optimization.
		if (s1.length() != s2.length()) {
			return false;
		}

		// After sorting, they should be the same 
		// if they are anagrams each other.
		return sortChars(s1).equals(sortChars(s2));
	}


	// Solution 2.
	public static boolean isPermutation2(String s1, String s2) {
		// Optimization.
		if (s1.length() != s2.length()) {
			return false;
		}

		int[] count = new int[256];
		// Count the num of characters in the String.
		for (char c : s1.toCharArray()) {
			// char is automatically converted to int.
			count[c]++;
		}

		for (char c : s2.toCharArray()) {
			if (--count[c] < 0) {
				// The num of the character is different.
				return false;
			}
		}

		// The num of each character is same.
		return true;
	}




	
	
	
	
	
	
	
	
	
}

























