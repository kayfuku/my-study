// Delete a node in a singly Linked List.
// Author: Cracking the Coding Interview p.78 + kei.
// Date  : January 31, 2016


// Delete a node that has data d in a singly linked list. 
Node deleteNode(Node head, int d) {
    // Set 'node' to the first node.
    Node node = head;
    Node prev = null;
    
    // Delete a head node. Special case.
    if (node.data == d) {
        return head.next;
    }

    // Traverse throughout the list.
    prev = node;
    node = node.next;
    // You should imagine a picture of the tail node
    // before writing conditional clause.
    while (node != null) {
        if (node.data == d) {
            // Detach the current node.
            prev.next = node.next;
            // Return the updated list.
            return head;
        }

        // Move to the next.
        prev = node;
        node = node.next;
    }
    
    // Return the updated list.
    return head;
}


class Node {
    // Pointer to the next node.
    Node next = null;
    // Data.
    int data;

    public Node(int d) {
        data = d;
    }

    // Append a node. 
    void appendToTail(int d) {
        Node endNode = new Node(d);
        Node node = this;

        // Go to the tail node of original list.
        while (node.next != null) {
            node = node.next;
        }

        // Append the new node to the tail.
        node.next = endNode;
    }
}































