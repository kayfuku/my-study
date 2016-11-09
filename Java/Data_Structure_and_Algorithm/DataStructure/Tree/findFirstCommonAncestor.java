// Find the first common ancestor in Binary Tree.
// Assume that each node has a link to its parent.
// Author: CtCI 6th 4.8 p.258 + kei
// Date  : October 23, 2016

package whiteboard;

public class Lab_whiteboard {

    public static void main(String[] args) {

        SimpleBinarySearchTree2 bst = new SimpleBinarySearchTree2();

        // Push data into BST.
        SimpleTreeNode bstNode = buildBinarySearchTree(bst);
        
        // Push data into BST with parent link.
        SimpleTreeNodeP bstNodeP = buildBinarySearchTreeP(bst);

        
        // Test findFirstCommonAncestorP().
//      SimpleTreeNodeP fca = findFirstCommonAncestorP(
//              bstNodeP, getAddressP(bst, 4), getAddressP(bst, 12));
//      System.out.println(fca.data); // 6 
//      fca = findFirstCommonAncestorP(
//              bstNodeP, getAddressP(bst, 9), getAddressP(bst, 14));
//      System.out.println(fca.data); // 12 


        
        // Test findFirstCommonAncestor().
        //bst.inorder(bstNode); // 1 3 4 6 9 12 14
//        SimpleTreeNode fca = findFirstCommonAncestor(
//        bstNode, getAddress(bst, 4), getAddress(bst, 12));
//        //System.out.println(fca.data); // 6 
//        SimpleTreeNode fca = findFirstCommonAncestor(
//        bstNode, getAddress(bst, 9), getAddress(bst, 14));
//        //System.out.println(fca.data); // 12 
//        SimpleTreeNode fca = findFirstCommonAncestorInBST(
//        bstNode, getAddress(bst, 9), getAddress(bst, 14));
//        System.out.println(fca.data); // 12
        SimpleTreeNode fca = findFirstCommonAncestorInBST(
        bstNode, getAddress(bst, 15), getAddress(bst, 14));
        System.out.println(fca); // null

        
    
        
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
    public static SimpleTreeNode buildBinarySearchTree(
            SimpleBinarySearchTree2 bst) {
        SimpleTreeNode tNode = null;

        tNode = bst.insert(tNode, 6);
        tNode = bst.insert(tNode, 3);
        tNode = bst.insert(tNode, 12);
        tNode = bst.insert(tNode, 1);
        tNode = bst.insert(tNode, 9);
        tNode = bst.insert(tNode, 4);
        tNode = bst.insert(tNode, 14);
        //tNode = bst.insert(tNode, 25);
        //tNode = bst.insert(tNode, 39); // With all above, not balanced.
        
        return tNode;
    }
    
    // Build Binary Search Tree such that each node has a link to its parent.
    // Author: kei
    // Date  : October 23, 2016
    public static SimpleTreeNodeP buildBinarySearchTreeP(
            SimpleBinarySearchTree2 bst) {
        SimpleTreeNodeP tNodeP = null;

        tNodeP = bst.insertP(tNodeP, 6);
        tNodeP = bst.insertP(tNodeP, 3);
        tNodeP = bst.insertP(tNodeP, 12);
        tNodeP = bst.insertP(tNodeP, 1);
        tNodeP = bst.insertP(tNodeP, 9);
        tNodeP = bst.insertP(tNodeP, 4);
        tNodeP = bst.insertP(tNodeP, 14);
        //tNodeP = bst.insertP(tNodeP, 25);
        //tNodeP = bst.insertP(tNodeP, 39); // With all above, not balanced.
        
        return tNodeP;
    }
    
    // Get node address from node data.
    // Author: kei
    // Date  : October 23, 2016
    public static SimpleTreeNodeP getAddressP(SimpleBinarySearchTree2 bst, int key) {
        return bst.mapP.get(key);
    }
    public static SimpleTreeNode getAddress(SimpleBinarySearchTree2 bst, int key) {
        return bst.map.get(key);
    }


    public static SimpleTreeNodeP findFirstCommonAncestorP(
            SimpleTreeNodeP root, SimpleTreeNodeP p, SimpleTreeNodeP q) {
        // Check if either node is not in the tree, or if one covers the other. 
        if (!coverP(root, p) || !coverP(root, q)) {
            return null;
        } else if (coverP(p, q)) {
            return p;
        } else if (coverP(q, p)) {
            return q;
        }
        
        // Traverse upwards until you find a node that covers q. 
        SimpleTreeNodeP sibling = getSibling(p);
        while (!coverP(sibling, q)) {
            p = p.parent;
            sibling = getSibling(p);            
        }

        return p.parent;
    }
    // Check if root tree has node p.
    public static boolean coverP(SimpleTreeNodeP root, SimpleTreeNodeP p) {
        if (root == null) { return false; }
        if (root == p) { return true; }     
        return coverP(root.left, p) || coverP(root.right, p);
    }
    // Get Sibling.
    public static SimpleTreeNodeP getSibling(SimpleTreeNodeP tNode) {
        if (tNode == null || tNode.parent == null) {
            return null;
        }
        
        SimpleTreeNodeP parent = tNode.parent;
        return parent.left == tNode ? parent.right : parent.left;
    }

    // Find first common ancestor in BT.
    // Assume that each node does not have a link to its parent.
    // Author: CtCI 6th 4.8 p.259 + kei
    // Date  : October 23, 2016
    public static SimpleTreeNode findFirstCommonAncestor(
        SimpleTreeNode root, SimpleTreeNode p, SimpleTreeNode q) {
        if (!covers(root, p) || !covers(root, q)) {
            return null;
        }
        return ancestorHelper(root, p, q);
    }
    private static SimpleTreeNode ancestorHelper(
        SimpleTreeNode root, SimpleTreeNode p, SimpleTreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        // p and q are not on the same side. 
        // p and q are on different side.
        boolean pIsOnLeft = covers(root.left, p);
        boolean qIsOnLeft = covers(root.left, q);
        if (pIsOnLeft != qIsOnLeft) {
            return root;
        }

        // p and q are on the same side.
        // Check down to the subtree.
        SimpleTreeNode subtreeRoot = pIsOnLeft ? root.left : root.right;
        return ancestorHelper(subtreeRoot, p, q);
    }
    // Check if root tree has node p.
    public static boolean covers(SimpleTreeNode root, SimpleTreeNode p) {
        if (root == null) {
            return false;
        }
        if (root == p) {
            return true;
        }
        return covers(root.left, p) || covers(root.right, p);
    }
    
    // Find first common ancestor in BST. O(log N)
    // Assume that each node does not have a link to its parent.
    // Author: PIE p.74 + kei
    // Date  : October 31, 2016
    public static SimpleTreeNode findFirstCommonAncestorInBST(
            SimpleTreeNode node, SimpleTreeNode p, SimpleTreeNode q) {
        if (node == null || p == null || q == null) {
            return null;
        }
        
        return findFirstCommonAncestorInBST(node, p.data, q.data);
    }
    private static SimpleTreeNode findFirstCommonAncestorInBST(
            SimpleTreeNode node, int p, int q) {
        
        while (node != null) {
            int currData = node.data;
            
            if (p < currData && q < currData) {
                node = node.left;
            } else if (p > currData && q > currData) {
                node = node.right;
            } else {
                return node;
            }           
        }
    
        return null;
    }




}































