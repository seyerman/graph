package graph;

import java.util.*;

public class AdjacencyMatrixGraph<V> implements IGraph<V> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    private int size; //logic size

    private boolean isDirected;

    private double[][] adjacencyMatrix;

    private Map<Integer, V> vertices;
    private Map<V, Integer> verticesIndices;
    private NavigableSet<Integer> emptySlots = new TreeSet<>();

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
        // TODO Auto-generated method stub

    }

    @Override
    public void addEdge(V u, V v, double w) {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub

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
        return adjacencyMatrix;
    }

    @Override
    public boolean isDirected() {
        return isDirected;
    }
}