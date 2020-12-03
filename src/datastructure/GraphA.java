package datastructure;

import java.util.ArrayList;

public class GraphA <K extends Comparable<K>,V> implements IGraph<K,V> {
	
	private ArrayList<Node<K,V>> nodes;

	private ArrayList<Edge> horizontal;

	public GraphA() {

		horizontal = new ArrayList<>();

	}

	@Override
	public ArrayList<Node<K, V>> BFS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Node<K, V>> DFS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[][] floydWarshall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Double[]> dijkstra(K principalKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double prim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void add(K key, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVertex(K key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEdge(K keyVertex, K keyAdyacent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V search(K key) {
		// TODO Auto-generated method stub
		return null;
	}
	
}