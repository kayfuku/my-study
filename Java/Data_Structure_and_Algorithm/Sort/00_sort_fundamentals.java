// Sort fundamentals.


// Sort objects in an array using Comparator. 
// Author: PIE p.76 + kei.
// Date  : November 1, 2016
Node[] nodesArray = new Node[10];

Arrays.sort(nodesArray, new Comparator<Node>() {
        @Override
        public int compare(Node node1, Node node2) {
            int n1 = node1.data, n2 = node2.data;
            return (n1 < n2 ? -1 : (n1 == n2 ? 0 : 1));
        }
    });


// Basics. 
Arrays.sort(array);                                   //ソート
Arrays.sort(array, Comparator.reverseOrder());        //降順になる
Arrays.sort(array, (x,y)->Integer.compare(y, x));     //コンパレータを渡してソート
Collections.sort(list);                               //ソート
Collections.sort(list, Collections.reverseOrder());   //逆順にソート
Collections.sort(list, (x,y)->Integer.compare(y, x)); //コンパレータを渡してソート
list.sort(Collections.reverseOrder());                //逆順にソート















