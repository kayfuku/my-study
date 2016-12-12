// Find the lowest common ancestor in Binary Tree.
// Assume that each node has a link to its parent.
// O(d0 + d1) time and space, where d0 is the distance from 
// the LCA to the first node, and d1 is the other. 
// Author: EPI 13.4 p.198 + kei
// Date  : December 1, 2016

package whiteboard;

import java.util.HashSet;
import java.util.Set;



public class Lab_whiteboard {

    public static void main(String[] args) {

        SimpleBinarySearchTree2 bst = new SimpleBinarySearchTree2();
        
        // Push data into BST with parent link.
        // Build Binary Search Tree.
        //                   6
        //                  /  \
        //                 3    12
        //                / \   / \
        //               1   4 9  14
        SimpleTreeNodeP bstNodeP = BinarySearchTree_usage2.buildBinarySearchTreeP(bst);

        SimpleTreeNodeP nodeP = findLowestCommonAncestor(
                BinarySearchTree_usage2.getAddressP(bst, 1), 
                BinarySearchTree_usage2.getAddressP(bst, 4));
        System.out.println(nodeP.data); // 3
        
        SimpleTreeNodeP nodeP2 = findLowestCommonAncestor(
                BinarySearchTree_usage2.getAddressP(bst, 1), 
                BinarySearchTree_usage2.getAddressP(bst, 9));
        System.out.println(nodeP2.data); // 6

        
        SimpleTreeNodeP nodeP3 = findLowestCommonAncestor2(
                BinarySearchTree_usage2.getAddressP(bst, 1), 
                BinarySearchTree_usage2.getAddressP(bst, 4));
        System.out.println(nodeP3.data); // 3
        
        SimpleTreeNodeP nodeP4 = findLowestCommonAncestor2(
                BinarySearchTree_usage2.getAddressP(bst, 1), 
                BinarySearchTree_usage2.getAddressP(bst, 9));
        System.out.println(nodeP4.data); // 6

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    

    
    // Find the first common ancestor in Binary Tree.
    // Assume that each node has a link to its parent.
    // O(d0 + d1) time and space, where d0 is the distance from 
    // the LCA to the first node, and d1 is the other. 
    // Author: EPI 13.4 p.198 + kei
    // Date  : December 1, 2016
    public static SimpleTreeNodeP findLowestCommonAncestor(
            SimpleTreeNodeP node0, SimpleTreeNodeP node1) {
        Set<SimpleTreeNodeP> set = new HashSet<>();

        while (node0 != null || node1 != null) {
            // Ascend tree in tandem from these two nodes. 
            if (node0 != null) {
                // You can split into contains() and add() like code below. 
                if (!set.add(node0)) {
                    return node0;
                }
                node0 = node0.parent;
            }
            if (node1 != null) {
                if (!set.add(node1)) {
                    return node1;
                }
                node1 = node1.parent;
            }
        } 

        throw new IllegalArgumentException(
            "node0 and node1 are not in the same tree");
    }

    // Author: EPI 13.4 p.198 + kei
    // Date  : December 2, 2016
    public static SimpleTreeNodeP findLowestCommonAncestor2(
            SimpleTreeNodeP node0, SimpleTreeNodeP node1) {
        Set<SimpleTreeNodeP> set = new HashSet<>();

        while (node0 != null || node1 != null) {
            // Ascend tree in tandem from these two nodes. 
            if (node0 != null) {
                // You can put together contains() and add() like code above. 
                if (set.contains(node0)) {
                    return node0;
                }
                set.add(node0);
                node0 = node0.parent;
            }
            if (node1 != null) {
                if (set.contains(node1)) {
                    return node1;
                }
                set.add(node1);
                node1 = node1.parent;
            }
        } 

        throw new IllegalArgumentException(
            "node0 and node1 are not in the same tree");

    }

    
    
    
    
    
    
    
    
}

























