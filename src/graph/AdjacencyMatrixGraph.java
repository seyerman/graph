package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyMatrixGraph<V> implements IGraph<V>{
	
	private static final int DEFAULT_CAPACITY = 10;
	private static final double GROWTH_FACTOR = 1.5;
	
	private int size; //logic size
	
	private boolean isDirected;
	
	private double[][] adjacencyMatrix;
	
	private Map<V,Integer> vertices;
	
	public AdjacencyMatrixGraph() {
		initialize(DEFAULT_CAPACITY);
	}

	public AdjacencyMatrixGraph(boolean id) {
		initialize(DEFAULT_CAPACITY);
		isDirected = id;
	}
	
	public AdjacencyMatrixGraph(int capacity) {
		initialize(capacity);
	}
	
	public AdjacencyMatrixGraph(boolean id, int capacity) {
		initialize(capacity);
		isDirected = id;
	}
	
	private final void initialize(int capacity) {
		isDirected = false;
		size = 0;
		adjacencyMatrix = new double[capacity][capacity];
		vertices = new HashMap<V, Integer>();
	}

	@Override
	public boolean addVertex(V v) {
		// TODO Auto-generated method stub
		return false;
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