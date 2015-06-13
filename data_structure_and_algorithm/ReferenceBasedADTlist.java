//**************************************************************
//  Purpose   : This program is for a practice working with
//              linked lists.
//  Author    : Fukutani, Kei
//  Date      : March 19, 2015
//  File Name : ReferenceBasedADTlist.java 
//**************************************************************

import java.util.Scanner;

public class ReferenceBasedADTlist
{
    public static void main(String[] args)
    // Allow user to add or remove item in a linked list,
    // and also reverse the list.
    // Pre: Use an original linked list, ListReferenceBased.
    // Post: After user manipulating the list, display the info 
    //       of the list.
    {
        try (Scanner keyboard = new Scanner(System.in))
        {
            ListInterface li = new ListReferenceBased();
            
            // Repeat prompting user until user exits.
            while (true)
            {
                System.out.println();
                System.out.println("1) Add item \n" + 
                                   "2) Remove item \n" + 
                                   "3) Reverse list \n" + 
                                   "4) Exit program \n");
                System.out.print("Enter your choice (1-4): ");
                int inputMenuNum = keyboard.nextInt();
                
                switch (inputMenuNum)
                {
                    // 1) Add item
                    case 1: 
                        System.out.print("Enter position: ");
                        int inputPosition = keyboard.nextInt();
                        if (inputPosition <= 0 || inputPosition > li.size() + 1)
                        {
                            System.out.println("Invalid position");
                            break;
                        }
                        
                        // Clear the buffer.
                        keyboard.nextLine();
                        
                        System.out.print("Enter item to add: ");
                        String inputItem = keyboard.nextLine();
                        
                        li.add(inputPosition, inputItem);
                        // Display the info about the list.
                        displayInfo(li);
                        break;
                        
                    // 2) Remove item
                    case 2: 
                        System.out.print("Enter position: ");
                        inputPosition = keyboard.nextInt();
                        if (inputPosition <= 0 || inputPosition > li.size())
                        {
                            System.out.println("Invalid position");
                            break;
                        }
                        
                        li.remove(inputPosition);
                        // Display the info about the list.
                        displayInfo(li);
                        break;
                    
                    // 3) Reverse list   
                    case 3: 
                        li.reverseList();
                        // Display the info about the list.
                        displayInfo(li);
                        break;
                       
                    // 4) Exit program
                    case 4: 
                        System.out.println();
                        System.out.println("Goodbye");
                        System.exit(0);

                    default:
                        System.out.println("Invalid menu number");
                        break;
                } // end of switch (inputMenuNum)  
            } // end of while (true)
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    } // end of main()
    
    public static void displayInfo(ListInterface li)
    // Display the info about the list li.
    {
        System.out.println();
        try
        {
            // Display the items in the list.
            System.out.print("List: ");
            int length = li.size();
            for (int i = 1; i <= length; i++)
            {
                System.out.print(li.get(i) + " ");
            }
            System.out.print("\n");
            
            // Display the number of items in the list.
            System.out.println("Number of items: " + li.size());
            
            // Display whether the list is sorted or not. 
            System.out.println("List is " + 
                               ((li.isSorted()) ? "" : "not ") + 
                               "in sorted order");               
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    } // end of displayInfo()
} // end of class Lab3

// ************ class ListReferenceBased ************************
// Reference-based implementation of ADT list.
class ListReferenceBased implements ListInterface
{
    private Node head; // Reference to linked list of items.

    public ListReferenceBased()
    // Constructor.
    {
        head = null;
    }

    @Override
    public boolean isEmpty()
    // Return true if the list is empty, else returns false.
    {
        return (size() == 0);
    }

    @Override
    public int size()
    // Obtain the number of items in the list.
    {
        int numItems = 0;

        for (Node curr = head; curr != null; curr = curr.next)
        {
            numItems++;
        }

        return numItems;
    } // end of size()

    private Node find(int index)
    // Locate a specified node in a linked list.
    // Pre: Index is the number of the desired node. Assumes
    //      that 1 <= index <= size()
    // Post: Return a reference to the index-th node.
    {
        Node curr = head;

        // curr refers to the index-th Node after this loop.
        for (int skip = 1; skip < index; skip++)
        {
            curr = curr.next;
        }

        return curr;
    } // end of find()

    @Override
    public Comparable get(int index) throws ListIndexOutOfBoundsException
    // Get the index-th item in the list.
    // Pre: 1 <= index <= size()
    // Post: Return the item of the index-th node.
    {
        if (index >= 1 && index <= size())
        {
            // Get a reference to the index-th node, 
            // then get the data in the node.
            Node curr = find(index);
            Comparable dataItem = curr.item;
            return dataItem;
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                    "List index out of bounds on get");
        }
    } // end of get()

    @Override
    public void add(int index, Comparable item) throws ListIndexOutOfBoundsException
    // Insert an item into the index-th position of the list.
    // Pre: 1 <= index <= size() + 1
    // Post: Produce a new linked list with the new node.
    {
        if (index >= 1 && index <= size() + 1)
        {
            if (index == 1)
            {
                // Insert the new node containing item at
                // beginning of list.
                Node newNode = new Node(item, head);
                head = newNode;
            }
            else
            {
                Node prev = find(index - 1);

                // Insert the new node containing item after
                // the node that prev references.
                Node newNode = new Node(item, prev.next);
                prev.next = newNode;
            }
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                    "List index out of bounds on add");
        }
    } // end of add()

    @Override
    public void remove(int index) throws ListIndexOutOfBoundsException
    // Remove an item from the index-th position of the list. 
    // Pre: 1 <= index <= size()
    // Post: Produce a new linked list without the node. 
    {
        if (index >= 1 && index <= size())
        {
            if (index == 1)
            {
                // Delete the first node from the list.
                head = head.next;
            }
            else
            {
                Node prev = find(index - 1);
                // Delete the node after the node that prev
                // references, save reference to node.
                Node curr = prev.next;
                prev.next = curr.next;
            }
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                    "List index out of bounds on remove");
        }
    } // end of remove()
    
    @Override
    public void removeAll()
    // Remove all the items in the list.
    {
        // Setting head to null causes list to be
        // unreachable and thus marked for garbage
        // collection.
        head = null;
    } 

    @Override
    public boolean isSorted()
    // Returns true if the list is sorted in ascending order, 
    // else returns false.
    {
        if (head == null)
        // No node in the list.
        {
            return true;            
        }
        else if (head.next == null)
        // Only one node in the list.
        {
            return true;            
        }
        else
        // Two or more nodes in the list. 
        {
            Node prev = head;
            Node curr = head.next;
            
            // This time, descending order means not sorted.
            if (curr != null && prev.item.compareTo(curr.item) > 0)
            {
                return false;                    
            }
            
            // Check if the list is sorted in ascending order.
            while (curr != null && prev.item.compareTo(curr.item) < 0)
            {
                prev = curr;
                curr = curr.next; 
                if (curr != null && prev.item.compareTo(curr.item) > 0)
                {
                    return false;                    
                }
            }
            
            return true;            
        }
    } // end of isSorted()
    
    @Override
    public void reverseList()
    // Adjust the node references so that the items are in the reverse 
    // of their original order, without creating any new nodes.
    {
        if (head != null && head.next != null)
        // Two or more nodes in the list.
        {
            Node temp = null;
            Node prev = null;            
            Node curr = head;
            
            // Firstly, take the first node from old list, and make it 
            // a last node of reversed list.
            // Then, take the second node from old list, and make it
            // a second last node of reversed list, and go on. 
            // Avoiding garbage collection requires some tips.
            while (curr.next != null)
            {
                prev = curr;
                curr = curr.next;
                prev.next = temp;
                temp = prev;
            }
            
            // Lastly, take the last node from old list, and make it
            // a first node of reversed list. 
            curr.next = prev;
            head = curr;
        }
    } // end of reverseList()
} // end of class ListReferenceBased

// ************ interface ListInterface *************************
// Interface for the ADT list.
interface ListInterface
{
    // list operations:
    public boolean isEmpty();

    public int size();

    public void add(int index, Comparable item) throws ListIndexOutOfBoundsException;

    public void remove(int index) throws ListIndexOutOfBoundsException;

    public Comparable get(int index) throws ListIndexOutOfBoundsException;

    public void removeAll();
    
    public boolean isSorted();
    
    public void reverseList();    
} // end of interface ListInterface

// ************ class Node **************************************
// A node for a linked list of objects.
class Node
{
    Comparable item;
    Node next;

    Node(Comparable newItem)
    // Constructor 1.
    {
        item = newItem;
        next = null;
    }

    Node(Comparable newItem, Node nextNode)
    // Constructor 2.
    {
        item = newItem;
        next = nextNode;
    }
} // end of class Node

// ************ class ListIndexOutOfBoundsException *************
// This exception is thrown when user inputs index out of range.
class ListIndexOutOfBoundsException extends Exception
{
    ListIndexOutOfBoundsException(String message)
    // Sets up the exception object with a particular message.
    {
        super(message);
    }
}
