// A singly Linked List.
// Append node.
// Author: Cracking the Coding Interview p.77 + kei.
// Date  : January 31, 2016


class LinkedListNode {
    // Pointer to the next node.
    LinkedListNode next = null;
    // Data.
    int data;

    public LinkedListNode(int d) {
        data = d;
    }

    // But why add at tail in SLL? It's O(N) time.
    void append(int d) {
        LinkedListNode endNode = new LinkedListNode(d);
        LinkedListNode node = this;

        // Go to the tail node of this list.
        while (node.next != null) {
            node = node.next;
        }

        // Append the new node to the tail.
        node.next = endNode;
    }
}































