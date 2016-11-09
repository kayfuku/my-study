// Sort the characters in a string and 
// return the result string. 
// Author: Cracking Coding Interview p.337  + kei.
// Date  : February 13, 2016


public String sortCharsInString(String s) {
	// String to chars.
	char[] chars = s.toCharArray(); 

	// Sort by characters.
	Arrays.sort(chars); 

	// chars to String.
	return new String(chars);
}







