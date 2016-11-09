// Depth First Search (DFS) recursive version.
// Author: アルゴリズムを学ぼう p.94 + kei
// Date  : September 22, 2016

/** 
 * Find goal node.
 * @param g An instance of Graph class.
 * @return true if goal is found.
 */
public boolean find(Graph g, Vertex start, Vertex goal) {

    return dfs(g, start, goal, new HashSet<Vertex>());

}

/**
 * Depth First Search. Search for goal node traversing from node v.
 * @param visited A set of vertices that have been visited before.
 */
public boolean dfs(Graph g, Vertex v, Vertex goal, Set<Vertex> visited) {

    if (v.equals(goal)) {
        // Base Case 1
        // goal found.
        return true;
    }

    if (visited.contains(v)) {
        // Do not visit node that has been visited before.
        return false;
    }
    visited.add(v);

    // DFS.
    List<Edge> edges = g.getAllEdgesAt(v);
    if (edges != null) {
        for (Edge edge : edges) {
            if (dfs(g, edge.end, goal, visited)) {
                return true;
            }
        }
    }

    // Not found.
    return false;
}






























