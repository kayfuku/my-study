// Stack. (Reference-based)
// Author: Cracking the Coding Interview 6th edition p.96 + kei.
// Date  : September 19, 2016


class MyStack<T> {

    private static class StackNode<T> {
        private T data;
        private StackNode<T> next;

        public StackNode(T data) {
            this.data = data;
        }
    } 

    private StackNode<T> top;

    public void push(T item) {
        StackNode<T> t = new StackNode<T>(item);
        // Insert node at head.
        t.next = top;
        top = t;
    }

    public T peek() {
        if (top == null) throw new EmptyStackException(); // Stack is empty.
        return top.data;
    }

    public T pop() {
        if (top == null) throw new EmptyStackException(); // Stack is empty.
        // Remove node at head.
        T data = top.data;
        top = top.next;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }

}

































