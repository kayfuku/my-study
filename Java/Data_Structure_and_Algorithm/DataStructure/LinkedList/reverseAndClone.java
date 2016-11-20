// Reverse and clone the list.
// Author: CtCI 6th p.217 + kei
// Date  : October 19, November 18, 2016

package whiteboard;




public class Lab_whiteboard {

    public static void main(String[] args) {


        SLLNode<Integer> singlyLL = new SLLNode<Integer>(2);
        
        // Test insertTail().
        singlyLL.insertTail(1);
        singlyLL.insertTail(3);
        singlyLL.insertTail(2);
        singlyLL.insertTail(4);
        singlyLL.displayList(); // 2 1 3 2 4
        System.out.println();
        
        // Test reverseAndClone().
        SLLNode<Integer> list = reverseAndClone(singlyLL);
        list.displayList(); // 4 2 3 1 2 
        System.out.println();
        
        // one node.
        SLLNode<Integer> singlyLL4 = new SLLNode<Integer>(1);
        singlyLL4.displayList(); // 1
        System.out.println();

        // Test reverseAndCloneRecur().
        SLLNode<Integer> list2 = reverseAndCloneRecur(singlyLL);
        list2.displayList(); // 
        System.out.println();

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    
    // Reverse and clone the list.
    // Author: CtCI 6th p.217 + kei
    // Date  : October 19, 2016
    public static SLLNode<Integer> reverseAndClone(SLLNode<Integer> node) {
        SLLNode<Integer> head = null;
        
        while (node != null) {
            SLLNode<Integer> newNode = new SLLNode<Integer>(node.data);
            // Add-to-head pattern. 
            newNode.next = head;
            head = newNode;
            node = node.next;
        }
        
        return head;
    }
    
    // Pending...
    // Reverse and clone the list. Recursive version. 
    // Author: kei
    // Date  : November 18, 2016
    public static SLLNode<Integer> reverseAndCloneRecur(SLLNode<Integer> sll) {
        if (sll.next == null) { 
            SLLNode<Integer> newNode = new SLLNode<>(sll.data);
            return newNode; 
        }
        
        SLLNode<Integer> newNode = new SLLNode<>(sll.data);
        newNode.next = reverseAndCloneRecur(sll.next);
        
        return newNode;
    }
    
    
}

























