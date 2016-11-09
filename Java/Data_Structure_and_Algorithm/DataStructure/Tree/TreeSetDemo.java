// TreeSet demo.
// add(): O(log N)
// pollFirst(), pollLast(): O(log N) 
// Author: kei
// Date  : February 9, 2016


package pack01;

import java.util.TreeSet;


public class Lab01 {

    public static void main(String[] args) {        
        
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        treeSet.add(1);
        treeSet.add(7);
        treeSet.add(2);
        treeSet.add(1);
        treeSet.add(9);
        treeSet.add(4);
        treeSet.add(6);
        for (Integer integer : treeSet) {
            System.out.println(integer);
        }
        System.out.println();

        System.out.println("pollFirst(): " + treeSet.pollFirst()); // 1
        System.out.println("pollLast(): " + treeSet.pollLast());   // 9


        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end of main().

    
    
    
    
}



























