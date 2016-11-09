// Divide a singly linked list into two new singly linked lists.
// Author: Cracking the Coding Interview p.188 + kei.
// Date  : February 2, 2016


public static LinkedListNode devideSinglyLinkedList(LinkedListNode node, int x) {
    LinkedListNode smallList = null;
    LinkedListNode bigList = null;

    // Divide the list 'node' into two lists.
    while (node != null) {
        // Necessary for moving to the next node in the original list.
        // Because node.next gets null or a node in the new list. 
        LinkedListNode nextNode = node.next;
        
        if (node.data < x) {
            // Insert node at head of the smallList.
            node.next = smallList;
            smallList = node;
        } else {
            // Insert node at head of the bigList.
            node.next = bigList;
            bigList = node;
        }

        node = nextNode;
    }

    if (smallList == null) {
        return bigList;
    }

    // Merge.
    // Find the end of the 'smallList' and merge.
    LinkedListNode head = smallList;
    LinkedListNode p = smallList;
    while (p.next != null) {
        p = p.next;
    }
    // Merge.
    p.next = bigList;

    return head;
}


LinkedListNode devideSinglyLinkedList2(LinkedListNode node, int x) {
    LinkedListNode head = node;
    LinkedListNode tail = node;

    // Divide the list 'node' into two lists.
    while (node != null) {
        // Necessary for moving to the next node in the original list.
        // Because node.next gets null or a node in the new list. 
        LinkedListNode nextNode = node.next;
        
        if (node.data < x) {
            // Insert node at head.
            node.next = head;
            head = node;
        } else {
            // Insert node at tail.
            tail.next = node;
            tail = node;
        }

        node = nextNode;
    }
    tail.next = null;
    
    return head;
}


























