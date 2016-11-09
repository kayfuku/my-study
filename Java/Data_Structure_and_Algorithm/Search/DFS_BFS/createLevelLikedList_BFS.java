// Create a linked list at each level of a Binary Search Tree
// using modified BFS.
// Author: Cracking Coding Interview p.220 + kei.
// Date  : February 10, 2016


ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
    ArrayList<LinkedList<TreeNode>> lists = new ArrayList<>();

    LinkedList<TreeNode> currentListEachLevel = new LinkedList<>();

    if (root != null) {
        currentListEachLevel.add(root);
    }

    while (currentListEachLevel.size() > 0) {
        lists.add(currentListEachLevel);

        LinkedList<TreeNode> parentsList = currentListEachLevel;
        currentListEachLevel = new LinkedList<TreeNode>();

        for (TreeNode parentNode : parentsList) {
            // Visit child nodes and add them to the list.
            if (parentsNode.left != null) {
                currentListEachLevel.add(parentsNode.left);
            }
            if (parentsNode.right != null) {
                currentListEachLevel.add(parentsNode.right);
            }
        }
    }

    return lists;
}




