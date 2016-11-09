// Create a graph. 
// Depth First Search (DFS) 
// Breadth First Search (BFS)
// Author: アルゴリズムを学ぼう p.94~ + kei
// Date  : September 23, 2016


package whiteboard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DFS_BFS {
	
	private static Vertex aVertex;
	private static Vertex eVertex;

	public static void main(String[] args) {
		
		Graph graph = createGraph();
		
		// DFS.
		boolean result = dfs(graph, aVertex, eVertex, new HashSet<Vertex>());
		//boolean result = dfsIter(graph, aVertex, null);
		
		// BFS.
		//boolean result = bfs(graph, aVertex, eVertex);
		
		
		System.out.println(result);

	}
	
	
	private static Graph createGraph() {
		Graph g = new Graph();
		
		LinkedList<Edge> aList = new LinkedList<Edge>();
		aVertex = new Vertex("a");
		Vertex bVertex = new Vertex("b");
		Vertex cVertex = new Vertex("c");
		Vertex dVertex = new Vertex("d");
		eVertex = new Vertex("e");
		Vertex fVertex = new Vertex("f");
		
		aList.add(new Edge(aVertex, bVertex));
		aList.add(new Edge(aVertex, cVertex));
		g.edgeMap.put(aVertex, aList);
		LinkedList<Edge> bList = new LinkedList<Edge>();
		bList.add(new Edge(bVertex, dVertex));
		bList.add(new Edge(bVertex, eVertex));
		g.edgeMap.put(bVertex, bList);
		LinkedList<Edge> cList = new LinkedList<Edge>();
		cList.add(new Edge(cVertex, bVertex));
		cList.add(new Edge(cVertex, eVertex));
		g.edgeMap.put(cVertex, cList);
		LinkedList<Edge> dList = new LinkedList<Edge>();
		dList.add(new Edge(dVertex, cVertex));
		dList.add(new Edge(dVertex, fVertex));
		g.edgeMap.put(dVertex, dList);
		LinkedList<Edge> eList = new LinkedList<Edge>();
		eList.add(new Edge(eVertex, fVertex));
		g.edgeMap.put(eVertex, eList);
		LinkedList<Edge> fList = new LinkedList<Edge>();
		fList.add(new Edge(fVertex, eVertex));
		g.edgeMap.put(fVertex, fList);
		
		// Display inside edgeMap.
		for (Map.Entry<Vertex, List<Edge>> entry : g.edgeMap.entrySet()) {
			System.out.println(entry.getKey());
			for (Edge edge : entry.getValue()) {
				System.out.println(edge.start + "-" + edge.end + " ");
			}
		}
		System.out.println();
		
		return g;
	}


	/**
	 * Depth First Search. Search for goal node traversing from node v.
	 * @param visited A set of vertices that have been visited before.
	 */
	public static boolean dfs(Graph g, Vertex v, Vertex goal, Set<Vertex> visited) {
		
	    if (v.equals(goal)) {
	        // Base Case 1
	        // goal found.
			System.out.println(v);
	        return true;
	    }

	    if (visited.contains(v)) {
	    	// Base Case 2
	        // Do not visit node that has been visited before.
	        return false;
	    }
	    visited.add(v);
	    
		System.out.println(v);

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

	/**
	 * Depth First Search iterative version. Search for goal node traversing from node v.
	 */
	public static boolean dfsIter(Graph g, Vertex start, Vertex goal) {
	    Set<Vertex> visited = new HashSet<Vertex>();
	    LinkedList<Vertex> stack = new LinkedList<Vertex>();

	    stack.push(start);

	    while (!stack.isEmpty()) {
	        Vertex v = stack.poll();

	        if (v.equals(goal)) {
	            // Found.
				System.out.println(v);
	            return true;
	        }

	        // This if statement may not be necessary
	        // if the println() below is not needed.
	        if (visited.contains(v)) {
	            continue;
	        }
	        visited.add(v);
	        
			System.out.println(v);

	        // Push all nodes that are connected to v.
	        List<Edge> edges = g.getAllEdgesAt(v);
	        if (edges != null) {
	        	for (Edge e : edges) {
		        	// Visit node that is not visited yet.
		        	// If it is visited, go to next.
		            if (!visited.contains(e.end)) {
			            stack.push(e.end);
		            }
		        	
//		            if (visited.contains(e.end)) {
//		                continue;
//		            }
//		            stack.push(e.end);
		        }
			}
	    } // end while (...)

	    // Not found.
	    return false;
	}

	/**
	 * Breadth First Search. Search for goal node traversing from node v.
	 */
	public static boolean bfs(Graph g, Vertex start, Vertex goal) {
	    Set<Vertex> visited = new HashSet<Vertex>();
	    LinkedList<Vertex> queue = new LinkedList<Vertex>();

	    queue.add(start);

	    while (!queue.isEmpty()) {
	        Vertex v = queue.poll();

	        if (v.equals(goal)) {
	            // Found.
				System.out.println(v);
	            return true;
	        }

	        // This if statement may not be necessary
	        // if the println() below is not needed.
	        if (visited.contains(v)) {
	            continue;
	        }
	        visited.add(v);
	        
			System.out.println(v);

	        // Add all nodes to queue that are connected to v.
	        List<Edge> edges = g.getAllEdgesAt(v);
	        if (edges != null) {
	        	for (Edge e : edges) {
		        	if (!visited.contains(e.end)) {
						queue.add(e.end);
					}
		        	
//		            if (visited.contains(e.end)) {
//		                continue;
//		            }
//		            queue.add(e.end);
		        }
			}
	    } // end while (...)

	    // Not found.
	    return false;
	}


	

}
