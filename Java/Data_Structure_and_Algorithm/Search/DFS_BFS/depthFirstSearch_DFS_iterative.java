// Depth First Search(DFS) iterative version.
// Author: アルゴリズムを学ぼう p.96 + kei
// Date  : September 22, 2016


/**
 * Depth First Search. Search for goal node traversing from node v.
 */
public boolean dfs(Graph g, Vertex start, Vertex goal) {
    Set<Vertex> visited = new HashSet<Vertex>();
    LinkedList<Vertex> stack = new LinkedList<Vertex>();

    stack.push(start);

    while (!stack.isEmpty()) {
        Vertex v = stack.poll();

        if (v.equals(goal)) {
            // Found.
            return true;
        }

        if (visited.contains(v)) {
            continue;
        }
        visited.add(v);

        // Push all nodes that are connected to v.
        List<Edge> edges = g.getAllEdgesAt(v);
        for (Edge e : edges) {
            if (visited.contains(e.end)) {
                continue;
            }
            stack.push(e.end);
        }
    }

    // Not found.
    return false;
}






























