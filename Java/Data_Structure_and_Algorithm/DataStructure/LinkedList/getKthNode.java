// Get the k-th node.
// Author: CtCI 6th p.222 + kei
// Date  : October 23, 2016
public static SLLNode<Integer> getKthNode(SLLNode<Integer> node, int k) {
    // You can use formal argument because in Java it's always 'pass by value'.
    while (k > 0 && node != null) {
        node = node.next;
        k--;
    }
    // Note that if k > len - 1, then return null.
    return node;
}










