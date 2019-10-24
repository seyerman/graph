package graph;
import java.util.*;

/**
 * This class is meant to execute different algorithms on graphs. 
 * @version 1.0 - 10/2019
 */
public class GraphAlgorithms{
	
	
	
	public static <V> List<V> bfs(IGraph<V> g, V v){
		return null; //TODO Create method
	}
	
	public static <V> List<V> dfs (IGraph<V> g, V v){
		return null; //TODO Create method
	}

	/**
	 * This method will traverse the graph given a data structure. This will perform  BFS or DFS, given the case.
	 * @param g The graph to be traversed.
	 * @param v The vertex from which the traversal will begin.
	 * @param ds The data structure to be used in this traversal. Either a Stack for a DFS or a Queue for BFS. Must be empty.
	 * @return A List with the resulting traversal performed on the given graph from the given vertex.
	 */
	private static <V> List<V> traversal(IGraph<V> g, V v, List<V> ds){

		V vertex = v;
		while(!ds.isEmpty()) {
			
		}
		return null; //TODO Create method
	}
	
	private static <V> Map<V, Double> dijkstra(IGraph<V> g, V v){
		return null; //TODO Create method
	}
}
