package datastructure;

public class Edge {
	
	private int index;
	private double weight;
	
	public Edge(int index) {
		super();
		this.index = index;
		this.weight = 1;
	}
	
	public Edge(int index, double weight) {
		super();
		this.index = index;
		this.weight = weight;
	}
	
	public int getIndex() {
		return index;
	}
	public double getWeight() {
		return weight;
	}
	
	public String toString() {
		return index+"";
	}
}
