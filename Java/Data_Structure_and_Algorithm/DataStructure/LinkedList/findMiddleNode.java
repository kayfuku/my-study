// Find middle node/the beginning node of the second half 
// when you don't know the size of the list. (Runner technique)
// Author: CtCI 6th p.218 + kei
// Date  : October 19, 2016
public static int findMiddleNode(SLLNode<Integer> head) {
    SLLNode<Integer> fast = head;
    SLLNode<Integer> slow = head;
            
    // 'fast' is moving at 2x speed.
    while (fast != null && fast.next != null) {
        // Do something here.
        
        slow = slow.next;
        fast = fast.next.next;          
    }
    
    // 'head' list has odd number of elements, so skip the 
    // middle element.
    if (fast != null) {
        slow = slow.next;
    }
    
    // At this point, 'slow' indicates the beginning node 
    // of the second half of the list.
    return slow.data;
}


































