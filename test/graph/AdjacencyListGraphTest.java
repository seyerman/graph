package graph;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AdjacencyListGraphTest {

	private AdjacencyListGraph<Integer> alg;
	
	//_______________________________________________________________________________________________________
	
	private void stage1() {
		
		
		
	}
	
	private void stage2() {
		
		alg = new AdjacencyListGraph<>();
		
	}
	
	private void stage3() {
		
		alg = new AdjacencyListGraph<>(true);
		
	}
	
	private void stage4() {
		
		alg = new AdjacencyListGraph<Integer>();
		alg.addVertex(1);
		alg.addVertex(2);
		
	}
	
	private void stage5() {
		
		alg = new AdjacencyListGraph<Integer>(true);
		alg.addVertex(1);
		alg.addVertex(2);
		
	}
	
	private void stage6() {
		
		alg = new AdjacencyListGraph<Integer>();
		alg.addVertex(1);
		alg.addVertex(2);
		alg.addEdge(1, 2);
		
	}
	
	//_______________________________________________________________________________________________________
	
	@Test
	public void testAdjacencyListGraph() {
		
		stage1();
		
		alg = new AdjacencyListGraph<>();
		
		assertTrue("The adjacency list was not created", alg != null);
		
	}
	
	@Test
	public void testAdjacencyListGraph2() {
		
		stage1();
		
		alg = new AdjacencyListGraph<>(true);
		
		assertTrue("The adjacency list was not created", alg != null);
		
		assertTrue("The graph is not directed", alg.isDirected() == true);
		
	}
	
	//getVertices() and getAdjancencyList() were created to prove this method
	@Test
	public void testInitialize() {
		
		stage1();
		
		alg = new AdjacencyListGraph<>();
		
		assertTrue("The graph was not correctly created", alg.getVertices() != null && alg.getAdjacencyList() != null);
		
	}
	
	//The method AddVertex() does not work correctly. It adds a vertex even if it was added before. 
	@Test
	public void testAddVertex() {
		
		stage2();
		
		alg.addVertex(11);
		
		assertTrue("The vertex was not added", alg.getVertices().containsKey(11));
		
		assertTrue("That vertex was added before", !alg.addVertex(11));
		
	}
	
	@Test
	public void testAddEdge1() {
		
		stage4();
		
		alg.addEdge(1, 2);
		
		assertTrue("The edge was not added", alg.areConnected(1, 2));
		
	}
	
	@Test
	public void testAddEdge2() {
		
		stage5();
		
		alg.addEdge(1, 2);
		
		assertTrue("The edge was not added", alg.areConnected(1, 2));
		
	}
	
	@Test
	public void testRemoveVertex() {
		
		stage6();
		
		alg.removeEdge(1, 2);
		
		assertTrue("The vertex was not remove", !alg.areConnected(1, 2));
		
	}
	
	@Test
	public void testAreConnected() {
		
		stage6();
		
		assertTrue("The vertex are not connected", alg.areConnected(1, 2));
		
	}

}
























