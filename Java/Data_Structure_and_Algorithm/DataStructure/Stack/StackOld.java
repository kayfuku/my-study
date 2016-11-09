// Stack.
// Author: Cracking the Coding Interview p.81 + kei.
// Date  : February 2, 2016


class Stack {
    Node top;

    Object pop() {
        if (top != null) {
            Object item = top.data;
            top = top.next;
            return item;
        }
        return null;
    }

    void push(Object item) {
        LinkedListNode node = new LinkedListNode(item);
        node.next = top;
        top = node;
    }

    Object peek() {
        if (top != null) {
            Object item = top.data;
            return item;
        }
        return null;        
    }

}



