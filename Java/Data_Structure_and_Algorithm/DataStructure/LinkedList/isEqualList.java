// Check if two list match.
// Author: CtCI 6th p.217 + kei
// Date  : October 19, 2016
public static boolean isEqualList(SLLNode<Integer> node1, 
                                  SLLNode<Integer> node2) {
    while (node1 != null && node2 != null) {
        if (node1.data != node2.data) {
            return false;
        }
        node1 = node1.next;
        node2 = node2.next;
    }

    // Note that not just return true here!
    return node1 == null && node2 == null;
}




































