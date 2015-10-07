//**************************************************************
//  Purpose   : This program is for a practice working with a 
//              binary search tree, and writing a recursive 
//              method to access the items in the binary search 
//              tree.
//  Author    : Fukutani, Kei
//  Date      : May 10, 2015
//  File Name : BinarySearchTree.java 
//**************************************************************

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class BinarySearchTree
{
    public static void main(String[] args) throws ClassNotFoundException
    {
        try
        {
            // (a) Read input from a file.
            Scanner keyboard = new Scanner(System.in);
            System.out.println();
            System.out.println("Tree sort program");
            System.out.print("Enter name of input file:  ");
            String fileName = keyboard.nextLine();
            File file = new File("data/" + fileName);
            keyboard.close();

            // (b) Build a binary search tree from the input.
            BinarySearchTree<Month, String> months = 
                    new BinarySearchTree<Month, String>();
            Month month;
            Scanner scanner = new Scanner(file);
            String line;
            while (scanner.hasNext())
            {
                line = scanner.nextLine();
                month = new Month(line);
                months.insert(month);
            }
            scanner.close();

            // (c) Display the binary search tree.
            System.out.println();
            System.out.println("Binary search tree:  ");
            System.out.println();
            months.display();
            System.out.println();

            // (d) Display the sorted items.
            System.out.println("Sorted items: \n");
            // Traverse the binary search tree in inorder to get
            // the items in ascending alphabetical order.
            TreeIterator<Month> treeIterator = 
                    new TreeIterator<Month>(months);
            treeIterator.setInorder();
            while (treeIterator.hasNext())
            {
                System.out.println(treeIterator.next());
            }
            System.out.println();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Error processing file");
        }
    } // end of main()
} // end of class Lab5

// **************** class Month ********************************
// An item in a binary search tree, which is called a record.
class Month extends KeyedItem<String>
{
    public Month(String month)
    // Constructor.
    {
        super(month);
    } // end constructor

    @Override
    public String toString()
    // Purpose: To display the search key in this item easily.
    // Preconditions: Search key data type is String.
    // Postconditions: Returns the String type of month name.
    {
        return getKey();
    } // end toString
} // end of class Month

// *************** class KeyedItem *****************************
// To make sure that an item in a binary tree has a search key and
// the search key data type implements the Comparable interface.
abstract class KeyedItem<KT extends Comparable<? super KT>>
{
    private KT searchKey;

    public KeyedItem(KT key)
    {
        searchKey = key;
    } // end constructor

    public KT getKey()
    {
        return searchKey;
    } // end getKey
} // end KeyedItem

// **************** class BinarySearchTree *********************
// Reference-Based ADT Binary Search Tree.
// Assumption: A tree contains at most one item with a
// given search key at any time.
class BinarySearchTree<T extends KeyedItem<KT>,
                       KT extends Comparable<? super KT>>
        extends BinaryTreeBasis<T>
{
    // inherits isEmpty(), makeEmpty(), getRootItem(), and
    // the use of the constructors from BinaryTreeBasis

    public void display()
    // Purpose: To display a binary search tree horizontally.
    // Preconditions: root is a reference to a root node of
    // binary search tree.
    // Postconditions: Display a binary search tree horizontally.
    {
        int indentation = 0;
        printTree(root, indentation);
    } // end of display()

    protected void printTree(TreeNode<T> root, int indentation)
    // Purpose: To display a binary search tree horizontally
    // using recursion.
    // Preconditions: root is a reference to a root node of
    // binary search tree. root.item has toString().
    // Postconditions: Display nodes in the binary tree, increasing
    // indentation by one level.
    {
        final int INDENTATION = 3;

        // Algorithm note:
        // 1) Traverse the right subtree down to the node which
        //    has no right child.
        // 2) Print the item of the node.
        // 3) Repeat 1) to 3) starting with the root node of
        //    left subtree of the node. If the node has no left child,
        //    then go back to the root node of the last 1)'s right subtree
        //    and go to 2).
        if (root != null)
        {
            printTree(root.rightChild, indentation + INDENTATION);

            if (indentation == 0)
            {
                System.out.println(root.item);
            }
            else
            {
                System.out.printf("%" + indentation + "s", " ");
                System.out.println(root.item);
            }

            printTree(root.leftChild, indentation + INDENTATION);
        }
    } // end of printTree(...)

    public BinarySearchTree()
    {
    } // end default constructor

    public BinarySearchTree(T rootItem)
    {
        super(rootItem);
    } // end constructor

    @Override
    public void setRootItem(T newItem) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    } // end setRootItem

    public void insert(T newItem)
    {
        root = insertItem(root, newItem);
    } // end insert

    public T retrieve(KT searchKey)
    {
        return retrieveItem(root, searchKey);
    } // end retrieve

    public void delete(KT searchKey) throws TreeException
    {
        root = deleteItem(root, searchKey);
    } // end delete

    public void delete(T item) throws TreeException
    {
        root = deleteItem(root, item.getKey());
    } // end delete

    protected TreeNode<T> insertItem(TreeNode<T> tNode, T newItem)
    {
        TreeNode<T> newSubtree;
        if (tNode == null)
        {
            // position of insertion found; insert after leaf
            // create a new node
            tNode = new TreeNode<T>(newItem, null, null);
            return tNode;
        } // end if
        T nodeItem = tNode.item;

        // search for the insertion position

        if (newItem.getKey().compareTo(nodeItem.getKey()) < 0)
        {
            // search the left subtree
            newSubtree = insertItem(tNode.leftChild, newItem);
            tNode.leftChild = newSubtree;
            return tNode;
        }
        else
        { // search the right subtree
            newSubtree = insertItem(tNode.rightChild, newItem);
            tNode.rightChild = newSubtree;
            return tNode;
        } // end if
    } // end insertItem

    protected T retrieveItem(TreeNode<T> tNode, KT searchKey)
    {
        T treeItem;
        if (tNode == null)
        {
            treeItem = null;
        }
        else
        {
            T nodeItem = tNode.item;
            if (searchKey.compareTo(nodeItem.getKey()) == 0)
            {
                // item is in the root of some subtree
                treeItem = tNode.item;
            }
            else if (searchKey.compareTo(nodeItem.getKey()) < 0)
            {
                // search the left subtree
                treeItem = retrieveItem(tNode.leftChild, searchKey);
            }
            else
            { // search the right subtree
                treeItem = retrieveItem(tNode.rightChild, searchKey);
            } // end if
        } // end if
        return treeItem;
    } // end retrieveItem

    protected TreeNode<T> deleteItem(TreeNode<T> tNode, KT searchKey)
    {
        // Calls: deleteNode.
        TreeNode<T> newSubtree;
        if (tNode == null)
        {
            throw new TreeException("TreeException: Item not found");
        }
        else
        {
            T nodeItem = tNode.item;
            if (searchKey.compareTo(nodeItem.getKey()) == 0)
            {
                // item is in the root of some subtree
                tNode = deleteNode(tNode); // delete the item
            }
            // else search for the item
            else if (searchKey.compareTo(nodeItem.getKey()) < 0)
            {
                // search the left subtree
                newSubtree = deleteItem(tNode.leftChild, searchKey);
                tNode.leftChild = newSubtree;
            }
            else
            { // search the right subtree
                newSubtree = deleteItem(tNode.rightChild, searchKey);
                tNode.rightChild = newSubtree;
            } // end if
        } // end if
        return tNode;
    } // end deleteItem

    protected TreeNode<T> deleteNode(TreeNode<T> tNode)
    {
        // Algorithm note: There are four cases to consider:
        // 1. The tNode is a leaf.
        // 2. The tNode has no left child.
        // 3. The tNode has no right child.
        // 4. The tNode has two children.
        // Calls: findLeftmost and deleteLeftmost
        T replacementItem;

        // test for a leaf
        if ((tNode.leftChild == null) && (tNode.rightChild == null))
        {
            return null;
        } // end if leaf

        // test for no left child
        else if (tNode.leftChild == null)
        {
            return tNode.rightChild;
        } // end if no left child

        // test for no right child
        else if (tNode.rightChild == null)
        {
            return tNode.leftChild;
        } // end if no right child

        // there are two children:
        // retrieve and delete the inorder successor
        else
        {
            replacementItem = findLeftmost(tNode.rightChild);
            tNode.item = replacementItem;
            tNode.rightChild = deleteLeftmost(tNode.rightChild);
            return tNode;
        } // end if
    } // end deleteNode

    protected T findLeftmost(TreeNode<T> tNode)
    {
        if (tNode.leftChild == null)
        {
            return tNode.item;
        }
        else
        {
            return findLeftmost(tNode.leftChild);
        } // end if
    } // end findLeftmost

    protected TreeNode<T> deleteLeftmost(TreeNode<T> tNode)
    {
        if (tNode.leftChild == null)
        {
            return tNode.rightChild;
        }
        else
        {
            tNode.leftChild = deleteLeftmost(tNode.leftChild);
            return tNode;
        } // end if
    } // end deleteLeftmost

} // end BinarySearchTree

// **************** class TreeIterator *************************
// For binary tree traversals in preorder, inorder, or postorder.
class TreeIterator<T> implements Iterator<T>
{
    private BinaryTreeBasis<T> binTree;
    private TreeNode<T> currentNode;
    private LinkedList<TreeNode<T>> queue; // from JCF

    public TreeIterator(BinaryTreeBasis<T> bTree)
    {
        binTree = bTree;
        currentNode = null;
        // empty queue indicates no traversal type currently
        // selected or end of current traversal has been reached
        queue = new LinkedList<TreeNode<T>>();
    } // end constructor

    @Override
    public boolean hasNext()
    {
        return !queue.isEmpty();
    } // end hasNext

    @Override
    public T next() throws java.util.NoSuchElementException
    {
        currentNode = queue.remove();
        return currentNode.item;
    } // end next

    @Override
    public void remove() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    } // end remove

    public void setPreorder()
    {
        queue.clear();
        preorder(binTree.root);
    } // setPreOrder

    public void setInorder()
    {
        queue.clear();
        inorder(binTree.root);
    } // end setInorder

    public void setPostorder()
    {
        queue.clear();
        postorder(binTree.root);
    } // end setPostorder

    private void preorder(TreeNode<T> treeNode)
    {
        if (treeNode != null)
        {
            queue.add(treeNode);
            preorder(treeNode.leftChild);
            preorder(treeNode.rightChild);
        } // end if
    } // end preorder

    private void inorder(TreeNode<T> treeNode)
    {
        if (treeNode != null)
        {
            inorder(treeNode.leftChild);
            queue.add(treeNode);
            inorder(treeNode.rightChild);
        } // end if
    } // end inorder

    private void postorder(TreeNode<T> treeNode)
    {
        if (treeNode != null)
        {
            postorder(treeNode.leftChild);
            postorder(treeNode.rightChild);
            queue.add(treeNode);
        } // end if
    } // end postorder
} // end TreeIterator

// **************** class BinaryTreeBasis **********************
// A basic binary tree class for Reference-Based ADT Binary Tree.
abstract class BinaryTreeBasis<T>
{
    protected TreeNode<T> root;

    public BinaryTreeBasis()
    {
        root = null;
    } // end default constructor

    public BinaryTreeBasis(T rootItem)
    {
        root = new TreeNode<T>(rootItem, null, null);
    } // end constructor

    public boolean isEmpty()
    {
        // Returns true if the tree is empty, else returns false.
        return root == null;
    } // end isEmpty

    public void makeEmpty()
    {
        // Removes all nodes from the tree.
        root = null;
    } // end makeEmpty

    public T getRootItem() throws TreeException
    {
        // Returns the item in the tree's root.
        if (root == null)
        {
            throw new TreeException("TreeException: Empty tree");
        }
        else
        {
            return root.item;
        } // end if
    } // end getRootItem

    public abstract void setRootItem(T newItem);
    // Throws UnsupportedOperationException if operation
    // is not supported.

} // end BinaryTreeBasis

// **************** class TreeNode *****************************
// A node in a tree structure.
class TreeNode<T>
{
    T item;
    TreeNode<T> leftChild;
    TreeNode<T> rightChild;

    public TreeNode(T newItem)
    {
        // Initializes tree node with item and no children.
        item = newItem;
        leftChild = null;
        rightChild = null;
    } // end constructor

    public TreeNode(T newItem, TreeNode<T> left, TreeNode<T> right)
    {
        // Initializes tree node with item and
        // the left and right children references.
        item = newItem;
        leftChild = left;
        rightChild = right;
    } // end constructor
} // end TreeNode

// **************** class TreeException ************************
// This exception is thrown when tree is empty.
class TreeException extends RuntimeException
{
    public TreeException(String s)
    {
        super(s);
    } // end constructor
} // end TreeException
