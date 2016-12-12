// LinkedHashSet demo.
// Author: kei
// Date  : November 17, 2016

package whiteboard;

import java.util.HashSet;
import java.util.LinkedHashSet;




public class Lab_whiteboard {

	public static void main(String[] args) {

		
		LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
		linkedHashSet.add(1);
		linkedHashSet.add(4);
		linkedHashSet.add(5);
		linkedHashSet.add(2);
		linkedHashSet.add(6);
		linkedHashSet.add(8);
		linkedHashSet.add(7);
		linkedHashSet.add(0);
		for (Integer integer : linkedHashSet) {
			System.out.println(integer);
		}
		// 1 4 5 2 6 8 7 0 
		// iterator() returns keys in the order in which they were inserted into the set. 
		
		System.out.println();
		
		HashSet<Integer> set = new HashSet<>();
		set.add(1);
		set.add(4);
		set.add(5);
		set.add(2);
		set.add(6);
		set.add(8);
		set.add(7);
		set.add(0);
		for (Integer integer : set) {
			System.out.println(integer);
		}
		// 0 1 2 4 5 6 7 8 
		// iterator() returns keys in an unspecified order. 

		
		
		
		System.out.println();
		System.out.println("done.");
		return;
	} // end main().
	
	

	
	
	
	
	
}

























