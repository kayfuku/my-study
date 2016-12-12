// LinkedList fundamentals. 
// Author: kei
// Date  : December 8, 2016 

package pack01;

import java.util.LinkedList;

public class Lab01 {

    public static void main(String[] args) {

        
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(0);
        list.add(null);
        
        // You can put null into the list. 
        System.out.println(list.toString()); // [1, 0, null] 
        Integer n = list.get(2);
        System.out.println(n); // null
        
        // Index out of bounds. 
        //list.get(3); // IndexOutOfBoundsException 
        
        // Primitive type does not accept null. 
        int num = list.get(2); // NullPointerException 
        
        
        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
}

























