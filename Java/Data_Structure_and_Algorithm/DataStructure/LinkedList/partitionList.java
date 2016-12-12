// Devide a singly linked list. 
// CtCI 6th 2.4 

package whiteboard;

public class ForCopy {

	public static void main(String[] args) {
		
		
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(7);
        list.add(0);
        list.add(2);
        list.add(4);
		
		SLLNode<Integer> newHead = partitionList(list.head, 3);
		newHead.displayList(); // 1 0 2 3 7 4 

		
		
		
		
		
		

	}
	
	
	// Author: kei
	// Date  : December 7, 2016
	public static SLLNode<Integer> partitionList(SLLNode<Integer> head, int x) {
		
		SLLNode<Integer> beforeHead = null, afterHead = null, node = head;
		
		while (node != null) {
			SLLNode<Integer> newNode = new SLLNode<Integer>(node.data);
			if (node.data < x) {
				newNode.next = beforeHead;
				beforeHead = newNode;				
			} else {
				newNode.next = afterHead;
				afterHead = newNode;
			}
			
			node = node.next;			
		}
		
		if (beforeHead != null) {
			SLLNode<Integer> n = beforeHead;
			while (n.next != null) {
				n = n.next;
			}
			n.next = afterHead;		
			
			return beforeHead;
		} else {
			return afterHead;
		}

	}


	// Author: CtCI 6th 2.4 p.213 + kei
	// Date  : September 18, 2016
	private static SLLNode<Integer> partitionList2(SLLNode<Integer> node, int x) {
		SLLNode<Integer> head = node;
		SLLNode<Integer> tail = node;

	    // Divide the list 'node' into two lists.
	    while (node != null) {
	        // Necessary for moving to the next node in the original list.
	        // Because node.next gets null or a node in the new list. 
	    	SLLNode<Integer> nextNode = node.next;
	        
	        if (node.data < x) {
	            // Insert node at head.
	            node.next = head;
	            head = node;
	        } else {
	            // Insert node at tail.
	            tail.next = node;
	            tail = node;
	        }

	        node = nextNode;
	    }
	    tail.next = null;
	    
	    return head;
	}

	
	// Author: Cracking the Coding Interview 2.4 p.189 + kei
	// Date  : September 18, 2016
	private static SLLNode<Integer> partitionList3(SLLNode<Integer> node, int x) {
		SLLNode<Integer> smallList = null;
		SLLNode<Integer> bigList = null;

	    // Divide the list 'node' into two lists.
	    while (node != null) {
	        // Necessary for moving to the next node in the original list.
	        // Because node.next gets null or a node in the new list. 
	    	SLLNode<Integer> nextNode = node.next;
	        
	        if (node.data < x) {
	            // Insert node at head of the smallList.
	            node.next = smallList;
	            smallList = node;
	        } else {
	            // Insert node at head of the bigList.
	            node.next = bigList;
	            bigList = node;
	        }

	        node = nextNode;
	    }

	    if (smallList == null) {
	        return bigList;
	    }

	    // Merge.
	    // Find the end of the 'smallList' and merge.
	    SLLNode<Integer> head = smallList;
	    SLLNode<Integer> p = smallList;
	    while (p.next != null) {
	        p = p.next;
	    }
	    // Merge.
	    p.next = bigList;

	    return head;
	}
	
	








	
	

}



