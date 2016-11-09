// Queue. (Reference-based)
// Author: Cracking the Coding Interview 6th edition p.96 + kei.
// Date  : September 19, 2016

class MyQueue<T> {

    private static class QueueNode<T> {
        private T data;
        private QueueNode<T> next;

        public QueueNode(T data) {
            this.data = data;
        }
    }

    private QueueNode<T> head;
    private QueueNode<T> tail;

    void enqueue(T item) {
        QueueNode<T> newNode = new QueueNode<T>(item);
        if (tail != null) {
            // Insert node at tail.
            tail.next = newNode;
        }
        tail = newNode;
        if (head == null) {
            // The first node added.
            head = tail;
        }
    }

    public T peek() {
        if (head == null) throw new NoSuchElementException();
        return head.data;
    }

    public T dequeue() {
        if (head == null) throw new NoSuchElementException();
        // Remove node at head.
        T data = head.data;
        head = head.next;
        if (head == null) {
            // The last one node is being taken out.
            tail = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return head == null;
    }

}










































