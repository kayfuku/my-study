// Convert Binary Tree to Array. O(N)
// Author: PIE p.77 + kei
// Date  : November 1, 2016

package whiteboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Lab_whiteboard {

    public static void main(String[] args) {

        SimpleBinarySearchTree2 bst = new SimpleBinarySearchTree2();

        // Build Binary Search Tree.
        //                   6
        //                  /  \
        //                 3    12
        //                / \   / \
        //               1   4 9  14
        // Push data into BST.
        SimpleTreeNode bstNode = BinarySearchTree_usage2.buildBinarySearchTree(bst);
        
        // Build Binary Search Tree.
        //
        //                   6
        //                  /  
        //                 3    
        //                /
        //               1
        // Push data into BST in worst case. 
        SimpleTreeNode bstNodeW = BinarySearchTree_usage2.buildBinarySearchTreeW(bst);


        // Test convertTreeToArrayList().
//      ArrayList<SimpleTreeNode> nodeArrayList = convertTreeToArrayList(bstNode);
//      System.out.println(nodeArrayList.toString()); // [6, 3, 1, 4, 12, 9, 14]
//      ArrayList<SimpleTreeNode> nodeArrayListW = convertTreeToArrayList(bstNodeW);
//      System.out.println(nodeArrayListW.toString()); // [6, 3, 1]
        
        // Test convertTreeToArrayListBFS().
        ArrayList<SimpleTreeNode> nodeArrayList = convertTreeToArrayListBFS(bstNode);
        //System.out.println(nodeArrayList.toString()); // [6, 3, 12, 1, 4, 9, 14]
//      ArrayList<SimpleTreeNode> nodeArrayListW = convertTreeToArrayListBFS(bstNodeW);
//      //System.out.println(nodeArrayListW.toString()); // [6, 3, 1]
        
        // Test convertArrayListToBinaryTree().
//      SimpleTreeNode tNode = convertArrayListToBinaryTree(nodeArrayList);
//      bst.inorder(tNode); // 1 3 4 6 9 12 14 
        
        // Create Min-Heap. 
        // ★ You might be able to create heap in O(N) time. 
        //System.out.println(nodeArrayList.toString()); // [6, 3, 12, 1, 4, 9, 14]
        ArrayList<SimpleTreeNode> listAsc = new ArrayList<>(nodeArrayList);
        Collections.sort(listAsc); // O(N log N)
        //System.out.println(listAsc); // [1, 3, 4, 6, 9, 12, 14]
        // Create Min-Heap from sorted ArrayList<TreeNode>.
        SimpleTreeNode minHeap = convertArrayListToBinaryTree(listAsc);
        //                    1
        //                  /   \
        //                 3     4
        //                / \   /  \
        //               6   9 12  14
        //bst.inorder(minHeap); // 6 3 9 1 12 4 14 
        
        // Create Max-Heap. 
        // ★ You might be able to create heap in O(N) time. 
        System.out.println(nodeArrayList.toString()); // [6, 3, 12, 1, 4, 9, 14]
        ArrayList<SimpleTreeNode> listDesc = new ArrayList<>(nodeArrayList);
        Collections.sort(listDesc, Collections.reverseOrder()); // O(N log N)
        System.out.println(listDesc); // [14, 12, 9, 6, 4, 3, 1]
        // Create Max-Heap from sorted ArrayList<TreeNode>.
        SimpleTreeNode maxHeap = convertArrayListToBinaryTree(listDesc);
        //                    14
        //                  /    \
        //                 12     9
        //                /  \   /  \
        //               6    4 3    1
        bst.inorder(maxHeap); // 6 12 4 14 3 9 1 


        
        

        System.out.println("done.");
        return;
    } // end of main().
    
    
    // Convert Binary Tree to ArrayList<TreeNode>. O(N)
    // Author: PIE p.77 + kei
    // Date  : November 1, 2016
    public static ArrayList<SimpleTreeNode> convertTreeToArrayList(SimpleTreeNode tNode) {
        if (tNode == null) { return null; }
        ArrayList<SimpleTreeNode> nodeArrayList = new ArrayList<>();
        convertTreeToArrayList(tNode, nodeArrayList);
        return nodeArrayList;
    }
    private static void convertTreeToArrayList(SimpleTreeNode tNode, 
                                               ArrayList<SimpleTreeNode> nodeArrayList) {
        if (tNode == null) { return; }
        
        // Pre-order traversal. 
        if (nodeArrayList != null) {
            nodeArrayList.add(tNode);
        }
        convertTreeToArrayList(tNode.left, nodeArrayList);
        convertTreeToArrayList(tNode.right, nodeArrayList);
    }
    
    // Convert Binary Tree to ArrayList<TreeNode>. O(N)
    // Author: PIE p.77 + kei
    // Date  : November 1, 2016
    public static ArrayList<SimpleTreeNode> convertTreeToArrayListBFS(SimpleTreeNode tNode) {
        if (tNode == null) { return null; }
        ArrayList<SimpleTreeNode> nodeArrayList = new ArrayList<>();
        convertTreeToArrayListBFS(tNode, nodeArrayList);
        return nodeArrayList;
    }
    private static void convertTreeToArrayListBFS(SimpleTreeNode tNode, 
                                                  ArrayList<SimpleTreeNode> nodeArrayList) {
        // Level traversal. (BFS)
        LinkedList<SimpleTreeNode> queue = new LinkedList<>();
        
        queue.add(tNode);
        
        while (!queue.isEmpty()) {
            SimpleTreeNode node = queue.poll();
            
            nodeArrayList.add(node);
            
            if (node.left != null) { queue.add(node.left); }
            if (node.right != null) { queue.add(node.right); }   
        }

    }

    
    // Convert ArrayList<TreeNode> to Complete Binary Tree. O(N)
    // Author: PIE p.77 + kei
    // Date  : November 1, 2016
    public static SimpleTreeNode convertArrayListToBinaryTree(
                                          ArrayList<SimpleTreeNode> nodeArrayList) {
        if (nodeArrayList == null) { return null; }
        SimpleTreeNode tNode = nodeArrayList.get(0);
        convertArrayListToBinaryTree(nodeArrayList, tNode);
        return tNode;
    }
    private static void convertArrayListToBinaryTree(ArrayList<SimpleTreeNode> nodeArrayList, 
                                                     SimpleTreeNode tNode) {        
        int len = nodeArrayList.size();
        for (int i = 0; i < len; i++) { 
            int left = 2 * i + 1;
            int right = left + 1;
            // left <= len - 1 means 'if there exists left child'.
            nodeArrayList.get(i).setLeft((left <= len - 1) ? nodeArrayList.get(left) : null); 
            nodeArrayList.get(i).setRight((right <= len - 1) ? nodeArrayList.get(right) : null); 
        }       
    }
    
    
    
    
    
    
    
}

























