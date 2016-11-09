package whiteboard;

public class DivideSinglyLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedListNode sll = new LinkedListNode(4);
		sll.append(7);
		sll.append(5);
		sll.append(2);
		sll.append(1);
		sll.append(3);
		sll.append(8);
		
		//sll = devideSinglyLinkedList(sll, 5);
		sll = devideSinglyLinkedList2(sll, 5);
				
		// Display the list.
		LinkedListNode n = sll;
		while (n != null) {
			System.out.println(n.data);
			n = n.next;
		}	
		

	}
	
	private static LinkedListNode devideSinglyLinkedList(LinkedListNode node, int x) {
	    LinkedListNode smallList = null;
	    LinkedListNode bigList = null;

	    // Divide the list 'node' into two lists.
	    while (node != null) {
	        // Necessary for moving to the next node in the original list.
	        // Because node.next gets null or a node in the new list. 
	        LinkedListNode nextNode = node.next;
	        
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
	    LinkedListNode head = smallList;
	    LinkedListNode p = smallList;
	    while (p.next != null) {
	        p = p.next;
	    }
	    // Merge.
	    p.next = bigList;

	    return head;
	}


	private static LinkedListNode devideSinglyLinkedList2(LinkedListNode node, int x) {
	    LinkedListNode head = node;
	    LinkedListNode tail = node;

	    // Divide the list 'node' into two lists.
	    while (node != null) {
	        // Necessary for moving to the next node in the original list.
	        // Because node.next gets null or a node in the new list. 
	        LinkedListNode nextNode = node.next;
	        
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








	
	

}



