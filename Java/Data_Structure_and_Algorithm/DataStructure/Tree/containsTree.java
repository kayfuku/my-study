// Check if a tree contains another tree. 
// SimpleTreeNode is in the BinarySearchTree_usage.java.
// Author: CtCI 4.10 p.266 + kei
// Date  : November 11, 2016

package whiteboard;


public class Lab_whiteboard {

    public static void main(String[] args) {

        
        SimpleBinarySearchTree2 bst = new SimpleBinarySearchTree2();

        // Push data into BST.
        SimpleTreeNode bstNode1 = buildBinarySearchTree1(bst);
        SimpleTreeNode bstNode2 = buildBinarySearchTree2(bst);
        SimpleTreeNode bstNode3 = buildBinarySearchTree3(bst);
        
        bst.inorder(bstNode1); // 1 3 4 6 9 12 14 
        System.out.println();
        bst.inorder(bstNode2); // 1 3 4  
        System.out.println();
        bst.inorder(bstNode3); // 9 12 14  
        System.out.println();
        
        // Prepare a Binary Tree, not Binary Search Tree.
        //                    6
        //                  /   \
        //                 3     3
        //                / \   / \
        //               1   4 9   14
        SimpleTreeNode bt1 = new SimpleTreeNode(6);
        bt1.left = new SimpleTreeNode(3);
        bt1.left.left = new SimpleTreeNode(1);
        bt1.left.right = new SimpleTreeNode(4);
        bt1.right = new SimpleTreeNode(3);
        bt1.right.left = new SimpleTreeNode(9);
        bt1.right.right = new SimpleTreeNode(14);
        inorder(bt1); // 1 3 4 6 9 3 14 

        System.out.println();
        
        // Prepare a Binary Tree, not Binary Search Tree.
        //                   3
        //                  / \
        //                 9   14
        SimpleTreeNode bt2 = new SimpleTreeNode(3);
        bt2.left = new SimpleTreeNode(9);
        bt2.right = new SimpleTreeNode(14);
        inorder(bt2); // 9 3 14 

        
        // Test containsTree().
        //System.out.println(containsTree(bstNode1, bstNode2)); // true
        //System.out.println(containsTree(bstNode1, bstNode3)); // true
        
        // Test containsTreeBad().
        //System.out.println(containsTreeBad(bstNode1, bstNode2)); // true
        //System.out.println(containsTreeBad(bstNode1, bstNode3)); // true
        //System.out.println(containsTreeBad(bt1, bt2)); // false
        
        // Test containsTree2().
        //System.out.println(containsTree2(bstNode1, bstNode2)); // true
        //System.out.println(containsTree2(bstNode1, bstNode3)); // true
        //System.out.println(containsTree2(bt1, bt2)); // true 

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end of main().
    
    // Build Binary Search Tree.
    //
    //                   6
    //                  /  \
    //                 3    12
    //                / \   / \
    //               1   4 9  14
    // Author: kei
    // Date  : September 24, 2016
    public static SimpleTreeNode buildBinarySearchTree1(
            SimpleBinarySearchTree2 bst) {
        SimpleTreeNode tNode = null;

        tNode = bst.insert(tNode, 6);
        tNode = bst.insert(tNode, 3);
        tNode = bst.insert(tNode, 12);
        tNode = bst.insert(tNode, 1);
        tNode = bst.insert(tNode, 9);
        tNode = bst.insert(tNode, 4);
        tNode = bst.insert(tNode, 14);
        
        return tNode;
    }
    
    // Build Binary Search Tree.
    //                 3    
    //                / \   
    //               1   4
    // Author: kei
    // Date  : November 11, 2016
    public static SimpleTreeNode buildBinarySearchTree2(
            SimpleBinarySearchTree2 bst) {
        SimpleTreeNode tNode = null;

        tNode = bst.insert(tNode, 3);
        tNode = bst.insert(tNode, 1);
        tNode = bst.insert(tNode, 4);
        
        return tNode;
    }
    
    // Build Binary Search Tree.
    //                 12    
    //                /  \   
    //               9    14
    // Author: kei
    // Date  : November 11, 2016
    public static SimpleTreeNode buildBinarySearchTree3(
            SimpleBinarySearchTree2 bst) {
        SimpleTreeNode tNode = null;

        tNode = bst.insert(tNode, 12);
        tNode = bst.insert(tNode, 9);
        tNode = bst.insert(tNode, 14);
        
        return tNode;
    }
    
    // in-order traversal.
    public static void inorder(SimpleTreeNode n) {
        if (n == null) { return; }
        
        inorder(n.left);
        System.out.println(n.data);
        inorder(n.right);
    }

    
    // Check if a tree contains another tree.
    // O(N + M) time, where N and M are the numbers of nodes in t1 and t2. 
    // O(N + M) space. 
    public static boolean containsTree(SimpleTreeNode t1, SimpleTreeNode t2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        
        // Add X at null node and do pre-order traversal
        // to get a string. 
        getOrderString(t1, sb1);
        getOrderString(t2, sb2);
        
        return sb1.indexOf(sb2.toString()) != -1;
    }
    // Pre-order traversal. Append X at null node. 
    // I think it doesn't matter which kind of traversals you use, 
    // pre-order, in-order, or post-order. 
    private static void getOrderString(SimpleTreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("X");
            return;
        }
        
        sb.append(node.data);
        getOrderString(node.left, sb);
        getOrderString(node.right, sb);
    }
    
    
    // Check if a tree contains another tree.
    // O(N + kM) average case runtime, where N and M are the numbers of nodes in t1 and t2, 
    // k is the number of occurrences of t2's root in t1. 
    // Worst case: O(MN)
    // O(log N + log M) space.
    public static boolean containsTree2(SimpleTreeNode t1, SimpleTreeNode t2) {
        if (t2 == null) { return true; }
        return subTree(t1, t2);
    }
    private static boolean subTree(SimpleTreeNode n1, SimpleTreeNode n2) {
        if (n1 == null) {
            return false;
        } else if (n1.data == n2.data && matchTree(n1, n2)) {
            // The point is how matchTree() is called. 
            // If the trees are not identical, it needs to get back to search. 
            return true;
        } 
        // Search in left and right subtree. 
        return subTree(n1.left, n2) || subTree(n1.right, n2);
    }
    public static boolean matchTree(SimpleTreeNode n1, SimpleTreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        } else if (n1 == null || n2 == null) {
            // One is null and the other is not null. 
            return false;
        } else if (n1.data != n2.data) {
            return false;
        } else {
            return matchTree(n1.left, n2.left) && matchTree(n1.right, n2.right);
        }
    }
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // This is incorrect!
    // // Check if a tree contains another tree.
    // // O(N + kM) average case runtime, where N and M are the numbers of nodes in t1 and t2, 
    // // k is the number of occurrences of t2's root in t1. 
    // // Worst case: O(MN)
    // // O(log N + log M) space.
    // public static boolean containsTreeBad(SimpleTreeNode t1, SimpleTreeNode t2) {
    //     if (t2 == null) { return true; }
        
    //     SimpleTreeNode n1 = findRootNodeOfTargetTreeBad(t1, t2);
    //     if (n1 != null) {
    //         return matchTreeBad(n1, t2);
    //     }
    //     // t2's root is not found in t1. 
    //     return false;
    // }
    // private static SimpleTreeNode findRootNodeOfTargetTreeBad(SimpleTreeNode n1, SimpleTreeNode n2) {
    //     if (n1 == null) {
    //         return null;
    //     }
    //     if (n1.data == n2.data) {
    //         return n1;
    //     } 
    //     // If there exists the node in the left subtree, then return the node. 
    //     // If there is no node that matches the node in left subtree, then 
    //     // search in right subtree. 
    //     SimpleTreeNode node = findRootNodeOfTargetTreeBad(n1.left, n2);
    //     if (node != null) {
    //         return node;
    //     }
    //     return findRootNodeOfTargetTreeBad(n1.right, n2);
    // }
    // public static boolean matchTreeBad(SimpleTreeNode n1, SimpleTreeNode n2) {
    //     if (n1 == null && n2 == null) {
    //         return true;
    //     } else if (n1 == null || n2 == null) {
    //         // One is null and the other is not null. 
    //         return false;
    //     } else if (n1.data != n2.data) {
    //         return false;
    //     } else {
    //         return matchTreeBad(n1.left, n2.left) && matchTreeBad(n1.right, n2.right);
    //     }
    // }
    
    
    
    
    
    
    
    
    
    


}























