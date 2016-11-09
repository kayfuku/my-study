// Find whether there is a route between 2 nodes in a directed graph, 
// using BFS.
// Author: Cracking Coding Interview p.217 + kei.
// Date  : February 8, 2016

public enum State {
    Unvisited, Visited, Visiting;
}

public static boolean hasRouteBtw2Nodes(Graph g, Node start, Node end) {
    LinkedList<Node> queue = new LinkedList<Node>();

    for (Node u : g.getNodes()) {
        u.state = State.Unvisited;
    }
    
    // BFS.
    start.state = State.Visiting;
    queue.add(start);
    Node u;
    while (!q.isEmpty()) {
        // Dequeue.
        u = q.removeFirst();
        if (u != null) {
            for (Node v : u.getAdjacent()) {
                if (v.state == State.Unvisited) {
                    if (v == end) {
                        return true;
                    } else {
                        v.state = State.Visiting;
                        queue.add(v);
                    }
                }
            }
            u.state = State.Visited;
        }
    }

    return false;
}




