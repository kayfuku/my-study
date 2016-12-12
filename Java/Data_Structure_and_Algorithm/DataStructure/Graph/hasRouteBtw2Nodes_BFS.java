// Find whether there is a route between 2 nodes in a directed graph
// using BFS. 
// Author: kei
// Date  : December 8, 2016

package whiteboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;



public class Lab_whiteboard {

    public static void main(String[] args) {

        
        // Build a graph.
        //          b  −−→  d
        //        ↗︎↑↘   ↙︎  ↘︎
        //      a 　｜ 　×    　 f
        //        ↘︎｜↙︎   ↘︎  /︎
        //          c  −−→  e
        GraphAdjList graph = DFS_BFS_2.createGraph();
        Vertex2 start = new Vertex2("a");
        Vertex2 goal = new Vertex2("f");
        boolean b = hasRouteBtw(graph, start, goal);
        System.out.println(b); // true 
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    

    public static boolean hasRouteBtw(GraphAdjList g, Vertex2 start, Vertex2 goal) {
        LinkedList<Vertex2> queue = new LinkedList<>();
        HashSet<Vertex2> visited = new HashSet<>();
        
        queue.add(start);
        while (!queue.isEmpty()) {
            Vertex2 currNode = queue.poll();
            if (currNode.equals(goal)) {
                return true;
            }
            visited.add(currNode);
            
            ArrayList<Vertex2> adjNodes = g.getAdjVertices(currNode);
            if (adjNodes != null) {
                for (Vertex2 node : adjNodes) {
                    if (!visited.contains(node)) {
                        queue.add(node);
                    }
                }
            }
        }
        
        return false;
    }
    
}



// Find whether there is a route between 2 nodes in a directed graph 
// using BFS.
// Author: CtCI 4.1 p.241 + kei.
// Date  : February 8, December 8, 2016

public enum State {
    Unvisited, Visited, Visiting;
}

public static boolean hasRouteBtw2Nodes(Graph g, Node start, Node end) {
    if (start.equals(end)) {
        return true;
    }

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





































