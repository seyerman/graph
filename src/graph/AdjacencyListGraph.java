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

	@Override
	public boolean removeVertex(V v) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
				return false;
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
