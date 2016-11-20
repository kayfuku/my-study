// Reverse the order of a list. Iterative and recursive version. 
// Without allocating additional nodes. 
// Author: EPI 8.2 p.104 + kei
// Date  : November 18, 2016

package whiteboard;


public class Lab_whiteboard {

    public static void main(String[] args) {

        // Test reverseList();
        SLLNode<Integer> sll = new SLLNode<>(0);
        sll.insertTail(1);
        sll.insertTail(2);
        sll.insertTail(3);
        sll.insertTail(4);
        sll.insertTail(5);
        sll.displayList(); // 0 1 2 3 4 5 
        System.out.println();
        
        SLLNode<Integer> reverseSll = reverseList(sll);
        reverseSll.displayList(); // 5 4 3 2 1 0 
        System.out.println();

        // Test reverseListRecur();
        SLLNode<Integer> sll2 = new SLLNode<>(0);
        sll2.insertTail(1);
        sll2.insertTail(2);
        sll2.insertTail(3);
        sll2.insertTail(4);
        sll2.insertTail(5);
        sll2.displayList(); // 0 1 2 3 4 5 
        System.out.println();

        SLLNode<Integer> reverseSll2 = reverseListRecur(sll2);
        reverseSll2.displayList(); // 5 4 3 2 1 0

        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    
    // Reverse the order of a list. O(N)
    // Author: EPI 8.2 p.104 + kei
    // Date  : November 18, 2016
    public static SLLNode<Integer> reverseList(SLLNode<Integer> L) {
        // d -> L
        SLLNode<Integer> dummyHead = new SLLNode<Integer>(0, L);
        
        // d -> 1 -> 2 -> 3
        //      i    n
        // d -> 2 -> 1 -> 3 
        //           i    n
        // d -> 3 -> 2 -> 1
        //                i
        SLLNode<Integer> iter = dummyHead.next;
        // Why while condition like this?
        // Because you want to keep processing this 
        // until iter node is at the second to last. 
        // Note that iter node reference is not moved, but 
        // the node itself eventually moves to the tail
        // since the nextIter node jumps back to the head.
        while (iter.next != null) {
            SLLNode<Integer> nextIter = iter.next;
            // Connect the iter node to the next next node. 
            iter.next = nextIter.next;
            // Move the nextIter node to the first node. 
            nextIter.next = dummyHead.next;
            dummyHead.next = nextIter;          
        } 
        
        return dummyHead.next;      
    }

    
    // Reverse the order of a list. Recursive version. 
    // Author: kei
    // Date  : November 18, 2016
    public static SLLNode<Integer> reverseListRecur(SLLNode<Integer> node) {
        if (node == null) { return null; }
        // Recursive version also needs a dummy node. 
        SLLNode<Integer> dummy = new SLLNode<Integer>(0, node);
        SLLNode<Integer> iter = dummy.next;
        return reverseListRecur(dummy, iter);
    }
    public static SLLNode<Integer> reverseListRecur(
            SLLNode<Integer> dummy, SLLNode<Integer> iter) {
        
        if (iter.next == null) {
            // node has just one node. 
            return dummy.next;
        }
        
        SLLNode<Integer> nextIter = iter.next;
        // Connect the iter node to the next next node. 
        iter.next = nextIter.next;
        // Move the nextIter node to the first node. 
        nextIter.next = dummy.next;
        dummy.next = nextIter;  

        return reverseListRecur(dummy, iter);
    }
}

























