// Create a linked list at each level of a Binary Search Tree
// using DFS recursive version.
// Author: Cracking Coding Interview p.219 + kei.
// Date  : February 10, 2016


void createLevelLinkedList(
    TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level) {

    // Base case.
    if (root == null) {
        return;
    }

    // Prepare a linked list at the level.
    LinkedList<TreeNode> listEachLevel = null;
    if (lists.size() == level) {
        listEachLevel = new LinkedList<TreeNode>();
        lists.add(listEachLevel);        
    } else {
        listEachLevel = lists.get(level);
    }

    // Add the node.
    listEachLevel.add(root);

    // Part of DFS recursive version in a tree.
    createLevelLinkedList(root.left, lists, level + 1);
    createLevelLinkedList(root.right, lists, level + 1);
}


ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
    ArrayList<LinkedList<TreeNode>> lists = new ArrayList<>();
    createLevelLinkedList(root, lists, 0);

    return lists;
}

