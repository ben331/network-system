package datastructure;

public class Edge {
	
	private int origin;
	private int index;
	private double weight;
	
	public Edge(int origin, int index) {
		super();
		this.index = index;
		this.weight = 1;
	}
	
	public Edge(int origin, int index, double weight) {
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

	public int getOrigin() {
		return origin;
	}
}
