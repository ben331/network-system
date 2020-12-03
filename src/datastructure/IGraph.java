package datastructure;

import java.util.ArrayList;

public interface IGraph<K extends Comparable<K>,V> {
	
	public ArrayList<Node<K,V>> BFS();
	public ArrayList<Node<K,V>> DFS();
	public double[][] floydWarshall();
	public ArrayList<Double[]> dijkstra(K principalKey);
	public double prim();
	public void addNode(K key, V value);
	public void remove(K key);
	public void searchNode(K key);
	
}
