package graph;

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

    /**
     * The associated matrix containing the weight of all edged between nodes in the graph.
     */
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
     * @param id       a boolean that indicates the graph is directed when true.
     * @param capacity the initial size of the adjacency matrix
     */
    public AdjacencyMatrixGraph(boolean id, int capacity) {
        initialize(capacity);
        isDirected = id;
    }

    /**
     * Auxiliary method used by the Constructor to set values to the class' fields. Creates the adjacency matrix.
     *
     * @param capacity the initial size of the adjacency matrix
     */
    private void initialize(int capacity) {
        isDirected = false;
        size = 0;
        adjacencyMatrix = new double[capacity][capacity];
        vertices = new HashMap<>();
        verticesIndices = new HashMap<>();
    }

    /**
     * Adds the given vertex to the graph.
     *
     * @param v The new vertex to be added
     * @return true if the vertex did not already exist in the graph
     */
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
                index = size - 1;
            } else {
                index = emptySlots.pollFirst();//TODO: May assign null?
            }
            vertices.put(index, v);
            verticesIndices.put(v, index);
            added = true;
        }
        return added;
    }

    /**
     * Adds a directed edge from vertex 'u' to vertex 'v' if the graph is directed. Otherwise, adds an edge between
     * vertices 'u' and 'v'.
     *
     * @param u a vertex within the graph
     * @param v a vertex within the graph
     */
    @Override
    public void addEdge(V u, V v) {
        Integer x = verticesIndices.get(u);
        Integer y = verticesIndices.get(v);
        if (x != null && y != null) {
            if (!isDirected) {
                adjacencyMatrix[x][y] = 1;
                adjacencyMatrix[y][x] = 1;
            } else {
                adjacencyMatrix[x][y] = 1;
            }
        }else{}//TODO: May need to change return type to boolean for when the edge couldn't be added
    }

    /**
     * Adds a directed edge from vertex 'u' to vertex 'v' if isDirected with weight 'w'. Adds an edge between vertices
     * 'u' and 'v' of weight 'w' otherwise.
     *
     * @param u a vertex within the graph
     * @param v a vertex within the graph
     * @param w is the weight of the edge between 'u' and 'v'
     */
    @Override
    public void addEdge(V u, V v, double w) {
        int x = verticesIndices.get(u);//TODO: check pre-conditions
        int y = verticesIndices.get(v);
        if (!isDirected) {
            adjacencyMatrix[x][y] = 1;
            adjacencyMatrix[y][x] = 1;
            adjacencyMatrixWeight[x][y] = w;
            adjacencyMatrixWeight[y][x] = w;
        } else {
            adjacencyMatrix[x][y] = 1;
            adjacencyMatrixWeight[x][y] = w;
        }
    }

    /**
     * Attempts to remove vertex 'v' from the graph.
     *
     * @param v vertex to be removed from the graph
     * @return true if 'v' exists in the graph. False otherwise
     */
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

    /**
     * Removes a directed edge from vertex 'u' to vertex 'v' if the graph is directed. Otherwise, removes an undirected
     * edge between vertices 'u' and 'v'.
     *
     * @param u vertex connected with V
     * @param v vertex connected with U
     */
    @Override
    public void removeEdge(V u, V v) {
        if (!isDirected) {
            adjacencyMatrix[(int) u][(int) v] = 0;//TODO: check pre-conditions
            adjacencyMatrix[(int) v][(int) u] = 0;
        } else {
            adjacencyMatrix[(int) u][(int) v] = 0;
        }
    }

    /**
     * Returns a List<V> containing all vertices adjacent to 'v'.
     *
     * @param v vertex whose adjacent vertices are to be consulted
     * @return a List<V> containing all vertices adjacent to 'v'
     */
    @Override
    public List<V> vertexAdjacent(V v) {
        Integer position = verticesIndices.get(v);
        List<V> adjacentVertices = null;
        if (position != null) {
            Set<Integer> adjacentVerticesPositions = new HashSet<>();
            for (int i = 0; i < size; i++) {
                if (adjacencyMatrix[position][i] != 0) {//Vertex at position i is adjacent
                    adjacentVerticesPositions.add(i);
                }
            }
            if (isDirected) {//Only necessary to execute if graph is directed
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
        }
        return adjacentVertices;
    }

    /**
     * If the graph is undirected, determines if vertices 'u' and 'v' share an edge. Otherwise, determines if there is
     * a directed edge from 'u' to 'v' and 'v' to 'u'.
     *
     * @param u a vertex
     * @param v a vertex
     * @return true if and only if said edge exists
     */
    @Override
    public boolean areConnected(V u, V v) {
        int uValor = verticesIndices.get(u);//TODO: check if 'u' and 'v' exist in the graph
        int vValor = verticesIndices.get(v);

        // return adjacencyMatrix[uValor][vValor] == 1 && adjacencyMatrix[vValor][uValor] == 1;
        // This return exists in case there is no need of being specific about the direction

        if (isDirected) {
            return adjacencyMatrix[uValor][vValor] == 1;
            // this returns if u connected and directed to v
        } else {
            return adjacencyMatrix[uValor][vValor] == 1 && adjacencyMatrix[vValor][uValor] == 1;
            // in case the graph is not connected then both should be connected to each other
        }
    }

    /**
     * Returns the weight matrix containing the weights of every edge, directed or not, between all vertices in the
     * graph.
     *
     * @return the matrix containing all weights in the graph
     */
    @Override
    public double[][] weightMatrix() {
        return adjacencyMatrixWeight;
    }

    /**
     * Returns whether the graph is directed.
     *
     * @return true if and only if graph is directed
     */
    @Override
    public boolean isDirected() {
        return isDirected;
    }

    /**
     * Gives the amount of vertices in the graph.
     *
     * @return an int indicating how many vertices are in the graph
     */
    @Override
    public int getVertexSize() {
        return vertices.size();
    }

    /**
     * Returns the index of vertex 'u' in the matrix.
     *
     * @param u the vertex whose index will be returned
     * @return the index of the vertex in the matrix
     */
    @Override
    public int getIndex(V u) {
        return verticesIndices.get(u);//TODO: check pre-conditions
    }
}