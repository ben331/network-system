package datastructure;

import java.util.ArrayList;

public class Node<K extends Comparable<K>,V> {
	
	//Fields
	private K key;
	private V value;
	private ArrayList<Edge> adjacency;
	
	//Constructor
	public Node(K key, V value, ArrayList<Edge> adjacency) {
		super();
		this.key = key;
		this.value = value;
		this.adjacency = adjacency == null ? new ArrayList<>() : adjacency;
	}
	
	//Getters and Setters
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	
	public double getNeiborgWeight(int index) {
		if(index<adjacency.size()) {
			return adjacency.get(index).getWeight();
		}else {
			return Double.MAX_VALUE;
		}
	}
	
	public int getNeiborgIndex(int index) {
		if(index<adjacency.size()) {
			return adjacency.get(index).getIndex();
		}else {
			return -1;
		}
	}
	
	public int getAdjacents() {
		return adjacency.size();
	}
	public void setValue(V value) {
		this.value = value;
	}
	
	public void addAdjacent(int index) {
		adjacency.add(new Edge(index, 1));
	}
	
	public void addAdjacent(int index, double weight) {
		adjacency.add(new Edge(index, weight));
	}
	
	public String toString() {
		return value.toString();
	}
}
