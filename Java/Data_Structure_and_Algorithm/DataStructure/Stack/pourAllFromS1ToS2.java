// Pop all elements from stack 1 and push them into stack 2.
// Author: CtCI p.236 + kei.
// Date  : October 20, 2016


void shiftStacks() {
    while (!stack1.isEmpty()) {
        stack2.push(stack1.pop());
    }
}





























