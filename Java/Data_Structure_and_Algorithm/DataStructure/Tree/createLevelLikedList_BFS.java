// Create a linked list at each level of a Binary Search Tree
// using modified BFS.
// Author: CtCI 4.3 p.243 + kei.
// Date  : February 10, December 8, 2016


ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
    ArrayList<LinkedList<TreeNode>> lists = new ArrayList<>();

    LinkedList<TreeNode> currentListEachLevel = new LinkedList<>();

    if (root != null) {
        currentListEachLevel.add(root);
    }

    while (!currentListEachLevel.isEmpty()) {
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

































