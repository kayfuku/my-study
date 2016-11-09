// Stack that has a method min() which allows you to get 
// a minimum value in the stack.
// Author: Cracking the Coding Interview p.205 + kei.
// Date  : February 3, 2016


class StackWithMin extends Stack<Integer> {
    Stack<Integer> stackMin;

    public StackWithMin() {
        stackMin = new Stack<Integer>();
    }

    @Override
    public void push(int value) {
        super.push(value);

        // Note: it needs '=' so that pop() works properly.
        if (value <= min()) {
            stackMin.push(value);
        }
    }

    @Override
    public Integer pop() {
        int value = super.pop();
        if (value == min()) {
            stackMin.pop();
        }
        return value;
    }

    public int min() {
        // When you think of stack, you think of it when it's empty.
        if (stackMin.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return stackMin.peek();
        }
    }
}

































