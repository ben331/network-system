package datastructure;

import java.util.ArrayList;

public interface IGraph<K extends Comparable<K>,V> {
	
	public ArrayList<Node<K,V>> BFS();
	public ArrayList<Node<K,V>> DFS();
	public double[][] floydWarshall();
	public void add(K key, V value, ArrayList<Edge> adjacency);
	public ArrayList<Double[]> dijkstra(K principalKey);
	public double prim();
	
}
