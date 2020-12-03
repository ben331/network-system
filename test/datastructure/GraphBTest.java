package datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphBTest {

	private GraphB<Integer, String> graph;
	
	void setup1() {
		graph = new GraphB<>(GraphB.SIMPLE_GRAPH);
		
		graph.addNode(1, "Uno");
		graph.addNode(2, "Dos");
		graph.getNodes().get(0).addAdjacent(1, 1);
		graph.getNodes().get(1).addAdjacent(0, 1);
	}
	
	void setup2() {
		graph = new GraphB<>(GraphB.SIMPLE_GRAPH);
		
		graph.addNode(1, "Uno");
		graph.addNode(2, "Dos");
		graph.addNode(3, "Tres");
		graph.getNodes().get(0).addAdjacent(1, 1);
		graph.getNodes().get(1).addAdjacent(0, 1);
		graph.getNodes().get(1).addAdjacent(2, 3);
		graph.getNodes().get(2).addAdjacent(0, 2);
	}
	
	void setup3() {
		graph = new GraphB<>(GraphB.SIMPLE_GRAPH);
		
		graph.addNode(1, "Uno");
		graph.addNode(2, "Dos");
		graph.addNode(3, "Tres");
		graph.addNode(4, "Cuatro");
		graph.addNode(5, "Cinco");
		graph.getNodes().get(0).addAdjacent(1, 1);
		graph.getNodes().get(1).addAdjacent(0, 1);
		graph.getNodes().get(1).addAdjacent(2, 3);
		graph.getNodes().get(2).addAdjacent(0, 2);
		graph.getNodes().get(2).addAdjacent(3, 1);
		graph.getNodes().get(3).addAdjacent(2, 1);
		graph.getNodes().get(2).addAdjacent(4, 1);
		graph.getNodes().get(4).addAdjacent(0, 1);
		graph.getNodes().get(3).addAdjacent(4, 3);
		graph.getNodes().get(4).addAdjacent(3, 3);
	}
	
	@Test
	void testGetters() {
		setup1();
		
		//Nodes
		assertEquals(2, graph.getNodes().size());
		assertEquals(1, graph.getNodes().get(0).getKey());
		assertEquals(2, graph.getNodes().get(1).getKey());
		
		//Adayacents
		assertEquals(2, graph.getNodes().get(graph.getNodes().get(0).getNeiborgIndex(0)).getKey());
		assertEquals(1, graph.getNodes().get(graph.getNodes().get(1).getNeiborgIndex(0)).getKey());
	}
	
	@Test
	void testAdd() {
		setup1();
		
		//Add
		graph.addNode(3, "Tres");
		graph.getNodes().get(2).addAdjacent(1, 1);
		graph.getNodes().get(2).addAdjacent(1, 3);
		
		//Test
		assertEquals(3, graph.getNodes().size());
		
		//Nodes
		assertEquals(2, graph.getNodes().size());
		assertEquals(1, graph.getNodes().get(0).getKey());
		assertEquals(2, graph.getNodes().get(1).getKey());
		
		//Adayacents
		assertEquals(2, graph.getNodes().get(graph.getNodes().get(0).getNeiborgIndex(0)).getKey());
		assertEquals(1, graph.getNodes().get(graph.getNodes().get(1).getNeiborgIndex(0)).getKey());
	}
}