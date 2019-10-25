package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;

/**
 * An Object that models a graph using an Adjacency Matrix.
 *
 * @param <V> the type of vertex in the graph
 * @author AED Class # 003 // 2019
 * @version 1.0 - 10/2019
 */
public class AdjacencyMatrixGraph<V> implements IGraph<V> {

    /**
     * The length of the matrix when using the default Constructor.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The rate at which the matrix's length increases as it becomes full.
     */
    private static final double GROWTH_FACTOR = 1.5;

    /**
     * The last index in the matrix at which a vertex exists.
     */
    private int size; //logic size

    /**
     * Indicates whether the graph represented by the matrix is directed.
     */
    private boolean isDirected;

    /**
     * The matrix itself.
     */
    private double[][] adjacencyMatrix;
    private double[][] adjacencyMatrixWeight;

    /**
     * A Map that accesses any vertex in the graph through its index in the matrix.
     */
    private Map<Integer, V> vertices;

    /**
     * A Map that uses any vertex as a key to access its corresponding index in the matrix.
     */
    private Map<V, Integer> verticesIndices;

    /**
     * A Set that contains ordered non-duplicate Integers of empty row/columns in the matrix whose values are lesser
     * than the logical size.
     */
    private NavigableSet<Integer> emptySlots = new TreeSet<>();

    /**
     * Constructs a new, empty matrix of double values of default length, along with two Map objects that interconnect
     * vertices to their indices in the matrix and indices in the matrix to their vertices.
     */
    public AdjacencyMatrixGraph() {
        initialize(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new, empty matrix of double values of default length, along with two Map objects that interconnect
     * vertices to their indices in the matrix and indices in the matrix to their vertices. The graph represented by the
     * matrix is directed if id is true.
     *
     * @param id a boolean that indicates the graph is directed when true.
     */
    public AdjacencyMatrixGraph(boolean id) {
        initialize(DEFAULT_CAPACITY);
        isDirected = id;
    }

    /**
     * Constructs a new, empty matrix of double values of default length, along with two Map objects that interconnect
     * vertices to their indices in the matrix and indices in the matrix to their vertices.
     *
     * @param capacity the initial size of the adjacency matrix
     */
    public AdjacencyMatrixGraph(int capacity) {
        initialize(capacity);
    }

    /**
     * Constructs a new, empty matrix of double values of default length, along with two Map objects that interconnect
     * vertices to their indices in the matrix and indices in the matrix to their vertices. The graph represented by the
     * matrix is directed if id is true.
     *
     * @param id a boolean that indicates the graph is directed when true.
     * @param capacity the initial size of the adjacency matrix
     */
    public AdjacencyMatrixGraph(boolean id, int capacity) {
        initialize(capacity);
        isDirected = id;
    }

    private void initialize(int capacity) {
        isDirected = false;
        size = 0;
        adjacencyMatrix = new double[capacity][capacity];
        vertices = new HashMap<>();
        verticesIndices = new HashMap<>();
    }

    @Override
    public boolean addVertex(V v) {
        boolean added = false;
        Integer index;
        if (verticesIndices.get(v) != null) {
            if (emptySlots.isEmpty()) {//No reusable rows/columns in the matrix
                if (size == adjacencyMatrix.length) {//Needs to initialize a bigger array
                    double[][] placeHolder = adjacencyMatrix;
                    int newLength = (int) (adjacencyMatrix.length * GROWTH_FACTOR);
                    adjacencyMatrix = new double[newLength][newLength];
                    for (int i = 0; i < placeHolder.length; i++) {
                        System.arraycopy(placeHolder[i], 0, adjacencyMatrix[i], 0, placeHolder.length);
                    }
                }
                size++;
                index = size;
            } else {
                index = emptySlots.pollFirst();//TODO: May assign null?
            }
            vertices.put(index, v);
            verticesIndices.put(v, index);
            added = true;
        }
        return added;
    }

    @Override
    public void addEdge(V u, V v) {
    	int x = verticesIndices.get(u);
		int y = verticesIndices.get(v);
    	if(!isDirected) {
			adjacencyMatrix[x][y] = 1;
			adjacencyMatrix[y][x] = 1;
		}else {
			adjacencyMatrix[x][y] = 1;
		}
    }

    @Override
    public void addEdge(V u, V v, double w) {
    	int x = verticesIndices.get(u);
		int y = verticesIndices.get(v);
    	if(!isDirected) {
			adjacencyMatrix[x][y] = 1;
			adjacencyMatrix[y][x] = 1;
			adjacencyMatrixWeight[x][y] = w;
			adjacencyMatrixWeight[y][x] = w;
		}else {
			adjacencyMatrix[x][y] = 1;
			adjacencyMatrixWeight[x][y] = w;
		}
    }

    @Override
    public boolean removeVertex(V v) {
        boolean removed = false;
        Integer position = verticesIndices.get(v);
        if (position != null) {
            vertices.remove(position);
            verticesIndices.remove(v);
            emptySlots.add(position);
            for (int i = 0; i < size; i++) {
                adjacencyMatrix[position][i] = 0;
            }
            for (int i = 0; i < size; i++) {
                adjacencyMatrix[i][position] = 0;
            }
            removed = true;
        }
        return removed;
    }

    @Override
    public void removeEdge(V u, V v) {
    	if(!isDirected) {
			adjacencyMatrix[(int) u][(int) v] = 0;
			adjacencyMatrix[(int) v][(int) u] = 0;
		}else {
			adjacencyMatrix[(int) u][(int) v] = 0;
		}
    }

    @Override
    public List<V> vertexAdjacent(V v) {
        Integer position = verticesIndices.get(v);
        List<V> adjacentVertices;
        if (position != null) {
            Set<Integer> adjacentVerticesPositions = new HashSet<>();
            for (int i = 0; i < size; i++) {
                if (adjacencyMatrix[position][i] != 0) {//Vertex at position i is adjacent
                    adjacentVerticesPositions.add(i);
                }
            }
            if (isDirected) {//Only necessary to check if graph is directed
                for (int i = 0; i < size; i++) {
                    if (adjacencyMatrix[i][position] != 0) {//Vertex at position i is adjacent
                        adjacentVerticesPositions.add(i);
                    }
                }
            }
            adjacentVertices = new ArrayList<>();
            for (Integer key : adjacentVerticesPositions
            ) {
                adjacentVertices.add(vertices.get(key));
            }
        } else {
            adjacentVertices = null;
        }
        return adjacentVertices;
    }

    @Override
    public boolean areConnected(V u, V v) {
        int uValor = verticesIndices.get(u);
        int vValor = verticesIndices.get(v);

//		return adjacencyMatrix[uValor][vValor] == 1 && adjacencyMatrix[vValor][uValor] == 1;
//		This return exists in case there is no need of being specific about the direction

        if (isDirected) {
            return adjacencyMatrix[uValor][vValor] == 1;
            // this returns if u connected and directed to v
        } else {
            return adjacencyMatrix[uValor][vValor] == 1 && adjacencyMatrix[vValor][uValor] == 1;
            // in case the graph is not connected then both should be connected to each other
        }
    }

    @Override
    public double[][] weightMatrix() {
        return adjacencyMatrixWeight;
    }

    @Override
    public boolean isDirected() {
        return isDirected;
    }

	@Override
	public int getIndex(V u) {
		return verticesIndices.get(u);
	}
}