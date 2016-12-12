// Check if a binary tree is symmetric. 
// Author: EPI 10.2 p.140 + kei
// Date  : December 9, 2016

package whiteboard;



public class Lab_whiteboard {

    public static void main(String[] args) {

        
        // Build Binary Search Tree.
        //                    6
        //                  /   \
        //                 3     3
        //                  \   / 
        //                   4 4  
        SimpleTreeNode binaryTree = new SimpleTreeNode(6);
        binaryTree.left = new SimpleTreeNode(3);
        binaryTree.right = new SimpleTreeNode(3);
        binaryTree.left.right = new SimpleTreeNode(4);
        binaryTree.right.left = new SimpleTreeNode(4);
        
        boolean b = isSymmetricTree(binaryTree);
        System.out.println(b); // true 
        
        
        // Build Binary Search Tree.
        //                    6
        //                  /   \
        //                 3     3
        //                  \   / 
        //                   4 1  
        SimpleTreeNode binaryTree2 = new SimpleTreeNode(6);
        binaryTree2.left = new SimpleTreeNode(3);
        binaryTree2.right = new SimpleTreeNode(3);
        binaryTree2.left.right = new SimpleTreeNode(4);
        binaryTree2.right.left = new SimpleTreeNode(1);
        
        b = isSymmetricTree(binaryTree2);
        System.out.println(b); // false 
        
        
        // Build Binary Search Tree.
        //                    6
        //                  /   \
        //                 3     3
        //                  \     \
        //                   4     4
        SimpleTreeNode binaryTree3 = new SimpleTreeNode(6);
        binaryTree3.left = new SimpleTreeNode(3);
        binaryTree3.right = new SimpleTreeNode(3);
        binaryTree3.left.right = new SimpleTreeNode(4);
        binaryTree3.right.right = new SimpleTreeNode(4);
        
        b = isSymmetricTree(binaryTree3);
        System.out.println(b); // false 


        
        
        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    
    // Check if a binary tree is symmetric. 
    // Author: EPI 10.2 p.140 + kei
    // Date  : December 9, 2016
    public static boolean isSymmetricTree(SimpleTreeNode node) {
        if (node == null) {
            return true;
        }       
        
        return isSymmetricTree(node.left, node.right);
    }
    
    private static boolean isSymmetricTree(
            SimpleTreeNode subtree0, SimpleTreeNode subtree1) {
        if (subtree0 == null && subtree1 == null) {
            return true;
        } else if (subtree0 != null && subtree1 != null) {
            return (/* Check if the root item is equal. */
                    subtree0.data == subtree1.data 
                    /* Check if the children are symmetric. */
                    && isSymmetricTree(subtree0.left, subtree1.right) 
                    && isSymmetricTree(subtree0.right, subtree1.left));
        }
        
        // One subtree is empty, and the other is not. 
        return false;       
    }
    
    
    
    
    
    
    
    
    
    
    
}

























