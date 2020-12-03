package datastructure;


import java.lang.RuntimeException;
import java.util.ArrayList;

public class GraphA <K extends Comparable<K>,V> implements IGraph<K,V> {
	
	private ArrayList<Node<K,V>> nodes;

	private int matrix[][];

	public GraphA() {


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
	public void add(K key, V value, ArrayList<Edge> adjacency) {
		// TODO Auto-generated method stub
		
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
	
	


}