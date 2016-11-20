// Get the size of the list.
// Author: CtCI 6th p.220 + kei
// Date  : October 19, 2016
public static int getSizeOfList(SLLNode<Integer> node) {
    if (node == null) {
        return -1;
    }
    int size = 0;
    while (node != null) {
        size++;
        node = node.next;
    }
    return size;        
}














