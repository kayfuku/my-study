// Delete duplicates in a singly linked list.
// Author: Cracking the Coding Interview p.184 + kei.
// Date  : January 31, 2016


// Pre: The list has at least two nodes.
public static void deleteDups(LinkedListNode node) {
    Set<Object> set = new HashSet<>();
    LinkedListNode prev = null;

    // Traverse the nodes. It ends at the end node.
    while (node != null) {
        if (set.contains(node.data)) {
            // Detach the current node.
            prev.next = node.next;            
        } else {
            set.add(node.data);
            // You need a previous cursor when deleting.
            prev = node;
        }

        // Move the current node to next.
        node = node.next;
    }
}




