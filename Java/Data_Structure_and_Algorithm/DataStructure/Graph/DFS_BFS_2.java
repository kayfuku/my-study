// Create a graph with Adjacency List data structure.
// Depth First Search (DFS) 
// Breadth First Search (BFS)
// A node equals to another node if its name is the same.
// Author: kei
// Date  : October 25, 31, 2016

package whiteboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DFS_BFS_2 {
	
	public static void main(String[] args) {
		
		GraphAdjList graph = createGraph();
		
		// DFS.
		//boolean result = dfs(graph, new Vertex2("a"), new Vertex2("f"), new HashSet<Vertex2>());
		boolean result = dfsIter(graph, new Vertex2("a"), new Vertex2("d"));
		
		// BFS.
		//boolean result = bfs(graph, new Vertex2("a"), new Vertex2("f"));
		
		
		System.out.println(result);

	}
	
	// Build a graph.
	//		    b  −−→  d
	//		  ↗︎↑↘   ↙︎  ↘︎
	//		a   ｜  ×    　 f
	//		  ↘︎｜↙︎   ↘︎  ⇄︎
	//		    c  −−→  e
	private static GraphAdjList createGraph() {
		GraphAdjList g = new GraphAdjList();
		
		Vertex2 aVertex = new Vertex2("a");
		Vertex2 bVertex = new Vertex2("b");
		Vertex2 cVertex = new Vertex2("c");
		Vertex2 dVertex = new Vertex2("d");
		Vertex2 eVertex = new Vertex2("e");
		Vertex2 fVertex = new Vertex2("f");
		
		ArrayList<Vertex2> aList = new ArrayList<Vertex2>();
		aList.add(bVertex);
		aList.add(cVertex);
		g.adjLists.put(aVertex, aList);
		ArrayList<Vertex2> bList = new ArrayList<Vertex2>();
		bList.add(dVertex);
		bList.add(eVertex);
		bList.add(fVertex);
		g.adjLists.put(bVertex, bList);
		ArrayList<Vertex2> cList = new ArrayList<Vertex2>();
		cList.add(bVertex);
		cList.add(eVertex);
		g.adjLists.put(cVertex, cList);
		ArrayList<Vertex2> dList = new ArrayList<Vertex2>();
		dList.add(cVertex);
		dList.add(fVertex);
		g.adjLists.put(dVertex, dList);
		ArrayList<Vertex2> eList = new ArrayList<Vertex2>();
		eList.add(fVertex);
		g.adjLists.put(eVertex, eList);
		ArrayList<Vertex2> fList = new ArrayList<Vertex2>();
		fList.add(eVertex);
		g.adjLists.put(fVertex, fList);


		
		// Display edges.
		for (Map.Entry<Vertex2, ArrayList<Vertex2>> entry : g.adjLists.entrySet()) {
			System.out.println(entry.getKey());
			for (Vertex2 v : entry.getValue()) {
				System.out.println(entry.getKey() + "-" + v + " ");
			}
		}
		System.out.println();
		
		return g;
	}


	/**
	 * Depth First Search. Search for goal node traversing from node v.
	 * @param visited A set of vertices that have been visited before.
	 */
	public static boolean dfs(GraphAdjList g, Vertex2 v, Vertex2 goal, Set<Vertex2> visited) {
		// This is not good place to put print method 
		// because the node visited is not avoided yet. 
		//System.out.println(v);
		
	    if (v.equals(goal)) {
	        // Base Case 1.
	        // goal found.
			System.out.println(v);
	        return true;
	    }

	    if (visited.contains(v)) {
	    	// Base Case 2.
	        // Do not visit node that has been already visited before.
	        return false;
	    }
	    visited.add(v);
	    
		System.out.println(v);

	    // DFS.
	    ArrayList<Vertex2> vertices = g.getAdjVertices(v);
	    if (vertices != null) {
	    	for (Vertex2 vertex : vertices) {
		        if (dfs(g, vertex, goal, visited)) {
		            return true;
		        }
		    }			
		}

	    // Not found.
	    return false;
	}

	/**
	 * Depth First Search iterative version. Search for goal node traversing from node v.
	 */
	public static boolean dfsIter(GraphAdjList g, Vertex2 start, Vertex2 goal) {
	    Set<Vertex2> visited = new HashSet<Vertex2>();
	    LinkedList<Vertex2> stack = new LinkedList<Vertex2>();

	    stack.push(start);

	    while (!stack.isEmpty()) {
	        Vertex2 v = stack.poll();
			System.out.println(v);

	        if (v.equals(goal)) {
	            // Found.
	            return true;
	        }

	        visited.add(v);
	        
	        // Push all nodes that are connected to v.
	        ArrayList<Vertex2> vertices = g.getAdjVertices(v);
	        if (vertices != null) {
	        	for (Vertex2 vertex : vertices) {
		        	// Visit node that has not visited yet.
		        	// If it has been already visited, go to next.
		            if (!visited.contains(vertex)) {
			            stack.push(vertex);
		            }
		        }
			}
	    } // end while (...)

	    // Not found.
	    return false;
	}

	/**
	 * Breadth First Search. Search for goal node traversing from node v.
	 */
	public static boolean bfs(GraphAdjList g, Vertex2 start, Vertex2 goal) {
	    Set<Vertex2> visited = new HashSet<Vertex2>();
	    LinkedList<Vertex2> queue = new LinkedList<Vertex2>();

	    queue.add(start);

	    while (!queue.isEmpty()) {
	        Vertex2 v = queue.poll();
	        System.out.println(v);

	        if (v.equals(goal)) {
	            // Found.
	            return true;
	        }

	        // Mark. 
	        visited.add(v);
	        
	        // Add all nodes to queue that are connected to v.
	        ArrayList<Vertex2> vertices = g.getAdjVertices(v);
	        if (vertices != null) {
	        	for (Vertex2 vertex : vertices) {
		        	if (!visited.contains(vertex)) {
						queue.add(vertex);
					}		        	
		        }
			}
	    } // end while (...)

	    // Not found.
	    return false;
	}


	

}


class GraphAdjList {
	//  adjLists
	//     v1 -> v3
	//     v2 -> v1 -> v3
	//     v3 -> v1
    public Map<Vertex2, ArrayList<Vertex2>> adjLists;

    public GraphAdjList() {
        this.adjLists = new HashMap<Vertex2, ArrayList<Vertex2>>();
    }

    public ArrayList<Vertex2> getAdjVertices(Vertex2 v) {
        return adjLists.get(v);
    }
}

class Vertex2 {
    String name;

    public Vertex2(String s) {
        this.name = s;
    }
    
    // Created automatically.
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

    // Created automatically.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex2 other = (Vertex2) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String toString() {
        return this.name;
    }
}
























