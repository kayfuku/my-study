// Queue.
// Author: Cracking the Coding Interview p.82 + kei.
// Date  : February 2, 2016


class Queue {
    Node first, last;

    void enqueue(Object item) {
        Node node = new Node(item);
        if (first == null) {
            last = node;
            first = node;            
        } else {
            last.next = node;
            last = node;
        }
    }

    Object dequeue() {
        if (first != null) {
            Object item = first.data;
            first = first.next;
            return item;
        }
        return null;
    }
}










