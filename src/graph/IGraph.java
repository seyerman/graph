package graph;

import java.util.List;

public interface IGraph<V> {
	public boolean addVertex(V v);
	public void addEdge(V u, V v);
	public void addEdge(V u, V v, double w);
	public boolean removeVertex(V v);
	public void removeEdge(V u, V v);
	public List<V> vertexAdjacent(V v);
	public boolean areConnected(V u, V v);
	public double[][] weightMatrix();
	public boolean isDirected();
}
