// Singly and Doubly Linked List.
// Author: kei
// Date  : October 19, 2016

package whiteboard;


public class SLL_DLL {

	public static void main(String[] args) {
		
		// Test SLL.
		// Create singly linked list.
//		SLLNode<Integer> singlyLL = new SLLNode<Integer>(2);
		
		// Test insertTail().
		// singlyLL.insertTail(1);
		// singlyLL.insertTail(3);
		// singlyLL.insertTail(2);
		// singlyLL.insertTail(4);
		//singlyLL.displayList(); // 2 1 3 2 4
		
		// Test insertTail2().
		SLLNode<Integer> singlyLL = null;
		singlyLL = insertTail2(singlyLL, 2);
		singlyLL = insertTail2(singlyLL, 1);
		singlyLL = insertTail2(singlyLL, 3);
		singlyLL = insertTail2(singlyLL, 2);
		singlyLL = insertTail2(singlyLL, 4);
		//singlyLL.displayList(); // 2 1 3 2 4
		
		// Test insertHead().
		singlyLL = singlyLL.insertHead(0);
		singlyLL = singlyLL.insertHead(5);
		singlyLL = singlyLL.insertHead(7);
		//singlyLL.displayList(); // 7 5 0 2 1 3 2 4
		// In the case that the list has only one node.
		SLLNode<Integer> singlyLL2 = new SLLNode<Integer>(2);
		singlyLL2 = singlyLL2.insertHead(5);
		//singlyLL2.displayList(); // 5 2

		// Test deleteNode().
		// Delete middle node.
		singlyLL = singlyLL.deleteNode(5);
		//singlyLL.displayList(); // 7 0 2 1 3 2 4
		// Delete tail node.
		singlyLL = singlyLL.deleteNode(4);
		//singlyLL.displayList(); // 7 0 2 1 3 2
		// Delete head node.
		singlyLL = singlyLL.deleteNode(7);
		//singlyLL.displayList(); // 0 2 1 3 2 
		// Delete the node of the list that has just one node.
		SLLNode<Integer> singlyLL3 = new SLLNode<Integer>(1);
		//singlyLL3.displayList(); // 1
		singlyLL3 = singlyLL3.deleteNode(1);
		//System.out.println(singlyLL3); // null
		// Delete node not existing.
		singlyLL = singlyLL.deleteNode(9);
		//singlyLL.displayList(); // 0 2 1 3 2
		
		// Test reverseAndClone().
		SLLNode<Integer> list = reverseAndClone(singlyLL);
		//list.displayList(); // 2 3 1 2 0
		// one node.
		SLLNode<Integer> singlyLL4 = new SLLNode<Integer>(1);
		//singlyLL4.displayList(); // 1
		
		// Test isEqualList().
		SLLNode<Integer> listReverse = reverseAndClone(list);
		//listReverse.displayList(); // 0 2 1 3 2 
		//System.out.println(isEqualList(singlyLL, listReverse)); // true
		
		// Test findMiddleNode(). 
		SLLNode<Integer> singlyLL5 = new SLLNode<Integer>(1);
		singlyLL5.insertTail(2);
		singlyLL5.insertTail(3);
		singlyLL5.insertTail(4);
		singlyLL5.insertTail(5);
		//singlyLL5.displayList(); // 1 2 3 4 5 
		//System.out.println(findMiddleNode(singlyLL5)); // 4
		singlyLL5.deleteNode(5);
		//singlyLL5.displayList(); // 1 2 3 4 
		//System.out.println(findMiddleNode(singlyLL5)); // 3

		// Test getSizeOfList().
		//singlyLL.displayList(); // 0 2 1 3 2 
		//System.out.println(getSizeOfList(singlyLL)); // 5

		// Test getKthNode();
		//singlyLL.displayList(); // 0 2 1 3 2 
		SLLNode<Integer> n = getKthNode(singlyLL, 5);
		//System.out.println(n.data); // 1
		// Because in Java, it's always 'Pass by value'.
		//System.out.println(singlyLL.data); // 0
		
		
		
		

		
		
	}
	
	// You can also pass in the head of list. 
	// Author: kei
	// October 30, 2016
	public static SLLNode<Integer> insertTail2(SLLNode<Integer> head, Integer data) {
		SLLNode<Integer> newNode = new SLLNode<Integer>(data);
		SLLNode<Integer> node = head;
		
		// There is no node. 
		if (node == null) {
			return newNode;
		}
		
		// Get to tail node.
		while (node.next != null) {
			node = node.next;
		}
		
		// Insert at tail.
		node.next = newNode;	
		
		return head;
	}
	
	// Reverse and clone the list.
	// Author: CtCI 6th p.217 + kei
	// Date  : October 19, 2016
	public static SLLNode<Integer> reverseAndClone(SLLNode<Integer> node) {
		SLLNode<Integer> head = null;
		
		while (node != null) {
			SLLNode<Integer> newNode = new SLLNode<Integer>(node.data);
			// Add-to-head pattern. 
			newNode.next = head;
			head = newNode;
			node = node.next;
		}
		
		return head;
	}
	
	// Check if two list match.
	// Author: CtCI 6th p.217 + kei
	// Date  : October 19, 2016
	public static boolean isEqualList(SLLNode<Integer> node1, 
			                          SLLNode<Integer> node2) {
		while (node1 != null && node2 != null) {
			if (node1.data != node2.data) {
				return false;
			}
			node1 = node1.next;
			node2 = node2.next;
		}

		// Note that not just return true here!
		return node1 == null && node2 == null;
	}
	
	// Find middle node/the beginning node of the second half 
	// when you don't know the size of the list. (Runner technique)
	// Author: CtCI 6th p.218 + kei
	// Date  : October 19, 2016
	public static int findMiddleNode(SLLNode<Integer> head) {
		SLLNode<Integer> fast = head;
		SLLNode<Integer> slow = head;
				
		// 'fast' is moving at 2x speed.
		while (fast != null && fast.next != null) {
			// Do something here.
			
			slow = slow.next;
			fast = fast.next.next;			
		}
		
		// 'head' list has odd number of elements, so skip the 
		// middle element.
		if (fast != null) {
			slow = slow.next;
		}
		
		// At this point, 'slow' indicates the beginning node 
		// of the second half of the list.
		return slow.data;
	}
	
	// Get the size of the list.
	// Author: CtCI 6th p.220 + kei
	// Date  : October 19, 2016
	public static int getSizeOfList(SLLNode<Integer> node) {
		if (node == null) {
			return -1;
		}
		int size = 0;
		while (node != null) {
			size++;
			node = node.next;
		}
		return size;		
	}
	
	// Get the k-th node.
	// Author: CtCI 6th p.222 + kei
	// Date  : October 23, 2016
	public static SLLNode<Integer> getKthNode(SLLNode<Integer> node, int k) {
		// You can use formal argument because in Java it's always 'pass by value'.
	    while (k > 0 && node != null) {
	        node = node.next;
	        k--;
	    }
	    // Note that if k > len - 1, then return null.
	    return node;
	}
	
	
	

}


// Singly Linked List. Not necessary?
// Author: アルゴリズムを学ぼう p.53 + kei.
// Date  : September 19, 2016
class SinglyLinkedList<T> {
	SLLNode<T> head;
}

// Node to be used in Singly Linked List.
// Author: kei
// Date  : October 19, 2016
class SLLNode<T> {
	T data;
	SLLNode<T> next;
	
	public SLLNode() {
	}
	
	public SLLNode(T data) {
		this.data = data;
	}
	
	public SLLNode(T data, SLLNode<T> node) {
		this.data = data;
		this.next = node;
	}
	
	// Insert at head. O(1)
	// 'this' is an object that has this method. 
	// Author: kei
	// Date  : October 19, 2016
	public SLLNode<T> insertHead(T data) {
		SLLNode<T> newNode = new SLLNode<T>(data);
		SLLNode<T> node = this;

		// Insert at head.
		newNode.next = node;
		return newNode;
	}
	
	// Insert at tail. O(N)
	// Author: kei + CtCI 6th p.92
	// Date  : October 19, 2016
	public void insertTail(T data) {
		SLLNode<T> newNode = new SLLNode<T>(data);
		SLLNode<T> node = this;
		
		// Get to tail node.
		while (node.next != null) {
			node = node.next;
		}
		
		// Insert at tail.
		node.next = newNode;		
	}

	// Delete node.
	// Author: kei + CtCI 6th p.93
	// Date  : October 19, 2016
	public SLLNode<T> deleteNode(T data) {
		SLLNode<T> node = this;
		
		// Delete head node.
		// Regardless of whether the list has just one node or more.
		if (node.data == data) {
			return node.next;
		}
		
		// Delete middle or tail node.
		// Get to the prev to the target.
		// You need prev node to delete node.
		while (node.next != null) {
			if (node.next.data == data) {
				node.next = node.next.next;
				return this;
			}	
			node = node.next;
		}
		
		// Not found.
		return this;
	}
	
	// Display every element of the list. 
	// Author: kei
	// Date  : October 19, 2016
	public void displayList() {
		SLLNode<T> node = this;
		while (node != null) {
			System.out.println(node.data);
			node = node.next;
		}
	}
	
	// Search key in SLL.
	// Author: kei + EPI p.101
	// Date  : October 19, 2016
	public SLLNode<T> search(SLLNode<T> node, T key) {
		
		while (node != null && node.data != key) {
			node = node.next;
		}
		
		return node;	
		
		// PIE p.36 + kei
		// October 30, 2016
//		if (node == null) {
//			throw new RuntimeException("Not found.");
//		} else {
//			return node;
//		}
	}
	
	// Insert a new node after a specified node.
	// Author: kei + EPI p.101
	// Date  : October 19, 2016
	public void insertAfter(SLLNode<T> node, SLLNode<T> newNode) {
		newNode.next = node.next;
		node.next = newNode;	
	}			


	
}

// Doubly Linked List.
// Author: アルゴリズムを学ぼう p.53 + kei.
// Date  : September 19, 2016
class DoublyLinkedList<T> {
	DLLNode<T> head;
	DLLNode<T> tail;
}

class DLLNode<T> {
	T value;
	DLLNode<T> prev;
	DLLNode<T> next;
}






























