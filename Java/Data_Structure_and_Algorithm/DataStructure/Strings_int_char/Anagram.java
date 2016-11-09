// Sort the string array so that all the anagrams are next to each other.
// Author: Cracking Coding Interview p.337  + kei.
// Date  : February 13, 2016

package pack01;

import java.util.Arrays;
import java.util.Comparator;

public class Anagram {

	public static void main(String[] args) {
		System.out.println();		
		
		String[] strings = {"acre", "xxx", "bbb", "race", "ccc", "care", "xxx"};
		
		Arrays.sort(strings, new AnagramComparator());
		
		for (String string : strings) {
			System.out.println(string);
		}
		
		System.out.println("done.");
		return;
	} // end of main().
}


class AnagramComparator implements Comparator<String> {
	
	// Sort the letters in a string and 
	// return the result string. 
	public String sortChars(String s) {
		char[] chars = s.toCharArray(); 
		Arrays.sort(chars); 
		return new String(chars);
	}
	
	@Override
	public int compare(String s1, String s2) {
		return sortChars(s1).compareTo(sortChars(s2));		
	}
	
	
}


























