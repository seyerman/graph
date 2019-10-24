package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyListGraph<V> implements IGraph<V>{
	
	private Map<V,Integer> vertices;	
	private List<List<V>> adjacencyLists;
	private boolean isDirected;
	
	public AdjacencyListGraph() {
		initialize();
	}

	public AdjacencyListGraph(boolean id) {
		initialize();
		isDirected = id;
	}
	
	private final void initialize() {
		isDirected = false;
		adjacencyLists = new ArrayList<List<V>>();
		vertices = new HashMap<V, Integer>();
	}
	
	@Override
	public boolean addVertex(V v) {
		boolean added = false;
		if(!searchVertex(v)) {
			int key = adjacencyLists.size();
			vertices.put(v, key);
			added = true;
		}
		return added;
	}

	private boolean searchVertex(V v) {
		return vertices.containsValue(v);
	}

	@Override
	public void addEdge(V u, V v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEdge(V u, V v, double w) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean removeVertex(V v) {
		
		// first looks if the vertex exists
		if(vertices.containsKey(v)) {
			
			// remove the existing list which represents the adjacent vertices of the vertex to remove
			adjacencyLists.remove(vertices.get(v));
			
			// remove any existing connection to the vertex
			for(int i=0; i<adjacencyLists.size(); i++) {
				if(adjacencyLists.get(i).contains(v)) adjacencyLists.get(i).remove(i);
			}
			
			// removes the vertex form the map
			vertices.remove(v);
			
			return true;
			
		}else {
			return false;
		}
		
	}

	@Override
	public void removeEdge(V u, V v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<V> vertexAdjacent(V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean areConnected(V u, V v) {
		
		int uValor = vertices.get(u);
		int vValor = vertices.get(v);
		
//		return adjacencyLists.get(uValor).contains(v) || adjacencyLists.get(uValor).contains(v);
//		This return exists in case there is no need of being specific about the direction
		
		if(isDirected) {
			return adjacencyLists.get(uValor).contains(v);
			// this returns if u connected and directed to v
		}else {
			return adjacencyLists.get(uValor).contains(v) && adjacencyLists.get(vValor).contains(u);
			// in case the graph is not connected then both should be connected to each other
		}
		
	}

	@Override
	public double[][] weightMatrix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
