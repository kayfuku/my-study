// Set of stacks.
// Author: Cracking the Coding Interview p.206 + kei.
// Date  : February 3, 2016


class SetOfStacks {
    ArrayList<LinkedList<Integer>> stacks = new ArrayList<LinkedList<Integer>>();
    // Capacity of each stack.
    private int capacity;

    public SetOfStacks(int capacity) {
        this.capacity = capacity;
    }

    public LinkedList<Integer> getLastStack() {
        if (stacks.size() == 0) {
            return null;
        }
        return stacks.get(stacks.size() - 1);
    }

    public void push(int value) {
        LinkedList<Integer> lastStack = getLastStack();
        if (lastStack != null && lastStack.size() < capacity) {
            lastStack.push(value);
        } else {
            // Using Deque interface as a stack.
            LinkedList<Integer> stack = new LinkedList<Integer>();
            stack.push(value);
            stacks.add(stack);
        }
    }

    public int pop() {
        LinkedList<Integer> lastStack = getLastStack();
        int value = lastStack.poll();
        if (lastStack.size() == 0) {
            stacks.remove(stacks.size() - 1);
        }
        return value;
    }

}





