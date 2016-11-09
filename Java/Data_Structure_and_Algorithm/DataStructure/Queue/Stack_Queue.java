// Stack and Queue implemented by array-based and reference-based.
// Author: kei
// Date  : October 20, 2016

package whiteboard;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class Stack_Queue {

	public static void main(String[] args) {
		
		// Test MyStackRef.
		MyStackRef<Integer> stackRef = new MyStackRef<Integer>();
		stackRef.push(2);
		stackRef.push(3);
		stackRef.push(5);
		//stackRef.display(); // 5 3 2 
		//System.out.println(stackRef.peek()); // 5
		stackRef.pop();
		int n = stackRef.pop();
		//System.out.println(n); // 3
		stackRef.pop();
		boolean b = stackRef.isEmpty();
		//System.out.println(b); // true
		//stackRef.pop(); // Throws exception.
		
		
		// Test MyStackArr.
		MyStackArr stackArr = new MyStackArr();
		stackArr.push(2);
		stackArr.push(3);
		stackArr.push(5);
		//System.out.println(Arrays.toString(stackArr.arr)); // [2, 3, 5]
		//stackArr.push(7); // Throws exception.
		n = (int) stackArr.peek();
		//System.out.println(n); // 5
		stackArr.pop();
		n = (int) stackArr.pop();
		//System.out.println(n); // 3
		stackArr.pop();
		try{
			//stackArr.pop(); // Throws exception.	
		} catch (Exception e) {
			e.printStackTrace();
		}
		try{
			//stackArr.pop(); // Throws exception.	
		} catch (Exception e) {
			e.printStackTrace();
		}		
		//System.out.println(stackArr.top); // -1


		// Test MyQueueRef.
		MyQueueRef<Integer> queueRef = new MyQueueRef<Integer>();
		queueRef.enqueue(1);
		queueRef.enqueue(3);
		queueRef.enqueue(6);
		//queueRef.display(); // 1 3 6
		n = queueRef.peek();
		//System.out.println(n); // 1
		n = queueRef.dequeue();
		//System.out.println(n); // 1
		n = queueRef.dequeue();
		//System.out.println(n); // 3
		n = queueRef.dequeue();
		//System.out.println(n); // 6
		//queueRef.dequeue();    // Throws exception.
		b = queueRef.isEmpty();
		//System.out.println(b);   // true

		
		// Test MyQueueArr.
		MyQueueArr queueArr = new MyQueueArr();
		queueArr.enqueue(7);
		queueArr.enqueue(8);
		queueArr.enqueue(4);
		//queueArr.enqueue(1); // Throws exception.
		//System.out.println(Arrays.toString(queueArr.arr)); // [7, 8, 4]
		n = (int) queueArr.dequeue();
		//System.out.println(n); // 7
		queueArr.dequeue(); // --> 8
		queueArr.dequeue(); // --> 4
		//queueArr.dequeue(); // Throws exception.
		queueArr.enqueue(1);
		queueArr.enqueue(2);
		//System.out.println(queueArr.front + " " + queueArr.back); // 0 1
		//System.out.println(Arrays.toString(queueArr.arr)); // [1, 2, 4]
		queueArr.enqueue(7);
		System.out.println(queueArr.front + " " + queueArr.back); // 0 2
		System.out.println(Arrays.toString(queueArr.arr)); // [1, 2, 7]

		
		
		
		
		
		

	}

	
	
	
}


// Stack. (Reference-based)
// Author: Cracking the Coding Interview 6th edition p.96 + kei.
// Date  : September 19, 2016
class MyStackRef<T> {

	// Stack node.
	private static class StackNode<T> {
		private T data;
		private StackNode<T> next;

		public StackNode(T data) {
			this.data = data;
		}
	}

	private StackNode<T> top;

	public void push(T item) {
		StackNode<T> newNode = new StackNode<T>(item);
		// Insert node at head.
		newNode.next = top;
		top = newNode;
	}

	public T peek() {
		if (top == null) {
			throw new EmptyStackException(); // Stack is empty.
		}
		return top.data;
	}

	public T pop() {
		if (top == null) {
			throw new EmptyStackException(); // Stack is empty.
		}
		// Remove node at head.
		T data = top.data;
		top = top.next;
		return data;
	}

	public boolean isEmpty() {
		return top == null;
	}
	
	// Display every element of the stack. 
	// Author: kei
	// Date  : October 20, 2016
	public void display() {
		StackNode<T> node = top;
		while (node != null) {
			System.out.println(node.data);
			node = node.next;
		}
	}

}


//Stack. (Array-based)
//Author: JAVA p.367 + kei.
//Date  : October 20, 2016
class MyStackArr {
	final int MAX_STACK = 3;
	Object[] arr;
	int top;
	// For resizing.
	int size;
	
	public MyStackArr() {
		arr = new Object[MAX_STACK];
		top = -1;
	}
	
	void push(Object obj) {
		if (isFull()) {
			throw new RuntimeException();
		}
		top++;
		arr[top] = obj;		
		size++;
	}
	
	Object peek() {
		if (isEmpty()) {
			throw new RuntimeException();
		}
		return arr[top];
	}
	
	Object pop() {
		if (isEmpty()) {
			throw new RuntimeException();
		}
		top--;
		size--;
		return arr[top + 1];		
	}
	
	
	boolean isFull() {
		return top == MAX_STACK - 1;
	}
	
	boolean isEmpty() {
		return top < 0;
	}
	
	int size() {
		return size;
	}
	
	// OpenDataStructure-java.pdf p.36
	void resize() {
		// TODO
	}
	
	void initialize() {
		top = 0;
	}
	
}


// Queue. (Reference-based)
// Author: Cracking the Coding Interview 6th edition p.96 + kei.
// Date  : September 19, 2016
class MyQueueRef<T> {

	// Queue node.
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
		QueueNode<T> t = new QueueNode<T>(item);
		if (tail != null) {
			// Insert node at tail.
			tail.next = t;
		}
		tail = t;
		if (head == null) {
			// The first node added.
			head = tail;
		}
	}

	public T peek() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		return head.data;
	}

	public T dequeue() {
		if (head == null) {
			throw new NoSuchElementException();
		}
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
	
	// Display every element of the stack. 
	// Author: kei
	// Date  : October 20, 2016
	public void display() {
		QueueNode<T> node = head;
		while (node != null) {
			System.out.println(node.data);
			node = node.next;
		}
	}	
	
}


// Queue. (Circular array-based)
// Author: JAVA p.422 + kei.
// Date  : October 20, 2016
class MyQueueArr {
	final int MAX_QUEUE = 3;
	Object[] arr;
	int front, back, count;
	
	public MyQueueArr() {
		arr = new Object[MAX_QUEUE];
		front = 0;
		back = MAX_QUEUE - 1;
		count = 0;
	}
	
	boolean isEmpty() {
		return count == 0;
	}
	
	boolean isFull() {
		return count == MAX_QUEUE;
	}
	
	void enqueue(Object obj) {
		if (isFull()) {
			throw new RuntimeException();
		}
		// Move forward in circular array.
		back = (back + 1) % MAX_QUEUE;
		arr[back] = obj;
		count++;
	}
	
	Object peek() {
		if (isEmpty()) {
			throw new RuntimeException();
		}
		return arr[front];		
	}
	
	Object dequeue() {
		if (isEmpty()) {
			throw new RuntimeException();
		}
		Object obj = arr[front];
		// Move forward in circular array.
		front = (front + 1) % MAX_QUEUE;
		count--;
		return obj;
	}
	
}























