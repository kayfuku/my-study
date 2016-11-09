// Check if two strings are anagrams each other.
// Character code is ASCII.
// Author: Cracking Coding Interview p.174  + kei.
// Date  : February 14, 2016


// Solution 1.
public String sortChars(String s) {
	char[] chars = s.toCharArray();
	Arrays.sort(chars);
	return new String(chars);
}

public boolean areAnagrams(String s1, String s2) {
	// Optimization.
	if (s1.length() != s2.length()) {
		return false;
	}

	// After sorting, they should be the same 
	// if they are anagrams each other.
	return sortChars(s1).equals(sortChars(s2));
}


// Solution 2.
public boolean areAnagrams(String s1, String s2) {
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









































