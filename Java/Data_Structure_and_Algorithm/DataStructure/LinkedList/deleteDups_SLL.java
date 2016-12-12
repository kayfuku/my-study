// Delete duplicates in a singly linked list.
// Author: CtCI 2.1 p.208 + kei
// Date  : January 31, November 29, 2016

package whiteboard;

import java.util.HashSet;



public class Lab_whiteboard {

    public static void main(String[] args) {

        
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(1);
        list.add(0);
        list.add(2);
        list.add(0);

        deleteDups(list.head);
        System.out.println(list.toString()); // [ 0 2 1 3 ] 
        
        
        
        
        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    
    // Pre: The list has at least two nodes.
    public static void deleteDups(SLLNode<Integer> head) {
        HashSet<Integer> set = new HashSet<>();
        // You need a previous cursor when deleting node.
        SLLNode<Integer> prev = null;
        SLLNode<Integer> node = head;
        
        // Traverse the nodes. It ends at the end node.
        while (node != null) {
            if (!set.contains(node.data)) {
                set.add(node.data);
                prev = node;
            } else {
                // Detach the current node.
                prev.next = node.next;              
            }
            // Move the current node to next.
            node = node.next;           
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}

















