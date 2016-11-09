// Breadth First Search(BFS).
// Author: アルゴリズムを学ぼう p.97 + kei
// Date  : September 22, 2016


/**
 * Breadth First Search. Search for goal node traversing from node v.
 */
public boolean bfs(Graph g, Vertex start, Vertex goal) {
    Set<Vertex> visited = new HashSet<Vertex>();
    LinkedList<Vertex> queue = new LinkedList<Vertex>();

    queue.add(start);

    while (!queue.isEmpty()) {
        Vertex v = queue.poll();

        if (v.equals(goal)) {
            // Found.
            return true;
        }

        if (visited.contains(v)) {
            continue;
        }
        visited.add(v);

        // Add all nodes to queue that are connected to v.
        List<Edge> edges = g.getAllEdgesAt(v);
        for (Edge e : edges) {
            if (visited.contains(e.end)) {
                continue;
            }
            queue.add(e.end);
        }
    }

    // Not found.
    return false;
}






























