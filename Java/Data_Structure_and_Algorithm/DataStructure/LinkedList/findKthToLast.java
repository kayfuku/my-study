// Find k-th to last element in a singly linked list.
// Iterative version.
// Author: CtCI 2.2 p.186 + kei.
// Date  : February 2, November 29, 2016

package whiteboard;



public class Lab_whiteboard {

    public static void main(String[] args) {

        
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(7);
        list.add(0);
        list.add(2);
        list.add(4);

        System.out.println(list.toString()); // [ 4 2 0 7 3 1 ]
        SLLNode<Integer> node = getKthToLast(list.head, 3);
        System.out.println(node.data); // 7 
        SLLNode<Integer> node2 = getKthToLast(list.head, 5);
        System.out.println(node2.data); // 2 
        SLLNode<Integer> node3 = getKthToLast(list.head, 1);
        System.out.println(node3.data); // 1 
        SLLNode<Integer> node4 = getKthToLast(list.head, 6);
        System.out.println(node4.data); // 4 
        SLLNode<Integer> node5 = getKthToLast(list.head, 7);
        System.out.println(node5.data); // NullPointerException.  

        
        
            
        
        
        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    

    public static SLLNode<Integer> getKthToLast(SLLNode<Integer> node, int k) {
        if (node == null || k <= 0) {
            return null;
        }
        
        SLLNode<Integer> n1 = node;
        SLLNode<Integer> n2 = node;
        
        // Repeat k - 1 times, which means...
        // Move p2 to the (k - 1)-th node from head starting with 0.
        // Move p2 to the k-th node from head starting with 1.
        for (int i = 0; i < k - 1; i++) {
            if (n2 == null) {
                // Not found since list size < k.
                return null;
            }           
            n2 = n2.next;
        }
        // Do not forget this!
        if (n2 == null) {
            return null;
        }
        
        // Not 'n2 != null' 
        // because n1 should immediately stop as soon as n2 gets 
        // to the end node. 
        while (n2.next != null) {
            n1 = n1.next;
            n2 = n2.next;
        }
        
        return n1;          
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}

























