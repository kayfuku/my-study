// Graph.
// Author: アルゴリズムを学ぼう p.91 + kei
// Date  : September 22, 2016

package whiteboard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    public Set<Vertex> vertexSet;
    public Map<Vertex, List<Edge>> edgeMap;

    public Graph() {
        this.vertexSet = new HashSet<Vertex>();
        this.edgeMap = new HashMap<Vertex, List<Edge>>();
    }

    public List<Edge> getAllEdgesAt(Vertex v) {
        return edgeMap.get(v);
    }


}

class Vertex {
    String name;

    public Vertex(String s) {
        this.name = s;
    }
    
    public String toString() {
        return this.name;
    }
}

class Edge {
    public Vertex start;
    public Vertex end;
    
    public Edge(Vertex s, Vertex e) {
    	this.start = s;
    	this.end = e;
	}
    
    public String toString() {
    	return start + "-" + end;
    }
    
}




























