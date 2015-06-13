//**************************************************************
//  Purpose   : This program is for a practice working with
//              ADT Stack and is about converting infix expression
//              to postfix form. 
//  Author    : Fukutani, Kei
//  Date      : April 20, 2015
//  File Name : Stack.java 
//**************************************************************

import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Stack
{
    public static void main(String[] args)
    // Purpose: Allow user to input infix expression and this program 
    //          converts it to postfix form and display it. And also, 
    //          evaluates it and display the result. 
    // Preconditions: Within infix expression, operators are limited to
    //                '+', '-', '*', or '/'. 
    // Postconditions: The postfix form is created. The result of the
    //                 infix expression is evaluated. 
    {
        Scanner keyboard = new Scanner(System.in);
        char again;
        do
        {
            System.out.print("Input infix expression: ");
            String num = keyboard.nextLine();
            
            Calculator calculator = new Calculator(num);
            System.out.println("Infix: " + calculator.getInfix());
            System.out.println("Postfix: " + calculator.getPostfix());              
            System.out.println("Result: " + calculator.evaluate());
            
            System.out.print("Continue? (Enter Y or N): ");
            String input = keyboard.nextLine();
            again = input.charAt(0);
            System.out.println();
        } while (again == 'y' || again == 'Y');
        
        keyboard.close();

        
        //System.err.println("done.");  
        return;
    } // end of main()
} // end of class Lab4

// ***************** class Calculator **************************
// Creates postfix form from infix expression and also evaluates
// the infix expression. 
class Calculator
{
    private String infix;
    private String postfix = "";

    public Calculator(String exp)
    // Constructor.
    // Initializes infix expression and converts it to postfix form.
    {
        infix = exp;
        convertPostfix();
    }

    private void convertPostfix()
    // Purpose: Converts infix expression to postfix form. 
    // Preconditions: 'infix' is not empty. 
    // Postconditions: Creates postfix form and returns true if successful.
    {       
        try
        {
            StackListBased aStack = new StackListBased();
            char ch;
            
            for (int i = 0; i < infix.length(); i++)
            {
                ch = infix.charAt(i);
                if (Character.getNumericValue(ch) >= 0 && Character.getNumericValue(ch) <= 9)
                // operand
                {
                    postfix += ch + " ";    
                } 
                else if (ch == '(')
                {
                    aStack.push(ch);
                }
                else if (ch == ')')
                {
                    while ((char) aStack.peek() != '(')
                    {
                        postfix += aStack.pop() + " ";                    
                    }
                    
                    // Remove the open parenthesis. 
                    Object openParen = aStack.pop();                
                }
                else if (ch == '+' || ch == '-' || 
                         ch == '*' || ch == '/')
                {
                    // Process stack operators of greater precedence. 
                    while (!aStack.isEmpty() && 
                           (char) aStack.peek() != '(' && 
                           getPrecedence(ch) <= getPrecedence((char) aStack.peek()))
                    {
                        postfix += aStack.pop() + " ";
                    }
                    
                    // Save new operator.
                    aStack.push(ch);                 
                } 
                else
                {
                    System.err.println("Error on convertPostfix: Failed to create postfix.\n" + 
                                       "Please enter 0-9, (, ), +, -, *, or /.");
                }
            } // end of for (int i = 0; i < infix.length(); i++)
            
            while (!aStack.isEmpty())
            {
                postfix += aStack.pop() + " ";                
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    } // end of convertPostfix()
    
    private int getPrecedence(char ch)
    // Purpose: Defines precedence of the operator ch.
    // Preconditions: ch is '+', '-', '*', or '/'.
    // Postconditions: Returns the precedence of the operator ch. 
    {
        if (ch == '+' || ch == '-')
        {
            return 1;
        }
        else if (ch == '*' || ch == '/')
        {
            return 2;            
        }
        else
        {
            System.err.println("Error: Unhundled operator.");
            return 0;
        }       
    } // end of getPrecedence()

    public String getInfix()
    // Purpose: Gets the input infix expression.
    // Preconditions: Nothing.
    // Postconditions: Returns String 'infix'.
    {
        return infix;
    }
    
    public String getPostfix()
    // Purpose: Gets the resulting postfix expression.
    // Preconditions: Nothing.
    // Postconditions: Returns String 'postfix'.
    {
        return postfix;
    }

    public int evaluate()
    // Purpose: Evaluates the infix expression.
    // Preconditions: 'infix' is valid and not empty. 
    // Postconditions: Returns the result of the infix expression. 
    {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");        
        int result = 0;
        try
        {
            result = (int) engine.eval(infix);
        }
        catch (ScriptException e)
        {
            e.printStackTrace();
        }    
        
        return result;
    } // end of evaluate()
} // end of class Calculator

// ***************** class StackListBased ***********************
// ADT Stack that uses reference-based ADT list.
class StackListBased implements StackInterface
{
    private ListIntf list;

    public StackListBased()
    {
        list = new ListReferenceBased();
    } // end default constructor

    @Override
    public boolean isEmpty()
    {
        return list.isEmpty();
    } // end isEmpty

    @Override
    public void push(Object newItem)
    {
        try
        {
            list.add(0, newItem);
        }
        catch (ListIndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
    } // end push

    @Override
    public Object pop() throws StackException
    {
        if (!list.isEmpty())
        {
            Object temp = null;
            try
            {
                temp = list.get(0);
                list.remove(0);
            }
            catch (ListIndexOutOfBoundsException e)
            {
                e.printStackTrace();
            }
            return temp;
        }
        else
        {
            throw new StackException("StackException on " + "pop: stack empty");
        } // end if
    } // end pop

    @Override
    public void popAll()
    {
        list.removeAll();
    } // end popAll

    @Override
    public Object peek() throws StackException
    {
        Object item = null;

        if (!isEmpty())
        {
            try
            {
                item = list.get(0);
            }
            catch (ListIndexOutOfBoundsException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            throw new StackException("StackException on " + "peek: stack empty");
        } // end if

        return item;
    } // end peek
} // end of class StackListBased

// ***************** interface StackInterface ******************
// Interface for ADT Stack.
interface StackInterface
{
    public boolean isEmpty();
    // Determines whether the stack is empty.
    // Precondition: None.
    // Postcondition: Returns true if the stack is empty;
    // otherwise returns false.

    public void popAll();
    // Removes all the items from the stack.
    // Precondition: None.
    // Postcondition: Stack is empty.

    public void push(Object newItem) throws StackException;
    // Adds an item to the top of a stack.
    // Precondition: newItem is the item to be added.
    // Postcondition: If insertion is successful, newItem
    // is on the top of the stack.
    // Exception: Some implementations may throw
    // StackException when newItem cannot be placed on
    // the stack.

    public Object pop() throws StackException;
    // Removes the top of a stack.
    // Precondition: None.
    // Postcondition: If the stack is not empty, the item
    // that was added most recently is removed from the
    // stack and returned.
    // Exception: Throws StackException if the stack is
    // empty.

    public Object peek() throws StackException;
    // Retrieves the top of a stack.
    // Precondition: None.
    // Postcondition: If the stack is not empty, the item
    // that was added most recently is returned. The
    // stack is unchanged.
    // Exception: Throws StackException if the stack is
    // empty.
} // end of interface StackInterface

// **************** class ListReferenceBased *******************
// Reference-based implementation of ADT list.
class ListReferenceBased implements ListIntf
{
    // reference to linked list of items
    private Node head;
    private int numItems; // number of items in list

    // definitions of constructors and methods
    public ListReferenceBased()
    {
        numItems = 0;
        head = null;
    } // end default constructor

    @Override
    public boolean isEmpty()
    {
        return numItems == 0;
    } // end isEmpty

    @Override
    public int size()
    {
        return numItems;
    } // end size

    private Node find(int index)
    {
        // --------------------------------------------------
        // Locates a specified node in a linked list.
        // Precondition: index is the number of the desired
        // node. Assumes that 1 <= index <= numItems+1
        // Postcondition: Returns a reference to the desired
        // node.
        // --------------------------------------------------
        Node curr = head;
        for (int skip = 0; skip < index; skip++)
        {
            curr = curr.next;
        } // end for
        return curr;
    } // end find

    @Override
    public Object get(int index) throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            // get reference to node, then data in node
            Node curr = find(index);
            Object dataItem = curr.item;
            return dataItem;
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                    "List index out of bounds on get");
        } // end if
    } // end get

    @Override
    public void add(int index, Object item) throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems + 1)
        {
            if (index == 0)
            {
                // insert the new node containing item at
                // beginning of list
                Node newNode = new Node(item, head);
                head = newNode;
            }
            else
            {
                Node prev = find(index - 1);

                // insert the new node containing item after
                // the node that prev references
                Node newNode = new Node(item, prev.next);
                prev.next = newNode;
            } // end if
            numItems++;
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                    "List index out of bounds on add");
        } // end if
    } // end add

    @Override
    public void remove(int index) throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            if (index == 0)
            {
                // delete the first node from the list
                head = head.next;
            }
            else
            {
                Node prev = find(index - 1);
                // delete the node after the node that prev
                // references, save reference to node
                Node curr = prev.next;
                prev.next = curr.next;
            } // end if
            numItems--;
        } // end if
        else
        {
            throw new ListIndexOutOfBoundsException(
                    "List index out of bounds on remove");
        } // end if
    } // end remove

    @Override
    public void removeAll()
    {
        // setting head to null causes list to be
        // unreachable and thus marked for garbage
        // collection
        head = null;
        numItems = 0;
    } // end removeAll
} // end of class ListReferenceBased

// **************** interface ListIntf *************************
// Interface for the ADT list
interface ListIntf
{
    // list operations:
    public boolean isEmpty();

    public int size();

    public void add(int index, Object item) throws ListIndexOutOfBoundsException;

    public void remove(int index) throws ListIndexOutOfBoundsException;

    public Object get(int index) throws ListIndexOutOfBoundsException;

    public void removeAll();
} // end of interface ListIntf

// ************ class Node *************************************
// A node for a linked list of objects.
class Node
{
    Object item;
    Node next;

    Node(Object newItem)
    // Constructor 1.
    {
        item = newItem;
        next = null;
    }

    Node(Object newItem, Node nextNode)
    // Constructor 2.
    {
        item = newItem;
        next = nextNode;
    }
} // end of class Node

// ************ class ListIndexOutOfBoundsException ************
// This exception is thrown when user inputs index out of range.
class ListIndexOutOfBoundsException extends Exception
{
    ListIndexOutOfBoundsException(String message)
    // Sets up the exception object with a particular message.
    {
        super(message);
    }
}

// ************ class StackException ****************************
// This exception is thrown when stack is empty.
class StackException extends Exception
{
    StackException(String message)
    // Sets up the exception object with a particular message.
    {
        super(message);
    }
}
