// Find k-th to last element in a singly linked list.
// Iterative version.
// Author: Cracking the Coding Interview 2.2 p.186 + kei.
// Date  : February 2, 2016


LinkedListNode findKthToLast(LinkedListNode head, int k) {
    if (k <= 0) {
        return null;
    }

    LinkedListNode p1 = head;
    LinkedListNode p2 = head;

    // Repeat k - 1 times, which means...
    // Move p2 to the (k - 1)-th node from head starting with 0.
    // Move p2 to the k-th node from head starting with 1.
    for (int i = 0; i < k - 1; i++) {
        // Not found since list size < k.
        if (p2 == null) {
            return null;
        }
        p2 = p2.next;
    }
    if (p2 == null) {
        return null;
    }

    // Move p1 and p2 at the same rate.
    // When p2 reaches to the end, p1 is k-th to last element.
    while (p2.next != null) {
        p1 = p1.next;
        p2 = p2.next;
    }

    return p1;
}































