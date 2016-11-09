// Graph with adjacency list. 
// Author: kei
// Date  : October 25, 2016

package whiteboard;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

class GraphAdjList {
    //  HashMap  ArrayList
    //      v1 |-> v3
    //      v2 |-> v1 -> v3
    //      v3 |-> v1
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


























