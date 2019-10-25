package graph;
import java.util.*;

import collections.ICollection;

/**
 * This class is meant to execute different algorithms on graphs. 
 * @author AED Class # 003 // 2019
 * @version 1.0 - 10/2019
 */
public class GraphAlgorithms{	
	
	/**
	 * Performs a breadth-first search to traverse a graph
	 * @param <V> Abstract data type that represent a vertex within the graph
	 * @param g Graph which is going to be traversed
	 * @param v Vertex where it's going to start the BFS
	 * @return A list with a resultant order due to a BFS
	 */
	public static <V> List<V> bfs(IGraph<V> g, V v){
		return null; //TODO Create method
	}
	
	/**
	 * Performs a depth-first search to traverse a graph
	 * @param <V> Abstract data type that represent a vertex within the graph
	 * @param g Graph which is going to be traversed
	 * @param v Vertex where it's going to start the DFS
	 * @return A list with a resultant order due to a DFS
	 */
	public static <V> List<V> dfs (IGraph<V> g, V v){
		return null; //TODO Create method
	}

	/**
	 * This method will traverse the graph given a data structure. This will perform  BFS or DFS, given the case.
	 * @param <V> Abstract data type that represent a vertex within the graph
	 * @param g The graph to be traversed.
	 * @param v The vertex from which the traversal will begin.
	 * @param ds The data structure to be used in this traversal. Either a Stack for a DFS or a Queue for BFS.
	 * <pre> ds Must be empty.
	 * @return A List with the resulting traversal performed on the given graph from the given vertex.
	 */
	private static <V> List<V> traversal(IGraph<V> g, V v, ICollection<V> ds){
		List<V> ret = new ArrayList<>();
		//Invariant: Each algorithm adds the given element first. 
		V vertex = v;
		ds.add(vertex);
		//Invariant: While the traversal occurs, the given DS to be used will have, at least, one element.
		while(!ds.isEmpty()) {
			 //Invariant: Element added is always retired from the DS
			vertex = ds.poll();
			ret.add(vertex);
			
		}
		return null;
	}
	
	/**
	 * An Algorithm based on Dijkstra's approach to find the shortest path from the source vertex to all other vertices in the graph.
	 * @param <V> Abstract data type that represent a vertex within the graph
	 * @param g The graph to be traversed.
	 * @param v The vertex where Dijkstra is going to be used.
	 * @return A  map with the shortest paths founded
	 */
	private static <V> Map<V, Double> dijkstra(IGraph<V> g, V v){
		return null; //TODO Create method
	}
}
