// Merge two sorted lists. 
// Author: EPI + kei.
// Date  : October 30, 2016

package whiteboard;

import java.awt.image.RescaleOp;

public class Lab_whiteboard {

    public static void main(String[] args) {

        SLLNode<Integer> list1 = new SLLNode(2);
        list1.insertTail(5);
        list1.insertTail(7);
        list1.displayList();
        System.out.println();
        SLLNode<Integer> list2 = new SLLNode(3);
        list2.insertTail(11);
        list2.displayList();
        System.out.println();
        
        SLLNode<Integer> result = mergeTwoSortedLists(list1, list2);
        result.displayList();
        
        
        System.out.println("done.");
        return;
    } // end of main().
    
    
    public static SLLNode<Integer> mergeTwoSortedLists(SLLNode<Integer> list1, 
                                                       SLLNode<Integer> list2) {
        // From Java 7, you can write just <>, but only at declaration. 
        SLLNode<Integer> dummyHead = new SLLNode<>(0, null);
        // To create list appending nodes and return the list, 
        // it's better to use dummy node to write pretty code. 
        SLLNode<Integer> current = dummyHead;
        // It should be good to use alternate pointer. 
        SLLNode<Integer> p1 = list1, p2 = list2;
        
        while (p1 != null && p2 != null /* Until either pointer reaches end. */ ) {
            if (p1.data <= p2.data) {
                current.next = p1;
                p1 = p1.next;
            } else {
                current.next = p2;
                p2 = p2.next;
            }
            current = current.next;     
        }
        
        // At this point, one list is traversed to the end.  
        // Append the other list. 
        current.next = (p1 == null) ? p2 : p1;
        
        // The beauty of dummy node. 
        return dummyHead.next;
    }
    
    
    
    
    
    
    
    
    
}



























































